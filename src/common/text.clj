(ns common.text
  (:require [clojure.string :as str]))

(defn group-by-blank-lines [input]
  (str/split input #"\r?\n\r?\n"))

(defn remove-whitespace [input]
  (str/replace input #"\s+" ""))

(defn count-lines [input]
  (count (str/split-lines input)))