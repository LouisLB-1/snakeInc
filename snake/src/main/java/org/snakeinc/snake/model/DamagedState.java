package org.snakeinc.snake.model;

import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.exception.SnakeMalnutrition;

public class DamagedState implements SnakeState {
    public void smove(Snake snake, Snake.Direction direction) throws OutOfPlayException, SelfCollisionException, SnakeMalnutrition {
        int x = snake.getHead().getX();
        int y = snake.getHead().getY();
        switch (direction) {
            case U:
                y++;
                break;
            case D:
                y--;
                break;
            case L:
                x++;
                break;
            case R:
                x--;
                break;
        }
        Cell newHead = snake.getGrid().getTile(x, y);
        if (newHead == null) {
            throw new OutOfPlayException();
        }
        if (newHead.containsASnake()) {
            throw new SelfCollisionException();
        }

        // Eat Fruit :
        if (newHead.containsAFruit()) {
            snake.eat(newHead.getFruit(), newHead);
            return;
        }

        // The snake did not eat :
        newHead.addSnake(snake);
        snake.getBody().addFirst(newHead);

        snake.getBody().getLast().removeSnake();
        snake.getBody().removeLast();
    }
}
