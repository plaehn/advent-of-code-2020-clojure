(ns advent.of.code.2020.day2
  (:require [clojure.string :as str]))

(defn parse [line]
  (let [[min-str max-str chr-str pwd] (str/split line #"[-: ]+")
        chr (first chr-str)
        min (read-string min-str)
        max (read-string max-str)]
    (list min max chr pwd)))

(def lines
  (map #(parse %) (str/split-lines (slurp "day2.txt"))))

(defn count-char [chr str]
  (get (frequencies str) chr 0))

(defn match? [line]
  (let [[min max chr pwd] line]
    (<= min (count-char chr pwd) max)))

(def result-part-1 (count (filter match? lines)))
(println (str "Solution part 1: " result-part-1))
(assert (= result-part-1 460))
