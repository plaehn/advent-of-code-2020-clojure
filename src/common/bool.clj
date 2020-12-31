(ns common.bool)

(defn xor [a b]
  (and (or a b) (not (and a b))))
