package org.snakeinc.snake.model;

import org.snakeinc.snake.GameParams;

import java.util.Random;

public class EasyStrategy implements FruitStrategy {
    public void StratAddFruit(Snake snake, Basket basket, Cell cell) {
        if (cell == null) {
            var random = new Random();
            Cell head = snake.getHead();
            int hx = head.getX();
            int hy = head.getY();
            Cell candidate = null;

            while (candidate == null || candidate.containsASnake() || candidate.containsAFruit()) {
                int x = random.nextInt(Math.max(0, hx - 3), Math.min(GameParams.TILES_X, hx + 4));
                int y = random.nextInt(Math.max(0, hy - 3), Math.min(GameParams.TILES_Y, hy + 4));
                candidate = basket.getGrid().getTile(x, y);
            }
            cell = candidate;
        }

        var random = new Random();
        int type;
        if (random.nextInt(0,100) <=30 ){
            type = 0;
        }
        else{
            type = 1;
        }
        Fruit fruit = FruitFactory.createFruitInCell(cell, type);
        basket.getFruits().add(fruit);
    }
}
