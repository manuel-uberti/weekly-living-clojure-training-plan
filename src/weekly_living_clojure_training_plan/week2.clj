(ns weekly-living-clojure-training-plan.week2)

;;; Day 1
;; Fibonacci Sequence
(defn fibo [n]
  (loop [n n
         f 0
         s 1
         r (conj [] s)]
    (if (< n 1)
      r
      (recur (dec n) s (+ f s) (conj r (+ f s))))))

(defn n-fibo [n]
  (take n (fibo n)))

(= (n-fibo 3) '(1 1 2))
(= (n-fibo 6) '(1 1 2 3 5 8))
(= (n-fibo 8) '(1 1 2 3 5 8 13 21))

;; Get the Caps
(defn caps [s]
  (apply str (re-seq #"[A-Z]+" s)))

(= (caps "HeLlO, WoRlD!") "HLOWRD")
(empty? (caps "nothing"))
(= (caps "$#A(*987Zf") "AZ")

;; Intro to some
(= 6 (some #{2 7 6} [5 6 7 8]))
(= 6 (some #(when (even? %) %) [5 6 7 8]))

;; Factorial Fun
;; (defn fact-1 [n]
;;   (* n (fact (dec n))))
(defn fact [n]
  (loop [r 1
         n n]
    (if (= n 0)
      r
      (recur (* n r) (dec n)))))

(= (fact 1) 1)
(= (fact 3) 6)
(= (fact 5) 120)
(= (fact 8) 40320)

;; Intro to Destructuring
(= [2 4] (let [[a b c d e f g] (range)] [c e]))


;;; Day 2
;; Advanced Destructuring
(= [1 2 [3 4 5] [1 2 3 4 5]] (let [[a b & c :as d]  [1 2 3 4 5]] [a b c d]))

;; A Half-Truth
(defn half-truth [& args]
  (cond
    (every? false? args) false
    (nil? (some false? args)) false
    :else true))

(= false (half-truth false false))
(= true (half-truth true false))
(= false (half-truth true))
(= true (half-truth false true false))
(= false (half-truth true true true))
(= true (half-truth true true true false))

;; Greatest Common Divisor
(defn gcd [a b]
  (cond
    (= b 0) a
    (= a 0) b
    (= a b) a
    (> a b) (gcd (- a b) b)
    (< a b) (gcd a (- b a))))

(= (gcd 2 4) 2)
(= (gcd 10 5) 5)
(= (gcd 5 7) 1)
(= (gcd 1023 858) 33)


;;; Day 3
;; Simple closures
(defn f [x]
  (fn [n]
    (reduce * (repeat x n))))

(= 256 ((f 2) 16) ((f 8) 2))
(= [1 8 27 64] (map (f 3) [1 2 3 4]))
(= [1 2 4 8 16] (map #((f %) 2) [0 1 2 3 4]))

;; Cartesian Product
(defn cp [a b]
  (into #{} (for [a a b b] [a b])))

(= (cp #{"ace" "king" "queen"} #{"spade" "heart" "diamond" "club"})
   #{["ace"   "spade"] ["ace"   "heart"] ["ace"   "diamond"] ["ace"   "club"]
     ["king"  "spade"] ["king"  "heart"] ["king"  "diamond"] ["king"  "club"]
     ["queen" "spade"] ["queen" "heart"] ["queen" "diamond"] ["queen" "club"]})

(= (cp #{1 2 3} #{4 5})
   #{[1 4] [2 4] [3 4] [1 5] [2 5] [3 5]})

(= 300 (count (cp (into #{} (range 10))
                  (into #{} (range 30)))))


;;; Day 4
;; Symmetric Difference
(defn sd [a b]
  (clojure.set/union (clojure.set/difference a b)
                     (clojure.set/difference b a)))

(= (sd #{1 2 3 4 5 6} #{1 3 5 7}) #{2 4 6 7})
(= (sd #{:a :b :c} #{}) #{:a :b :c})
(= (sd #{} #{4 5 6}) #{4 5 6})
(= (sd #{[1 2] [2 3]} #{[2 3] [3 4]}) #{[1 2] [3 4]})

;; Least Common Multiple
(defn lcm [& args]
  (reduce (fn [r x] (* (/ x (gcd r x)) r)) args))

(== (lcm 2 3) 6)
(== (lcm 5 3 7) 105)
(== (lcm 1/3 2/5) 2)
(== (lcm 3/4 1/6) 3/2)
(== (lcm 7 5/7 2 3/5) 210)


;;; Day 5
;; Pascal's Triangle
(defn pt [n]
  (if (= n 1)
    [1]
    (into []
          (map #(apply + %)
               (partition 2 1
                          (concat [0] (pt (- n 1)) [0]))))))

(= (pt 1) [1])

(= (map pt (range 1 6))
   [    [1]
        [1 1]
        [1 2 1]
        [1 3 3 1]
        [1 4 6 4 1]])

(= (pt 11)
   [1 10 45 120 210 252 210 120 45 10 1])
