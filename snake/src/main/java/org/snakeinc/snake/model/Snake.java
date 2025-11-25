package org.snakeinc.snake.model;

import java.util.ArrayList;

import lombok.Getter;
import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.exception.SnakeMalnutrition;
@Getter
public abstract sealed class Snake permits Anaconda, Python, BoaConstrictor {

    protected final ArrayList<Cell> body;
    protected final FruitEatenListener onFruitEatenListener;
    private final Grid grid;
    protected Integer score;

    public enum Direction { U, D, R, L}

    public Snake(FruitEatenListener listener, Grid grid) {
        this.score = 0;
        this.body = new ArrayList<>();
        this.onFruitEatenListener = listener;
        this.grid = grid;
        Cell head = grid.getTile(GameParams.SNAKE_DEFAULT_X, GameParams.SNAKE_DEFAULT_Y);
        Cell mid1 = grid.getTile(GameParams.SNAKE_DEFAULT_X-1, GameParams.SNAKE_DEFAULT_Y);
        Cell mid2 = grid.getTile(GameParams.SNAKE_DEFAULT_X-2, GameParams.SNAKE_DEFAULT_Y);
        Cell mid3 = grid.getTile(GameParams.SNAKE_DEFAULT_X-3, GameParams.SNAKE_DEFAULT_Y);
        Cell tail = grid.getTile(GameParams.SNAKE_DEFAULT_X-4, GameParams.SNAKE_DEFAULT_Y);
        head.addSnake(this);
        mid1.addSnake(this);
        mid2.addSnake(this);
        mid3.addSnake(this);
        tail.addSnake(this);
        body.add(head);
        body.add(mid1);
        body.add(mid2);
        body.add(mid3);
        body.add(tail);
    }

    public int getSize() {
        return body.size();
    }

    public Cell getHead() {
        return body.getFirst();
    }

    public void eat(Fruit Fruit, Cell cell) throws SnakeMalnutrition {}

    public void move(Direction direction) throws OutOfPlayException, SelfCollisionException, SnakeMalnutrition {
        int x = getHead().getX();
        int y = getHead().getY();
        switch (direction) {
            case U:
                y--;
                break;
            case D:
                y++;
                break;
            case L:
                x--;
                break;
            case R:
                x++;
                break;
        }
        Cell newHead = grid.getTile(x, y);
        if (newHead == null) {
            throw new OutOfPlayException();
        }
        if (newHead.containsASnake()) {
            throw new SelfCollisionException();
        }

        // Eat Fruit :
        if (newHead.containsAFruit()) {
            this.eat(newHead.getFruit(), newHead);
            return;
        }

        // The snake did not eat :
        newHead.addSnake(this);
        body.addFirst(newHead);

        body.getLast().removeSnake();
        body.removeLast();

    }

}
