(ns markoff.core
  (:require [clojure.string :as st]))

(defn markoff
  "Applies the instructions in pgm-vec, starting with the first, to input-str."
  [input-str pgm-vec]
  (loop [sigma input-str
         line 0]
    (let [{:keys [theta phi a b]} (nth pgm-vec line)]
      (if (= line a b)
        sigma
        (let [newstr (st/replace-first sigma theta phi)]
          (println sigma)
          (recur newstr
                 (if (identical? sigma newstr) a b)))))))
