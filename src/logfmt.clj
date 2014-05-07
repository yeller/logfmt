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
  "")

(defn out
  "formats a static pair of keys/values and logs it to stdout specifically
  designed for speed - at compile time it expands into a java.String.format
  call"
  [& pairs]
  (println (apply msg pairs)))

(defn err
  "formats a static pair of keys/values and logs it to stderr specifically
  designed for speed - at compile time it expands into a java.String.format
  call"
  [& pairs]
  (binding [*out* *err*]
    (println (apply msg pairs))))
