package org.snakeinc.snake.model;

import lombok.Getter;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.exception.SnakeMalnutrition;
import org.snakeinc.snake.ui.GamePanel;

import java.util.Random;

@Getter
public class Game {

    private final Grid grid;
    private final Basket basket;
    private final Snake snake;

    public Game() {
        grid = new Grid();
        basket = new Basket(grid);
        var random = new Random();
        int type = random.nextInt(0,3);
        switch (type) {
            case 0 -> snake = new Anaconda((Fruit, cell) -> basket.removeFruitInCell(Fruit,cell), grid);
            case 1 -> snake = new Python((Fruit, cell) -> basket.removeFruitInCell(Fruit,cell), grid);
            default -> snake = new BoaConstrictor((Fruit, cell) -> basket.removeFruitInCell(Fruit,cell), grid);
        }
        basket.refillIfNeeded(snake, 3);
    }

    public void iterate(Snake.Direction direction) throws OutOfPlayException, SelfCollisionException, SnakeMalnutrition {
        snake.move(direction);
        basket.refillIfNeeded(snake, 3);
    }
}
