(ns logfmt)

(defn ^String msg
  "takes a series of k/v pairs and turns them into a formatted string. retains
  order of the pairs"
  [& pairs]
  "")

(defn ^String map->msg
  "takes a map, and turns it into a logfmt string. sorts the order of the keys
  output, so your log messages are stable"
  [m]
  "")

(defmacro out
  "formats a static pair of keys/values and logs it to stdout specifically
  designed for speed - at compile time it expands into a java.String.format
  call"
  [& pairs]
  "")
