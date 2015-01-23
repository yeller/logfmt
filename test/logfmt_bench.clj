(ns logfmt-bench
  (:require [criterium.core :refer :all]
            [logfmt :refer :all]))

(defn -main [& args]
  (println "\nmsg")
  (println "===================")
  (bench
    (msg :foo "bar" :baz "1"))

  (println "\nmap->msg")
  (println "===================")
  (bench
    (map->msg {:foo "bar" :baz "1"}))

  (println "\nout")
  (println "===================")
  (bench
    (with-out-str
      (out :foo "bar" :baz "1")))

  (println "\nerr")
  (println "===================")
  (bench
    (with-out-str
      (binding [*err* *out*]
        (err :foo "bar" :baz "1")))))
