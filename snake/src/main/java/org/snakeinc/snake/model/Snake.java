package org.snakeinc.snake.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
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
    protected SnakeState state;
    protected final List<Cell> observers = new ArrayList<>();
    @Setter
    protected Direction direction;

    public enum Direction { U, D, R, L}

    public Snake(FruitEatenListener listener, Grid grid) {
        this.state = new HealthyState();
        this.score = 0;
        this.body = new ArrayList<>();
        this.onFruitEatenListener = listener;
        this.direction = Direction.R;
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
        state.smove(this, direction);
        notifyObservers();
    }

    public void incrementState() {
        switch (state){
            case HealthyState healthyState:
                this.state = new PoisonedState();
                if (direction == Direction.U) {
                    direction = Direction.D;
                }
                else if (direction == Direction.D) {
                    direction = Direction.U;
                }
                break;
            case PoisonedState poisonedState:
                this.state = new DamagedState();
                if (direction == Direction.U) {
                    direction = Direction.D;
                }
                else if (direction == Direction.D) {
                    direction = Direction.U;
                }
                else if (direction == Direction.R) {
                    direction = Direction.L;
                }
                else {
                    direction = Direction.R;
                }
                break;
            default:
                break;
        }
    }

    public void decrementState() {
        if (state instanceof PoisonedState) {
            this.state = new HealthyState();
        }
    }

    public void attach(Cell observer) {
        observers.add(observer);
    }

    public void detach(Cell observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        List<Cell> copy = new ArrayList<>(observers);
        for (Cell obs : copy) {
            obs.update(this);
        }
    }

}
