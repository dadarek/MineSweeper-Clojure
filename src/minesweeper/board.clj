(ns minesweeper.board)

(defn count-trues [values]
  (count (filter #(= % true) values)))

(defn create-validator [board]
  #(and (<= 0 %) (< % (count board))))

(defn mine? [space]
  (= '* space))

(defn create-analyzer [board counter]
  #(if (mine? (nth board %))
    '*
    (counter %)))

(defn create-neighbor-counter [board validator]
  (fn [space]
    (count-trues
      (map #(and
             (validator %)
             (mine? (nth board %)))
           [(dec space) (inc space)]))))


(defn find-mines [board] 
  (let [validator (create-validator board)
        counter (create-neighbor-counter board validator)
        analyzer (create-analyzer board counter)]
    (map analyzer (range 0 (count board)))))
