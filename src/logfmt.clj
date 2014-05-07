(ns logfmt
  (:require [clojure.string :as s]))

(defn ^String msg
  "takes a series of k/v pairs and turns them into a formatted string. retains
  order of the pairs"
  [& pairs]
  (s/join " "
    (map (fn [k v] (str (name k) "=" v)) (take-nth 2 pairs) (take-nth 2 (rest pairs)))))

(defn ^String map->msg
  "takes a map, and turns it into a logfmt string. sorts the order of the keys
  output, so your log messages are stable"
  [m]
  (s/join " "
          (map (fn [[k v]] (str (name k) "=" v)) m)))

(defmacro out
  "formats a static pair of keys/values and logs it to stdout specifically
  designed for speed - at compile time it expands into a java.String.format
  call

  (out :foo a) expands to
  (println (str \"foo= \" a))

  (out :foo a :bar 1) expands to
  (println (str \"foo= \" a \" bar=1\"))
  "
  [& pairs]
  `(println (msg ~@pairs)))

(defmacro err
  "formats a static pair of keys/values and logs it to stderr specifically
  designed for speed - at compile time it expands into a java.String.format
  call
  expands exactly like logfmt/out, except it prints to stderr"
  [& pairs]
  (binding [*out* *err*]
    `(println (msg ~@pairs))))
