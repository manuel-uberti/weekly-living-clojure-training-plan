(ns living-clojure.week1)

;;; Day 1
;; Intro to Strings
(= "HELLO WORLD" (.toUpperCase "hello world"))

;; Intro to Lists
(= (list :a :b :c) '(:a :b :c))

;; Lists: conj
(= '(1 2 3 4) (conj '(2 3 4) 1))
(= '(1 2 3 4) (conj '(3 4) 2 1))

;; Intro to Vectors
(= [:a :b :c]
   (list :a :b :c)
   (vec '(:a :b :c))
   (vector :a :b :c))

;; Vectors: conj
(= [1 2 3 4] (conj [1 2 3] 4))
(= [1 2 3 4] (conj [1 2] 3 4))

;; Intro to Sets
(= #{:a :b :c :d} (set '(:a :a :b :c :c :c :c :d :d)))
(= #{:a :b :c :d} (clojure.set/union #{:a :b :c} #{:b :c :d}))

;; Sets: conj
(= #{1 2 3 4} (conj #{1 4 3} 2))

;; Intro to Maps
(= 20 ((hash-map :a 10, :b 20, :c 30) :b))
(= 20 (:b {:a 10, :b 20, :c 30}))

;; Maps: conj
(= {:a 1, :b 2, :c 3} (conj {:a 1} {:b 2} [:c 3]))

;; Intro to Sequences
(= 3 (first '(3 2 1)))
(= 3 (second [2 3 4]))
(= 3 (last (list 1 2 3)))


;;; Day 2
;; Sequences: rest
(= '(20 30 40) (rest [10 20 30 40]))

;; Intro to Functions
(= 8 ((fn add-five [x] (+ x 5)) 3))
(= 8 ((fn [x] (+ x 5)) 3))
(= 8 (#(+ % 5) 3))
(= 8 ((partial + 5) 3))

;; Double Down
(= ((partial * 2) 2) 4)
(= (#(* % 2) 3) 6)
(= ((partial * 2) 11) 22)
(= (#(* % 2) 7) 14)

;; Hello World
(= ((fn [n] (str "Hello, " n "!")) "Dave") "Hello, Dave!")
(= (#(str "Hello, " % "!") "Jenn") "Hello, Jenn!")
(= ((fn greeting [n] (str "Hello, " n "!")) "Rhea") "Hello, Rhea!")

;; Sequences: Maps
(= '(6 7 8) (map #(+ % 5) '(1 2 3)))

;; Sequences: filter
(= '(6 7) (filter #(> % 5) ' (3 4 5 6 7)))

;; Local bindings
(= 7 (let [x 5] (+ 2 x)))
(= 7 (let [x 3, y 10] (- y x)))
(= 7 (let [x 21] (let [y 3] (/ x y))))

;; Let it be
(= 10 (let [x 4, y 6] (+ x y)))
(= 4 (let [y 1, z 3] (+ y z)))
(= 1 (let [z 1] z))


;;; Day 3
;; Regular Expressions
(= "ABC" (apply str (re-seq #"[A-Z]+" "bA1B3Ce")))

;; Simple Recursion
(= '(5 4 3 2 1) ((fn foo [x] (when (> x 0) (conj (foo (dec x)) x))) 5))

;; Recurring Theme
(= [7 6 5 4 3]
   (loop [x 5
          result []]
     (if (> x 0)
       (recur (dec x) (conj result (+ 2 x)))
       result)))

;; Rearranging Code: ->
(= (last (sort (rest (reverse [2 5 4 1 3 6]))))
   (-> [2 5 4 1 3 6] (reverse) (rest) (sort) (last))
   5)

;; Rearranging Code: ->>
(= (reduce + (map inc (take 3 (drop 2 [2 5 4 1 3 6]))))
   (->> [2 5 4 1 3 6] (drop 2) (take 3) (map inc) (reduce +))
   11)

;; For the win
(= '(1 5 9 13 17 21 25 29 33 37)
   (for [x (range 40)
         :when (= 1 (rem x 4))]
     x))

(= '(1 5 9 13 17 21 25 29 33 37)
   (for [x (iterate #(+ 4 %) 0)
         :let [z (inc x)]
         :while (< z 40)]
     z))

(= '(1 5 9 13 17 21 25 29 33 37)
   (for [[x y] (partition 2 (range 20))]
     (+ x y)))


;;; Day 4
;; Penultimante Element
(= (second (reverse (list 1 2 3 4 5))) 4)
(= (second (reverse ["a" "b" "c"])) "b")
(= (second (reverse [[1 2] [3 4]])) [1 2])

;; Sum It All Up
(= (reduce + [1 2 3]) 6)
(= (reduce + (list 0 -2 5 5)) 8)
(= (reduce + #{4 2 1}) 7)
(= (reduce + '(0 0 -1)) -1)
(= (reduce + '(1 10 3)) 14)

;; Find the odd numbers
(= (remove even? #{1 2 3 4 5}) '(1 3 5))
(= (filter (complement even?) [4 2 1 6]) '(1))
(= (remove even? [2 2 4 6]) '())
(= (filter (complement even?) [1 1 1 3]) '(1 1 1 3))

;; Palindrome Detector
(false? (= (reverse '(1 2 3 4 5)) '(1 2 3 4 5)))
(true? (= (apply str (reverse "racecar")) "racecar"))
(true? (= (reverse [:foo :bar :foo]) [:foo :bar :foo]))
(true? (= (reverse '(1 1 3 3 1 1)) '(1 1 3 3 1 1)))
(false? (= (reverse '(:a :b :c)) '(:a :b :c)))

;; Duplicate a Sequence
(= (#(flatten (for [e %] [e e])) [1 2 3]) '(1 1 2 2 3 3))
(= (#(flatten (for [e %] [e e])) [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))
(= (#(for [e % s [e e]] s) [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))


;;; Day 5
;; Compress a Sequence
(= (apply str (reduce (fn [r v] (if (some #{v} r) r (conj r v)))
                      [] "Leeeeerrroyyy"))
   "Leroy")
(= (map first (partition-by identity [1 1 2 3 3 2 2 3])) '(1 2 3 2 3))
(= (map first (partition-by identity [[1 2] [1 2] [3 4] [1 2]]))
   '([1 2] [3 4] [1 2]))

;; Pack a Sequence
(= (partition-by identity [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))
(= (partition-by identity [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))
(= (partition-by identity [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4])))

;; Drop Every Nth Item
(defn drop-nth [c n]
  (for [c (partition-all n c)]
    (take (dec n) c)))

(= (flatten (drop-nth [1 2 3 4 5 6 7 8] 3))
   [1 2 4 5 7 8])

(= (flatten (drop-nth [:a :b :c :d :e :f] 2))
   [:a :c :e])

(= (flatten (drop-nth [1 2 3 4 5 6] 4))
   [1 2 3 5 6])

;; Intro to Iterate
(= '(1 4 7 10 13) (take 5 (iterate #(+ 3 %) 1)))

;; Replicate a Sequence
(defn f-repeat [c n]
  (for [c c
        r (repeat n c)]
    r))

(= (f-repeat [1 2 3] 2)
   '(1 1 2 2 3 3))

(= (f-repeat [:a :b] 4)
   '(:a :a :a :a :b :b :b :b))

(= (f-repeat [4 5 6] 1) '(4 5 6))

(= (f-repeat [[1 2] [3 4]] 2)
   '([1 2] [1 2] [3 4] [3 4]))

(= (f-repeat [44 33] 2) [44 44 33 33])
