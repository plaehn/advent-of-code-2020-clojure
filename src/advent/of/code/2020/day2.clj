(ns advent.of.code.2020.day2
  (:require [clojure.string :as str]))

(defn parse [line]
  (map #((list
           (read-string (first %))
           (read-string (second %))
           (first (get % 2))
           (get % 3)))
       (str/split line #"[-: ]+")))

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
