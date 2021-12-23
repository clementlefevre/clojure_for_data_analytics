(ns clojure-for-data-analysis.database-demo  (:require [next.jdbc :as jdbc] [tablecloth.api :as tc]))
;; set the DB Connection
(def ds (jdbc/get-datasource {:jdbcUrl "jdbc:exa:<YOUR_DB_URL>;schema=<YOUR_SCHEMA>"  :user (System/getenv "EXASOL_USER")
                              :password  (System/getenv "EXASOL_PWD") :useSSL false}))

;; query DB
(def raw_rs (jdbc/execute! ds ["SELECT * FROM USERS WHERE USERS.NAME LIKE '%STEFANIA%'"]))

;; convert to dataframe and do your stuff:
(def df (-> raw_rs (tc/dataset  {:key-fn keyword})))


(tc/info df)



