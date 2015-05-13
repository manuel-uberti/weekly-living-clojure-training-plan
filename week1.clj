(ns living-clojure.week1)

;;; Day 1
(= "HELLO WORLD" (.toUpperCase "hello world"))

(= (list :a :b :c) '(:a :b :c))

(= '(1 2 3 4) (conj '(2 3 4) 1))
(= '(1 2 3 4) (conj '(3 4) 2 1))

(= [:a :b :c]
   (list :a :b :c)
   (vec '(:a :b :c))
   (vector :a :b :c))

(= [1 2 3 4] (conj [1 2 3] 4))
(= [1 2 3 4] (conj [1 2] 3 4))

(= #{:a :b :c :d} (set '(:a :a :b :c :c :c :c :d :d)))
(= #{:a :b :c :d} (clojure.set/union #{:a :b :c} #{:b :c :d}))

(= 20 ((hash-map :a 10, :b 20, :c 30) :b))
(= 20 (:b {:a 10, :b 20, :c 30}))

(= {:a 1, :b 2, :c 3} (conj {:a 1} {:b 2} [:c 3]))

(= 3 (first '(3 2 1)))
(= 3 (second [2 3 4]))
(= 3 (last (list 1 2 3)))


;;; Day 2
(= '(20 30 40) (rest [10 20 30 40]))

(= 8 ((fn add-five [x] (+ x 5)) 3))
(= 8 ((fn [x] (+ x 5)) 3))
(= 8 (#(+ % 5) 3))
(= 8 ((partial + 5) 3))

(= ((partial * 2) 2) 4)
(= (#(* % 2) 3) 6)
(= ((partial * 2) 11) 22)
(= (#(* % 2) 7) 14)

(= ((fn [n] (str "Hello, " n "!")) "Dave") "Hello, Dave!")
(= (#(str "Hello, " % "!") "Jenn") "Hello, Jenn!")
(= ((fn greeting [n] (str "Hello, " n "!")) "Rhea") "Hello, Rhea!")

(= '(6 7 8) (map #(+ % 5) '(1 2 3)))

(= '(6 7) (filter #(> % 5) ' (3 4 5 6 7)))

(= 7 (let [x 5] (+ 2 x)))
(= 7 (let [x 3, y 10] (- y x)))
(= 7 (let [x 21] (let [y 3] (/ x y))))

(= 10 (let [x 4, y 6] (+ x y)))
(= 4 (let [y 1, z 3] (+ y z)))
(= 1 (let [z 1] z))


;;; Day 3
(= "ABC" (apply str (re-seq #"[A-Z]+" "bA1B3Ce")))

(= '(5 4 3 2 1) ((fn foo [x] (when (> x 0) (conj (foo (dec x)) x))) 5))

(= [7 6 5 4 3]
   (loop [x 5
          result []]
     (if (> x 0)
       (recur (dec x) (conj result (+ 2 x)))
       result)))

(= (last (sort (rest (reverse [2 5 4 1 3 6]))))
   (-> [2 5 4 1 3 6] (reverse) (rest) (sort) (last))
   5)

(= (reduce + (map inc (take 3 (drop 2 [2 5 4 1 3 6]))))
   (->> [2 5 4 1 3 6] (drop 2) (take 3) (map inc) (reduce +))
   11)

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
(= (second (reverse (list 1 2 3 4 5))) 4)
(= (second (reverse ["a" "b" "c"])) "b")
(= (second (reverse [[1 2] [3 4]])) [1 2])

(= (reduce + [1 2 3]) 6)
(= (reduce + (list 0 -2 5 5)) 8)
(= (reduce + #{4 2 1}) 7)
(= (reduce + '(0 0 -1)) -1)
(= (reduce + '(1 10 3)) 14)

(= (remove even? #{1 2 3 4 5}) '(1 3 5))
(= (filter (complement even?) [4 2 1 6]) '(1))
(= (remove even? [2 2 4 6]) '())
(= (filter (complement even?) [1 1 1 3]) '(1 1 1 3))

(false? (= (reverse '(1 2 3 4 5)) '(1 2 3 4 5)))
(true? (= (apply str (reverse "racecar")) "racecar"))
(true? (= (reverse [:foo :bar :foo]) [:foo :bar :foo]))
(true? (= (reverse '(1 1 3 3 1 1)) '(1 1 3 3 1 1)))
(false? (= (reverse '(:a :b :c)) '(:a :b :c)))

(= (#(flatten (for [e %] [e e])) [1 2 3]) '(1 1 2 2 3 3))
(= (#(flatten (for [e %] [e e])) [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))
(= (#(for [e % s [e e]] s) [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))
