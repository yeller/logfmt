(defproject logfmt "0.1.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :profiles {:dev {:dependencies [[org.clojure/tools.namespace "0.2.4"]
                                  [criterium  "0.4.3"]
                                  [info.sunng/ring-jetty9-adapter "0.3.0"]]

             :source-paths ["examples"]}
             :bench
             {:dependencies [[criterium  "0.4.3"]]}})
