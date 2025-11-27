package org.snakeinc.snake.model;

import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.exception.SnakeMalnutrition;

public interface SnakeState {
    void smove(Snake snake, Snake.Direction direction) throws OutOfPlayException, SelfCollisionException, SnakeMalnutrition;
}
