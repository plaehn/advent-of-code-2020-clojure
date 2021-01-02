(ns advent.of.code.2020.day5
  (:require [clojure.string :as str]))

(def boarding-passes
  (str/split-lines (slurp "day5.txt")))

(defn reduce-range [input max-val first-half-chr]
  (first
    (reduce
      (fn [range chr]
        (let [half-size (/ (count range) 2)]
          (if (= chr first-half-chr)
            (take half-size range)
            (drop half-size range))))
      (range 0 (inc max-val))
      input)))

(defn compute-seat-id [boarding-pass]
  (let [divider (- (count boarding-pass) 3)
        row-str (subs boarding-pass 0 divider)
        col-str (subs boarding-pass divider)
        row (reduce-range row-str 127 \F)
        col (reduce-range col-str 7 \L)
        seat-id (+ (* 8 row) col)]
    seat-id))

(def seat-ids
  (map #(compute-seat-id %) boarding-passes))

(def max-seat-id
  (apply max seat-ids))

(defn has-gap? [pair]
  (< 1 (reduce - (reverse pair))))

(def my-seat-id
  (let [sorted-pairs (partition 2 1 (sort seat-ids))
        pairs-with-gap (filter #(has-gap? %) sorted-pairs)
        my-seat-id (inc (first (first pairs-with-gap)))]
    my-seat-id))

(println (str "Solution part 1: " max-seat-id))
(assert (= max-seat-id 826))

(println (str "Solution part 2: " my-seat-id))
(assert (= my-seat-id 678))

