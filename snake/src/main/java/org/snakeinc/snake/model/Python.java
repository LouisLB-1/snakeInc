package org.snakeinc.snake.model;

public final class Python extends Snake{
    public Python(FruitEatenListener listener, Grid grid){
        super(listener, grid);
    }

    public void eat(Fruit Fruit, Cell cell) {
        onFruitEatenListener.onFruitEaten(Fruit,cell);
    }
}
