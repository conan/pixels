(ns pixels.core-test
  (:require [midje.sweet :refer :all]
            [pixels.core :refer :all]))


(def blank-3x4 [["O" "O" "O"]
                ["O" "O" "O"]
                ["O" "O" "O"]
                ["O" "O" "O"]])


(fact "I creates new 3x4 image"
  (I 3 4) => blank-3x4)


(fact "C clears 3x4 image"
  (let [pixmap [["A" "B" "C"]
                ["D" "E" "F"]
                ["G" "H" "I"]
                ["J" "K" "L"]]]
    (C pixmap) => blank-3x4))


(fact "L colours pixel"
  (L blank-3x4 2 2 "L") => [["O" "O" "O"]
                            ["O" "L" "O"]
                            ["O" "O" "O"]
                            ["O" "O" "O"]])


(fact "V draws vertical segment"
  (V blank-3x4 3 2 4 "V") => [["O" "O" "O"]
                              ["O" "O" "V"]
                              ["O" "O" "V"]
                              ["O" "O" "V"]])


(fact "H draws horizontal segment"
  (H blank-3x4 1 3 2 "H") => [["O" "O" "O"]
                              ["H" "H" "H"]
                              ["O" "O" "O"]
                              ["O" "O" "O"]])


(fact "F flood fills regions"
  (let [pixmap [["O" "X" "O"]
                ["O" "X" "X"]
                ["X" "O" "X"]
                ["X" "X" "X"]]]

    ;; fill all the Xs
    (F pixmap 2 2 "F") => [["O" "F" "O"]
                           ["O" "F" "F"]
                           ["F" "O" "F"]
                           ["F" "F" "F"]]

    ;; fill the top left Os
    (F pixmap 1 1 "F") => [["F" "X" "O"]
                           ["F" "X" "X"]
                           ["X" "O" "X"]
                           ["X" "X" "X"]]

    ;; fill the middle O
    (F pixmap 2 3 "F") => [["O" "X" "O"]
                           ["O" "X" "X"]
                           ["X" "F" "X"]
                           ["X" "X" "X"]]))


(fact "S pretty-prints pixmaps"
  (let [pixmap [["O" "X" "O"]
                ["O" "X" "X"]
                ["X" "O" "X"]
                ["X" "X" "X"]]]
    (with-out-str (S pixmap)) => (apply str
                                   "O X O\n"
                                   "O X X\n"
                                   "X O X\n"
                                   "X X X\n")))


