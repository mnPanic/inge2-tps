(declare-const a Int)
(declare-const b Int)
(declare-const c Int)

(declare-const c1 Bool)
(declare-const c2 Bool)
(declare-const c3 Bool)
(declare-const c4 Bool)

(assert (= c1 (or 
    (<= a 0)
    (or (<= b 0) (<= c 0)))))

(assert (= c2 (not (and (> (+ a b) c) (and (> (+ a c) b) (> (+ b c) a))))))

(assert (=
    c3
    (and 
        (= a b)
        (= b c))))

(assert (= c4 (or 
    (= a b)
    (or (= b c) (= a c)))))

; query
(assert (and
    (not c1)
    (and
        (not c2)
        (and
            (not c3)
            c4))))

(check-sat)
(get-model)
