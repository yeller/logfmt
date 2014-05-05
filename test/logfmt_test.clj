(ns logfmt-test
  (:require [clojure.test :refer :all]
            [logfmt :refer :all]))

(deftest it-formats-a-message
  (is (= "foo=bar baz=1"
         (msg :foo "bar" :baz "1")
        ))
  )
