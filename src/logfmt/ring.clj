(ns logfmt.ring
  (:require logfmt))

(defn wrap-logging [handler]
  (fn [request]
    (let [start    (System/nanoTime)
          response (handler request)
          end      (System/nanoTime)]
      (logfmt/out :method     (name (:request-method request))
                  :path       (:uri request)
                  :status     (:status response)
                  :duration   (str (Math/round (float (/ (- end start) 1000000))) "ms"))
      response)))
