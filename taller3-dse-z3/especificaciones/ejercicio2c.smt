; 5x + 4y = 64
(declare-const x Int)
(declare-const y Int)
(assert (= 
    (* x y)
    64))
(check-sat)
(get-model)