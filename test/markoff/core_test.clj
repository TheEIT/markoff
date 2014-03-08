(ns markoff.core-test
  (:require [clojure.test :refer :all]
            [markoff.core :refer :all]))
			
(def missing-keys-pgm
  [{:theta "x" :phi "y" :b 1}
   {:theta "y" :phi "z" :a 0 :b 2}
   {:theta "z" :phi "" :b 3}
   {:theta "" :phi "" :a 3 :b 3}])

(deftest missing-keys
  (testing "If program is missing keys, error should result, even if input is valid"
    (is (not (string? (markoff "xx" missing-keys-pgm))))))
