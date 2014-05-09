(ns logfmt-test
  (:require [clojure.test :refer :all]
            [logfmt :refer :all]))

(defmacro with-err-str [& body]
  `(with-out-str
     (binding [~'*err* ~'*out*]
       ~@body)))

(deftest msg-formats-a-message
  (is (= "foo=bar baz=1"
         (msg :foo "bar" :baz "1"))))

(deftest map->msg-formats-a-message
  (is (= "foo=bar baz=1"
         (map->msg {:foo "bar" :baz "1"}))))

(deftest out-formats-a-msg-to-stdout
  (testing "all literals"
    (is (= "foo=bar baz=1\n"
           (with-out-str
             (out :foo "bar" :baz 1)))))
  (testing "a variable"
    (let [a 1]
      (is (= "foo=bar baz=1\n"
             (with-out-str (out :foo "bar" :baz a)))))))

(deftest err-formats-a-msg-to-stderr
  (is (= "foo=bar baz=1\n"
         (with-err-str
           (err :foo "bar" :baz 1)))))
