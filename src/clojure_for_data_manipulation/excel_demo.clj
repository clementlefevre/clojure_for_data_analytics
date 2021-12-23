(ns clojure-for-data-manipulation.excel-demo (:require [clojure-for-data-manipulation.file-service :as fs]
                                                       [clojure.string :as s]
                                                       [tablecloth.api :as tc]
                                                       [tech.v3.dataset.io.spreadsheet]
                                                       [tech.v3.libs.fastexcel]
                                                       [tech.v3.libs.poi]
                                                       [taoensso.timbre :as timbre :refer [info error]]))



(defn- clean-col-name
  "Returns a sanitized  name (Wilkommen nach Deutschland)." [col]
  (-> col
      (s/lower-case)
      (s/replace  " " "_")
      (s/replace "\u00fc" "ue")
      (s/replace "\u00df" "ss")
      (s/replace "\u00e4", "ae")))

(defn- read_sheetnames
  "Returns a sequence of sheetnames for a given filepath" [filepath]
  (tech.v3.libs.fastexcel/input->workbook filepath))


(defn- filter_sheets
  "Returns a sequence of sheetnames matching a given strings"
  [sheetsname]
  (let [filter_sheeto '("Top Stationen" "MT_HT" "Fläche")]
    (filter #(.contains filter_sheeto (.name %)) sheetsname)))


(defn load-table
  "Return a dataset from a given filepath"
  [filepath]
  (-> filepath
      (read_sheetnames)
      (filter_sheets)
      (first)
      (tech.v3.dataset.io.spreadsheet/sheet->dataset  {:n-initial-skip-rows 8 :key-fn keyword})))



(defn- create-cols-clean-map
  "Return a sanitized sequence of column names"
  [ds]
  (let [cols_names (tc/column-names ds)]
    (zipmap (doall cols_names) (map #(keyword (clean-col-name (name %))) cols_names))))

(defn load-and-clean-table [filepath]
  (info (format "loading %s" filepath))
  (try
    (let  [ds  (load-table filepath)]
      (-> ds

          (tc/rename-columns   (create-cols-clean-map ds))
          (tc/drop-missing  :tp_nr)))

    (catch Exception e (error (str "caught exception: " (.getMessage e))))))

(defn load-combine-with-pmap [files]
  (info (format "retrieving %s files..." (count files)))
  (->> files
       (pmap load-and-clean-table)
       doall))

(defn excel-worker
  "Given a folder path, load all Excel files and combine them into a single dataframe and save the result as .csv"
  [folder-path]

  (let [result-pmap (load-combine-with-pmap (fs/get-xlsx folder-path)) output_filename (fs/set-filename "csv")]
    (tc/write!    (apply tc/concat result-pmap) output_filename)
    (info (str "Result saved as " output_filename))))

