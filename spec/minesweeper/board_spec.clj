(ns minesweeper.board-spec
  (:use [minesweeper.board :only [find-mines] ]
        [speclj.core]))

(describe "find-mines"
  (context "1x1 field"
    (it "finds no mines"
      (let [expected '((0))
            field    '((.))]
        (should=  expected (find-mines field))))

    (it "finds one mine"
      (let [expected '((*))
            field    '((*))]
        (should=  expected (find-mines field)))))

  (context "1x2 field"
    (it "finds no mines"
      (let [expected '((0 0))
            field    '((. .))]
        (should=  expected (find-mines field))))

    (it "finds all mines"
      (let [expected '((* *))
            field    '((* *))]
        (should=  expected (find-mines field))))

    (it "finds left mine"
      (let [expected '((1 *))
            field    '((. *))]
        (should=  expected (find-mines field))))

    (it "finds right mine"
      (let [expected '((* 1))
            field    '((* .))]
        (should=  expected (find-mines field)))))

  (context "2x1 field"
    (it "finds no mines"
      (let [expected '((0)
                       (0))
            field    '((.)
                       (.))]
        (should=  expected (find-mines field))))

    (it "finds all mines"
      (let [expected '((*)
                       (*))
            field    '((*)
                       (*))]
        (should=  expected (find-mines field))))

    (it "finds top mine"
      (let [expected '((*)
                       (1))
            field    '((*)
                       (.))]
        (should=  expected (find-mines field))))

    (it "finds bottom mine"
      (let [expected '((1)
                       (*))
            field    '((.)
                       (*))]
        (should=  expected (find-mines field)))))

  (context "1x3 field"
    (it "finds multiple mines"
      (let [expected '((* 2 *))
            field    '((* . *))]
        (should=  expected (find-mines field)))))

  (context "3x3 field"
    (it "finds corner mines"
      (let [expected '((* 2 *)
                       (2 4 2)
                       (* 2 *))
            field    '((* . *)
                       (. . .)
                       (* . *))]
        (should=  expected (find-mines field))))

    (it "really finds corner mines"
      (let [expected '((1 1 1)
                       (1 * 1)
                       (1 1 1))
            field    '((. . .)
                       (. * .)
                       (. . .))]
        (should=  expected (find-mines field)))))

  (context "a really big field"
    (it "works on these also"
      (let [expected '((* 2 * 2 * 2 * 2 * 2 * 2 * 2 * 1)
                       (2 4 2 4 2 4 2 4 2 4 2 4 2 4 2 2)
                       (* 2 * 2 * 2 * 2 * 2 * 2 * 2 * 1)
                       (1 2 1 2 1 2 1 2 1 2 1 2 1 2 1 1)
                       (0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0))

            field    '((* . * . * . * . * . * . * . * .)
                       (. . . . . . . . . . . . . . . .)
                       (* . * . * . * . * . * . * . * .)
                       (. . . . . . . . . . . . . . . .)
                       (. . . . . . . . . . . . . . . .))]
        (should=  expected (find-mines field)))))

)
