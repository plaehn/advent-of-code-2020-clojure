(ns advent.of.code.2020.day6
  (:require [clojure.string :as str]))

(def groups
  (str/split (slurp "day6.txt") #"\r?\n\r?\n"))

(defn count-answers [group]
  (let [all-answers (str/replace group #"\s+" "")
        frequencies (frequencies all-answers)]
    (count frequencies)))

(def count-part-1
  (reduce + (map #(count-answers %) groups)))

(println (str "Solution part 1: " count-part-1))
(assert (= count-part-1 6726))

