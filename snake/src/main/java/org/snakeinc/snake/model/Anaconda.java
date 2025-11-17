package org.snakeinc.snake.model;

public final class Anaconda extends Snake {

    public Anaconda(FruitEatenListener listener, Grid grid) {
        super(listener, grid);
    }

    public void eat(Fruit Fruit, Cell cell) {
        body.addFirst(cell);
        cell.addSnake(this);
        onFruitEatenListener.onFruitEaten(Fruit,cell);
    }
}
