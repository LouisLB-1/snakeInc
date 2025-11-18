package org.snakeinc.snake.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Data
@EqualsAndHashCode
public class Cell {

    @Getter
    private int x;

    @Getter
    private int y;

    Snake snake;
    Fruit Fruit;

    protected Cell(int x, int y) {
        setX(x);
        setY(y);
    }

    public void addFruit(Fruit Fruit) {
        this.Fruit = Fruit;
    }

    public void addSnake(Snake snake) {
        this.snake = snake;
    }

    public void removeSnake() {
        this.snake = null;
    }

    public void removeFruit() {
        this.Fruit = null;
    }

    public boolean containsASnake() {
        return this.snake != null;
    }
    
    public boolean containsAFruit() {
        return this.Fruit != null;
    }

}
