(defproject clojure-for-data-analysis "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.clojure/core.async "1.5.640"]
                 [com.github.seancorfield/next.jdbc "1.2.753"]
                 [com.exasol/exasol-jdbc "6.1.3"]
                 [scicloj/tablecloth "6.031"]
                 [com.taoensso/timbre "5.1.2"]
                 [clj-time "0.15.2"]]
  :main ^:skip-aot clojure-for-data-analysis.core
  :aot [clojure-for-data-analysis.core]
  :repl-options {:init-ns clojure-for-data-analysis.core}
  :repositories [["maven.exasol.com" "https://maven.exasol.com/artifactory/exasol-releases"]]
  :profiles {:uberjar {:aot :all}})

