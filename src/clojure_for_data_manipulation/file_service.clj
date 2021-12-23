(ns clojure-for-data-manipulation.file-service (:require [clj-time [core :as t]] [clj-time [format :as f]]))

(defn get-xlsx [path_files] (let [grammar-matcher (.getPathMatcher
                                                   (java.nio.file.FileSystems/getDefault)
                                                   "glob:*.{xlsx}")] (->> path_files
                                                                          clojure.java.io/file
                                                                          file-seq
                                                                          (filter #(.isFile %))
                                                                          (filter #(.matches grammar-matcher (.getFileName (.toPath %))))
                                                                          (mapv #(.getAbsolutePath %)))))


(defn set-filename [extension]
  (str (.replaceAll  (f/unparse (f/formatters :mysql) (t/now)) "[^0-9]" "_") "." extension))