(ns minesweeper.board)

(defn- count-columns [board]
  (count (first board)))

(defn- count-rows [board]
  (count board))

(defn- cell-at [board row column]
  (nth (nth board row) column) )

(defn- mine? [board row column]
  (= '* (cell-at board row column)))

(defn- mine-on-left? [board row column]
  (and
    (> (count-columns board) (inc column))
    (mine? board row (inc column))))

(defn- mine-on-right? [board row column]
  (and
    (<= 0 (dec column))
    (mine? board row (dec column))))

(defn- mine-on-top? [board row column]
  (and
    (<= 0 (dec row))
    (mine? board (dec row) column)))

(defn- mine-on-bottom? [board row column]
  (and
    (> (count-rows board) (inc row))
    (mine? board (inc row) column)))

(defn- count-neighbors [board row column]
  (if (or
        (mine-on-left? board row column)
        (mine-on-right? board row column)
        (mine-on-top? board row column)
        (mine-on-bottom? board row column))
    '1
    '0))

(defn find-mines [board] 
  (let [columns (count-columns board)
        rows (count-rows board)]
    (for [row (range rows)]
      (for [column (range columns)]
        (if (mine? board row column)
          '*
          (count-neighbors board row column)
        )))))
