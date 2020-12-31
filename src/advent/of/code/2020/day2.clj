(ns advent.of.code.2020.day2
  (:require [clojure.string :as str]))

(defn parse [line]
  (let [[num1-str num2-str chr-str pwd] (str/split line #"[-: ]+")
        num1 (read-string num1-str)
        num2 (read-string num2-str)
        chr (first chr-str)]
    (list num1 num2 chr pwd)))

(def lines
  (map #(parse %) (str/split-lines (slurp "day2.txt"))))

(defn count-char [chr str]
  (get (frequencies str) chr 0))

(defn match-for-first-policy? [line]
  (let [[min max chr pwd] line]
    (<= min (count-char chr pwd) max)))

(def result-part-1 (count (filter match-for-first-policy? lines)))
(println (str "Solution part 1: " result-part-1))
(assert (= result-part-1 460))
