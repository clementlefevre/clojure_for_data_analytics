(ns clojure-for-data-analysis.core (:require
                                    [tech.v3.datatype.datetime]
                                    [clojure-for-data-analysis.excel-demo :as ed]
                                    [taoensso.timbre :as timbre :refer [info error]]
                                    [clj-time.core :as t])
    (:gen-class))

(defn -main [& args]
  (info  (str "Loading Excel files located in : " (first args)))
  (try
    (let [start-time (t/now)]
      (ed/excel-worker (first args))
      (info (str "All files combined. Script took "  (t/in-millis (t/interval start-time (t/now))) " Milliseconds.")))

    (catch Exception e (error (str "caught exception: " (.getMessage e))))))


(comment

  (-main "MY_FOLDER_WITH_EXCEL_FILES"))




