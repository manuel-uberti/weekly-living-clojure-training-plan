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
