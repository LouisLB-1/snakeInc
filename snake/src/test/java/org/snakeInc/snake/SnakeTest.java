package org.snakeInc.snake;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.model.Game;
import org.snakeinc.snake.model.Snake;

public class SnakeTest {

    Game game = new Game();

    @Test
    public void snakeEatApplesAfterMove_ReturnsCorrectBodySize() throws OutOfPlayException, SelfCollisionException {
        game.getBasket().addApple(game.getGrid().getTile(5, 4));
        game.getSnake().move(Snake.Direction.U);
        Assertions.assertEquals(2, game.getSnake().getSize());
    }

    @Test
    void snakeMovesUp_ReturnCorrectHead() throws OutOfPlayException, SelfCollisionException {
        game.getSnake().move(Snake.Direction.U);
        Assertions.assertEquals(5, game.getSnake().getHead().getX());
        Assertions.assertEquals(4, game.getSnake().getHead().getY());
    }

    @Test
    void snakeOutOfPLayBehavior() throws OutOfPlayException, SelfCollisionException {
        for (int i = 0; i < GameParams.SNAKE_DEFAULT_Y; i++) {
            game.getSnake().move(Snake.Direction.U);
        }
        Assertions.assertThrows(OutOfPlayException.class, () -> game.getSnake().move(Snake.Direction.U));
    }

    @Test
    void snakeSelfCollisionBehavior() throws OutOfPlayException, SelfCollisionException {
        for (int i = 0; i < 5; i++) {
            game.getBasket().addApple(game.getGrid().getTile(6+i,5));
            game.getSnake().move(Snake.Direction.R);
        }
        game.getSnake().move(Snake.Direction.D);
        game.getSnake().move(Snake.Direction.L);
        Assertions.assertThrows(SelfCollisionException.class, () -> game.getSnake().move(Snake.Direction.U));
    }

}
