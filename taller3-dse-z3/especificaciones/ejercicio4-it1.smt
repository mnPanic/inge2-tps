(declare-const a Int)
(declare-const b Int)
(declare-const c Int)

(declare-const c1 Bool)

(assert (= c1 (or 
    (<= a 0)
    (or (<= b 0) (<= c 0)))))

; query
(assert (not c1))

(check-sat)
(get-model)
