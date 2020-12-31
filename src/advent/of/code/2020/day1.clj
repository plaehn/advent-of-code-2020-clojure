(ns advent.of.code.2020.day1
  (:require [clojure.string :as str]))

(def numbers
  (map #(read-string %) (str/split-lines (slurp "day1.txt"))))

(defn cartesian-product [colls]
  (if (empty? colls)
    '(())
    (for [more (cartesian-product (rest colls))
          x (first colls)]
      (cons x more))))

(defn solve [tuple-size numbers]
  (some
    #(when (= (reduce + %) 2020) (reduce * %))
    (cartesian-product (repeat tuple-size numbers))))

(def result-for-pairs (solve 2 numbers))
(println (str "Solution part 1: " result-for-pairs))
(assert (= result-for-pairs 63616))

(def result-for-triples (solve 3 numbers))
(println (str "Solution part 2: " result-for-triples))
(assert (= result-for-triples 67877784))