(ns logfmt
  (:require [clojure.string :as s]))

(defn ^String msg
  "takes a series of k/v pairs and turns them into a formatted string. retains
  order of the pairs"
  [& pairs]
  (s/join " "
    (map (fn [[k v]] (str (name k) "=" v))
        (partition 2 pairs))))

(defn ^String map->msg
  "takes a map, and turns it into a logfmt string. sorts the order of the keys
  output, so your log messages are stable"
  [m]
  (s/join " "
          (map (fn [[k v]] (str (name k) "=" v)) m)))

(defn atomic-literal? [x]
  (or (string? x)
      (keyword? x)
      (number? x)))

(defn format-pairs
  "helper function for out and err
   expands any literal values into string values, doing string concatenation
   at compile time"
  [pairs]
  (let [partitioned-pairs (partition 2 pairs)]
    `(str
       ~@(loop [[k v :as current] (first partitioned-pairs)
                remaining (rest partitioned-pairs)
                out []]
           (if (nil? current)
             out
             (recur
               (first remaining)
               (rest remaining)

               (if (first out)
                 (if (atomic-literal? v)
                   (conj out (str " " (name k) "=" v))
                   (conj out (str " " (name k) "=") v))

                 (if (atomic-literal? v)
                   (conj out (str (name k) "=" v))
                   (conj out (str (name k) "=") v)))))))))

(defmacro out
  "formats a static pair of keys/values and logs it to stdout. Specifically
   designed for speed - does a bunch of string concatenation at compile time

  (out :foo a) expands to something like
  (println (str \"foo= \" a))

  (out :foo a :bar 1) expands to something like
  (println (str \"foo= \" a \" bar=1\"))
  "
  [& pairs]
  `(println ~(format-pairs pairs)))

(defmacro err
  "formats a static pair of keys/values and logs it to stderr. Specifically
   designed for speed - does a bunch of string concatenation at compile time

   expands exactly like logfmt/out, except it prints to stderr"
  [& pairs]
  (binding [*out* *err*]
    `(println ~(format-pairs pairs))))
