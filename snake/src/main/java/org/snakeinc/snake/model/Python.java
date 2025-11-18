package org.snakeinc.snake.model;

import org.snakeinc.snake.exception.SnakeMalnutrition;

public final class Python extends Snake{
    public Python(FruitEatenListener listener, Grid grid){
        super(listener, grid);
    }

    public void eat(Fruit Fruit, Cell cell) throws SnakeMalnutrition{
        switch (cell.getFruit()){
            case Apple apple:
                onFruitEatenListener.onFruitEaten(Fruit,cell);
                cell.addSnake(this);
                body.addFirst(cell);
                body.getLast().removeSnake();
                body.removeLast();
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
