(ns advent.of.code.2020.day6
  (:require [common.text :as txt]))

(def groups
  (txt/group-by-blank-lines (slurp "day6.txt")))

(defn count-answers [group answer-required-from]
  (let [persons-in-group (txt/count-lines group)
        min-freq (cond
                   (= answer-required-from :everyone) persons-in-group
                   (= answer-required-from :anyone) 1)
        all-answers (txt/remove-whitespace group)
        frequencies (frequencies all-answers)]
    (count (filter #(<= min-freq (second %)) frequencies))))

(defn count-answers-from [answer-required-from]
  (reduce + (map #(count-answers % answer-required-from) groups)))

(def count-part-1 (count-answers-from :anyone))
(println (str "Solution part 1: " count-part-1))
(assert (= count-part-1 6726))

(def count-part-2 (count-answers-from :everyone))
(println (str "Solution part 2: " count-part-2))
(assert (= count-part-2 3316))