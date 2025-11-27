package org.snakeinc.snake.model;

import org.snakeinc.snake.GameParams;

import java.util.Random;

public class RandomStrategy implements FruitStrategy {
    public void StratAddFruit(Snake snake, Basket basket, Cell cell) {
        if (cell == null) {
            var random = new Random();
            cell = basket.getGrid().getTile(random.nextInt(0, GameParams.TILES_X), random.nextInt(0, GameParams.TILES_Y));
            while (cell.containsASnake() | cell.containsAFruit()){
                cell = basket.getGrid().getTile(random.nextInt(0, GameParams.TILES_X), random.nextInt(0, GameParams.TILES_Y));
            }
        }
        snake.attach(cell);

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
