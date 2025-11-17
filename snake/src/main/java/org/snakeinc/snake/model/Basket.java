package org.snakeinc.snake.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Data;
import org.snakeinc.snake.GameParams;

@Data
public class Basket {

    private Grid grid;
    private List<Fruit> Fruits;

    public Basket(Grid grid) {
        Fruits = new ArrayList<>();
        this.grid = grid;
    }

    public void addFruit(Cell cell) {
        if (cell == null) {
            var random = new Random();
            cell = grid.getTile(random.nextInt(0, GameParams.TILES_X), random.nextInt(0, GameParams.TILES_Y));
            while (cell.containsASnake() | cell.containsAnFruit()){
                cell = grid.getTile(random.nextInt(0, GameParams.TILES_X), random.nextInt(0, GameParams.TILES_Y));
            }
        }
        var random = new Random();
        int type = random.nextInt(0,2);
        Fruit fruit = FruitFactory.createFruitInCell(cell, type);
        Fruits.add(fruit);
    }

    public void removeFruitInCell(Fruit Fruit, Cell cell) {
        cell.removeFruit();
        Fruits.remove(Fruit);
    }

    public boolean isEmpty() {
        return Fruits.isEmpty();
    }

    private void refill(int nFruits) {
        for (int i = 0; i < nFruits; i++) {
            addFruit(null);
        }
    }

    public void refillIfNeeded(int nFruits) {
        int missingFruit = nFruits - Fruits.size();
        if (missingFruit > 0) {
            refill(missingFruit);
        }
    }

}
