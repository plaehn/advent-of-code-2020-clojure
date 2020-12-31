(ns advent.of.code.2020.day3
  (:require [clojure.string :as str]))

(def forest-map
  (str/split-lines (slurp "day3.txt")))

(def width (count (first forest-map)))

(defn char-at [position]
  (let [[y x] position]
    (get (get forest-map y)
         (mod x width))))

(def trajectories '([1 3] [1 1] [1 5] [1 7] [2 1]))

(defn path [trajectory]
  (loop [position '[0 0]
         path []]
    (let [current-char (char-at position)]
      (if current-char
        (recur (vec (map + position trajectory)) (conj path current-char))
        path))))

(defn is-tree? [chr]
  (= \# chr))

(defn number-of-trees [trajectory]
  (count (filter is-tree? (path trajectory))))

(def solution1 (number-of-trees (first trajectories)))
(println (str "Solution part 1: " solution1))
(assert (= solution1 234))


