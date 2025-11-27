package org.snakeinc.snake.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.snakeinc.snake.GameParams;

import java.util.Random;


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

    public void update(Snake snake) {
        if (this.getFruit() == null) return;

        if (Math.abs(this.getX() - snake.getHead().getX()) + Math.abs(this.getY() - snake.getHead().getY()) <= 2) {
            moveFruitAway(snake);
        }
    }

    public void moveFruitAway(Snake snake){
        Random var = new Random();
        if (var.nextInt(0,100) <= 20) {
            Grid grid = snake.getGrid();
            Cell newCell;
            do {
                newCell = grid.getTile(var.nextInt(GameParams.TILES_X), var.nextInt(GameParams.TILES_Y));
            } while (newCell.containsASnake() || newCell.containsAFruit());
            newCell.addFruit(this.getFruit());
            this.removeFruit();
            snake.detach(this);
            snake.attach(newCell);
        }
    }
}
