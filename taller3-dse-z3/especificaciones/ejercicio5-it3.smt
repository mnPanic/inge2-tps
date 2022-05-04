(declare-const k Real)
(declare-const a0 Real)
(declare-const a1 Real)
(declare-const a2 Real)

(declare-const c0 Bool)
(declare-const c1 Bool)
(declare-const c2 Bool)

; c0: array[0] + k == 0
; c1: array[1] + k == 0
; c2: array[2] + k == 0

(assert (= a0 5.0))
(assert (= a1 1.0))
(assert (= a2 3.0))

(assert (= c0 (= 0 (+ a0 k))))
(assert (= c1 (= 0 (+ a1 k))))
(assert (= c2 (= 0 (+ a2 k))))

; query #1
; !c0 ^ c1 ^ c2
; (assert (and (not c0) (and c1 c2)))
; unsat

; c0
(assert c0)

(check-sat)
(get-model)