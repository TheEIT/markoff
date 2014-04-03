(ns markoff.core
  (:require [clojure.string :as st]))

(defn markoff
  "Applies the instructions in pgm-vec, starting with the first, to input-str."
  [input-str pgm-vec]
  (loop [sigma input-str
         line 0]
    (let [{:keys [theta phi a b]} (nth pgm-vec line)]
      (if (= line a b)
        (do (println sigma)
            sigma)
        (let [newstr (st/replace-first sigma theta phi)]
          (if (identical? sigma newstr)
            (recur newstr a)
            (do (println sigma)
                (recur newstr b))))))))
