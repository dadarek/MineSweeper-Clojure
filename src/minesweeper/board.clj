(ns minesweeper.board)

(defn create-validator [board]
  #(and (<= 0 %) (< % (count board))))

(defn mine? [space]
  (= '* space))

(defn create-analyzer [board counter]
  #(if (mine? (nth board %))
    \*
    (counter %)))

(defn create-neighbor-counter [board validator]
  (fn [space]
   (if (or
         (and
           (validator (dec space))
           (mine? (nth board (dec space))))
         (and
           (validator (inc space))
           (mine? (nth board (inc space)))))
    1
    0)))

(defn find-mines [board] 
  (let [validator (create-validator board)
        counter (create-neighbor-counter board validator)
        analyzer (create-analyzer board counter)]
  (apply str 
    (map analyzer (range 0 (count board))))))
