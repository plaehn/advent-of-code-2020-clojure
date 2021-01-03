(ns advent.of.code.2020.day7
  (:require [clojure.string :as str]))

(defn parse-contained-bag-with-count [contained-bag-with-count]
  (let [bag-count (read-string contained-bag-with-count)
        bag-color (str/replace-first contained-bag-with-count #"\d+\s+" "")]
    [bag-count bag-color]))

(defn parse-contained-bags-with-count [contained-bags-with-count]
  (if (= (get contained-bags-with-count 0) "no other")
    '()
    (map #(parse-contained-bag-with-count %) contained-bags-with-count)))

(defn parse-rule [line]
  (let [[container-bag contained-str] (str/split line #" bags contain ")
        contained-bags-with-count (str/split contained-str #" bags?, | bags?\.")]
    [container-bag (parse-contained-bags-with-count contained-bags-with-count)]))

(def bag-rules "Maps container bags to list of contained bags with count, eg.

{\"muted yellow\" ([2 \"shiny gold\"] [9 \"faded blue\"]),
 \"light red\" ([1 \"bright white\"] [2 \"muted yellow\"]),
 \"dotted black\" (),
 ...}"
  (into {} (map #(parse-rule %) (str/split-lines (slurp "day7.txt")))))


(defn can-contain? [bag own-color]
  (some
    (fn [[_ color]]
      (or
        (= color own-color)
        (can-contain? color own-color)))
    (bag-rules bag)))

(defn count-bags-in [bag]
  (let [contained-bags (bag-rules bag)]
    (if (empty? contained-bags)
      0
      (reduce
        +
        (map
          (fn [[n color]]
            (* n (inc (count-bags-in color))))
          contained-bags)))))

(def solution-part-1 (count (filter #(can-contain? (first %) "shiny gold") bag-rules)))
(println (str "Solution part 1: " solution-part-1))
(assert (= solution-part-1 257))

(def solution-part-2 (count-bags-in "shiny gold"))
(println (str "Solution part 2: " solution-part-2))
(assert (= solution-part-2 1038))
