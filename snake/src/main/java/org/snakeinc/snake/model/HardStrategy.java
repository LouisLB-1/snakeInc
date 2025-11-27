package org.snakeinc.snake.model;

import org.snakeinc.snake.GameParams;

import java.util.Random;

public class HardStrategy implements FruitStrategy {
    public void StratAddFruit(Snake snake, Basket basket, Cell cell) {
        if (cell == null) {
            var random = new Random();
            Cell candidate = null;

            while (candidate == null || candidate.containsASnake() || candidate.containsAFruit()) {
                int x, y;
                int side = random.nextInt(4);

                switch (side) {
                    case 0 -> {
                        x = random.nextInt(0, 2);
                        y = random.nextInt(0, GameParams.TILES_Y);
                    }
                    case 1 -> {
                        x = random.nextInt(GameParams.TILES_X - 2, GameParams.TILES_X);
                        y = random.nextInt(0, GameParams.TILES_Y);
                    }
                    case 2 -> {
                        y = random.nextInt(0, 2);
                        x = random.nextInt(0, GameParams.TILES_X);
                    }
                    default -> {
                        y = random.nextInt(GameParams.TILES_Y - 2, GameParams.TILES_Y);
                        x = random.nextInt(0, GameParams.TILES_X);
                    }
                }
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
