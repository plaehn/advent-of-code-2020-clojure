(ns advent.of.code.2020.day3
  (:require [clojure.string :as str]))

(def forest-map
  (str/split-lines (slurp "day3.txt")))

(def width (count (first forest-map)))

(defn char-at [position]
  (let [[y x] position]
    (get (get forest-map y)
         (mod x width))))

(def trajectory [1 3])

(def path
  (loop [position '[0 0]
         path []]
    (let [current-char (char-at position)]
      (if current-char
        (recur (vec (map + position trajectory)) (conj path current-char))
        path))))

(defn is-tree? [chr]
  (= \# chr))

(def number-of-trees
  (count (filter is-tree? path)))

(println (str "Solution part 1: " number-of-trees))
(assert (= number-of-trees 234))


