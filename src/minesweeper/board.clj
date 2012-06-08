(ns minesweeper.board)

(defn mine? [space]
  (= \* space))

(defn count-neighbors [space board]
  (if (and 
        (>= 0 (dec space))
        (mine? (get board (dec space))))
    1
    0))

(defn create-analyzer [board]
  #(if (mine? (get board %))
    \*
    (count-neighbors % board)))

(defn find-mines [board] 
  (apply str 
    (map 
      (create-analyzer board) 
      (range 0 (count board)))))
