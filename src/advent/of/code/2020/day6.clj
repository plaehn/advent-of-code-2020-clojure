(ns advent.of.code.2020.day6
  (:require [common.text :as txt]))

(def groups
  (txt/group-by-blank-lines (slurp "day6.txt")))

(defn count-answers [group required-freq]
  (let [min-freq (if (= required-freq :everyone)
                   (txt/count-lines group)
                   1)
        all-answers (txt/remove-whitespace group)
        frequencies (frequencies all-answers)]
    (count (filter #(<= min-freq (second %)) frequencies))))

(def count-part-1
  (reduce + (map #(count-answers % :anyone) groups)))

(def count-part-2
  (reduce + (map #(count-answers % :everyone) groups)))

(println (str "Solution part 1: " count-part-1))
(assert (= count-part-1 6726))

(println (str "Solution part 2: " count-part-2))
(assert (= count-part-2 3316))

