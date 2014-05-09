(ns logfmt.ring-example-app
  (:require [ring.adapter.jetty9 :as jetty]
            [logfmt.ring :as logfmt-ring]))

(defn -main [& args]
  (jetty/run-jetty
    (logfmt-ring/wrap-logging
      (fn [& args] {:status 200 :body "example"}))
    {:port 4001}))
