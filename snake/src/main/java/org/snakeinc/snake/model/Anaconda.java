package org.snakeinc.snake.model;

import org.snakeinc.snake.exception.SnakeMalnutrition;

public final class Anaconda extends Snake {

    public Anaconda(FruitEatenListener listener, Grid grid) {
        super(listener, grid);
    }

    public void eat(Fruit Fruit, Cell cell) throws SnakeMalnutrition{
        switch (cell.getFruit()){
            case Apple apple:
                body.addFirst(cell);
                cell.addSnake(this);
                onFruitEatenListener.onFruitEaten(Fruit,cell);
                break;
            case Lemon lemon:
                body.getLast().removeSnake();
                body.removeLast();
                if (this.getSize()==0){
                    throw new SnakeMalnutrition();
                }
                body.getLast().removeSnake();
                body.removeLast();
                if (this.getSize()==0){
                    throw new SnakeMalnutrition();
                }
                onFruitEatenListener.onFruitEaten(Fruit,cell);
                break;
        }
    }
}
