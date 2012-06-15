(ns minesweeper.board)

(defn- count-columns [board]
  (count (first board)))

(defn- count-rows [board]
  (count board))

(defn- cell-at [board row column]
  (nth (nth board row) column) )

(defn- mine? [board row column]
  (and 
    (<= 0 row)
    (<= 0 column)
    (> (count-rows board) row)
    (> (count-columns board) column)
    (= '* (cell-at board row column))))

(defn- count-neighbors [board row column]
  (count
    (filter #(= true %)
      [
        (mine? board row (dec column))
        (mine? board row (inc column))
        (mine? board (dec row) column)
        (mine? board (inc row) column)
        (mine? board (inc row) (inc column))
        (mine? board (dec row) (inc column))
        (mine? board (inc row) (dec column))
        (mine? board (dec row) (dec column))
      ])))

(defn find-mines [board] 
  (let [columns (count-columns board)
        rows (count-rows board)]
    (for [row (range rows)]
      (for [column (range columns)]
        (if (mine? board row column)
          '*
          (count-neighbors board row column)
        )))))
