(ns minesweeper.board-spec
  (:use [minesweeper.board :only [find-mines] ]
        [speclj.core]))

(describe "Board"
  (context "1x1 field"
    (it "Finds no mines"
      (should= "0" (find-mines '(.))))

    (it "Finds one mine"
      (should= "*" (find-mines '(*)))))

  (context "2x1 field"
    (it "Finds no mines"
      (should= "00" (find-mines '(. .))))

    (it "Finds all mines"
      (should= "**" (find-mines '(* *))))

    (it "Finds left mine"
      (should= "*1" (find-mines '(* .))))

    (it "Finds right mine"
      (should= "1*" (find-mines '(. *)))))
)
