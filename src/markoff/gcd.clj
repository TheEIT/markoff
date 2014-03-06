(ns markoff.gcd
  (:use [markoff.core]))

(def gcd-pgm [{:theta "ab" :phi "" :a 2 :b 1}
              {:theta "" :phi "c" :a 0 :b 0}
              {:theta "a" :phi "b" :a 3 :b 2}
              {:theta "c" :phi "a" :a 4 :b 3}
              {:theta "b" :phi "b" :a 5 :b 0}
              {:theta "" :phi "" :a 5 :b 5}])

(defn gcd
  "Uses strings to calculate the greatest common denominator of m and n."
  [m n]
  (markoff (str (apply str (repeat m "a")) (apply str (repeat n "b")))
           gcd-pgm))

(defn -main
  []
  (gcd 48 28))