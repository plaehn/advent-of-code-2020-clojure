(ns advent.of.code.2020.day1
  (:require [clojure.string :as str]))

(def input
  (map #(read-string %) (str/split-lines (slurp "day1.txt"))))

(def cartesianProduct
  (for [first input
        second input]
    (list first second)))

(def result
  (some #(when (= (reduce + %) 2020) (reduce * %)) cartesianProduct))

(println (str "Solution part 1: " result))
(assert (= result 63616))

(def cartesianProduct
  (for [first input
        second input
        third input]
    (list first second third)))

(def result
  (some #(when (= (reduce + %) 2020) (reduce * %)) cartesianProduct))

(println (str "Solution part 2: " result))
(assert (= result 67877784))