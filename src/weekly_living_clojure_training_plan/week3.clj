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


;;; Day 2
;; Flipping out
(defn flip [f]
  (fn [& args]
    (apply f (reverse args))))

(= 3 ((flip nth) 2 [1 2 3 4 5]))

(= true ((flip >) 7 8))

(= 4 ((flip quot) 2 8))

(= [1 2 3] ((flip take) [1 2 3 4 5] 3))

;; Rotate a sequence
(defn rotate [d s]
  (if (> d 0)
    (let [p (take d s)
          r (drop d s)
          l (count s)]
      (if (<= d l)
        (flatten (cons r p))
        (let [e (- d l)]
          (flatten (cons (drop e s) (take e s))))))
    (rotate (+ d (count s)) s)))

(= (rotate 2 [1 2 3 4 5]) '(3 4 5 1 2))

(= (rotate -2 [1 2 3 4 5]) '(4 5 1 2 3))

(= (rotate 6 [1 2 3 4 5]) '(2 3 4 5 1))

(= (rotate 1 '(:a :b :c)) '(:b :c :a))

(= (rotate -4 '(:a :b :c)) '(:c :a :b))


;;; Day 3
;; Reverse Interleave
(defn r-interleave [s x]
  (apply map list (partition x s)))

(= (r-interleave [1 2 3 4 5 6] 2) '((1 3 5) (2 4 6)))

(= (r-interleave (range 9) 3) '((0 3 6) (1 4 7) (2 5 8)))

(= (r-interleave (range 10) 5) '((0 5) (1 6) (2 7) (3 8) (4 9)))

;; Split by Type
(defn split-by-type [s]
  (vals (group-by #(type %) s)))

(= (set (split-by-type [1 :a 2 :b 3 :c])) #{[1 2 3] [:a :b :c]})

(= (set (split-by-type [:a "foo" "bar" :b])) #{[:a :b] ["foo" "bar"]})

(= (set (split-by-type [[1 2] :a [3 4] 5 6 :b])) #{[[1 2] [3 4]] [:a :b] [5 6]})


;;; Day 4
;; Prime Numbers
(def whole-numbers (iterate inc 2))

(defn prime? [n]
  (every? false? (map #(= (mod n %) 0) (range 2 n))))

(defn prime-numbers [n]
  (take n (filter prime? whole-numbers)))

(= (prime-numbers 2) [2 3])

(= (prime-numbers 5) [2 3 5 7 11])

(= (last (prime-numbers 100)) 541)


;;; Day 5
;; Anagram Finder
(defn anagram-finder [s]
  (set
   (map set
        (filter #(> (count %) 1)
                (vals (group-by sort s))))))

(= (anagram-finder ["meat" "mat" "team" "mate" "eat"])
   #{#{"meat" "team" "mate"}})

(= (anagram-finder ["veer" "lake" "item" "kale" "mite" "ever"])
   #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}})
