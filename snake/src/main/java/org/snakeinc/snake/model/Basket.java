package org.snakeinc.snake.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Data;
import lombok.Getter;
import org.snakeinc.snake.GameParams;

@Data
public class Basket {
    @Getter
    protected Grid grid;
    protected List<Fruit> Fruits;
    private FruitStrategy strategy;

    public Basket(Grid grid) {
        Fruits = new ArrayList<>();
        this.grid = grid;
        Random var = new Random();
        int strat = var.nextInt(3);
        switch (strat){
            case (0) -> this.strategy = new RandomStrategy();
            case (1) -> this.strategy = new EasyStrategy();
            case (2) -> this.strategy = new HardStrategy();
        }
    }

    public void addFruit(Snake snake, Cell cell) {
        strategy.StratAddFruit(snake, this, cell);
    }

    public void removeFruitInCell(Fruit Fruit, Cell cell) {
        cell.removeFruit();
        Fruits.remove(Fruit);
    }

    public boolean isEmpty() {
        return Fruits.isEmpty();
    }

    private void refill(Snake snake, int nFruits) {
        for (int i = 0; i < nFruits; i++) {
            addFruit(snake, null);
        }
    }

    public void refillIfNeeded(Snake snake, int nFruits) {
        int missingFruit = nFruits - Fruits.size();
        if (missingFruit > 0) {
            refill(snake, missingFruit);
        }
    }

}
