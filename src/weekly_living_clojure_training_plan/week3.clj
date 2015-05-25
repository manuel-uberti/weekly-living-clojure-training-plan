(ns weekly-living-clojure-training-plan.week3)

;;; Day 1
;; To Tree, or not to Tree
(defn btree? [s]
  (and (= (count s) 3)
       (let [b (second s)
             c (last s)]
         (and
          (or (nil? b)
              (and (coll? b) (btree? b)))
          (or (nil? c)
              (and (coll? c) (btree? c)))))))

(= (btree? '(:a (:b nil nil) nil))
   true)

(= (btree? '(:a (:b nil nil)))
   false)

(= (btree? [1 nil [2 [3 nil nil] [4 nil nil]]])
   true)

(= (btree? [1 [2 nil nil] [3 nil nil] [4 nil nil]])
   false)

(= (btree? [1 [2 [3 [4 nil nil] nil] nil] nil])
   true)

(= (btree? [1 [2 [3 [4 false nil] nil] nil] nil])
   false)

(= (btree? '(:a nil ()))
   false)

;; Beauty is Simmetry
(defn stree?
  ([s] (stree? (second s) (last s)))
  ([b c] (or
          (and (nil? b) (nil? c))
          (and
           (and (coll? b) (coll? c))
           (= (count b) (count c) 3)
           (= (first b) (first c))
           (stree? (second b) (last c))
           (stree? (last b) (second c))))))

(= (stree? '(:a (:b nil nil) (:b nil nil))) true)

(= (stree? '(:a (:b nil nil) nil)) false)

(= (stree? '(:a (:b nil nil) (:c nil nil) false)))

(= [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
    [2 [3 nil [4 [6 nil nil] [5 nil nil]]] nil]])

(= (stree? [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
            [2 [3 nil [4 [6 nil nil] [5 nil nil]]] nil]])
   true)

(= (stree? [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
            [2 [3 nil [4 [5 nil nil] [6 nil nil]]] nil]])
   false)

(= (stree? [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
            [2 [3 nil [4 [6 nil nil] nil]] nil]])
   false)
