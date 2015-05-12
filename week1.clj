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
