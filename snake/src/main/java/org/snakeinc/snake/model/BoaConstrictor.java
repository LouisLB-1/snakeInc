package org.snakeinc.snake.model;

import org.snakeinc.snake.exception.SnakeMalnutrition;

public final class BoaConstrictor extends Snake{
    public BoaConstrictor(FruitEatenListener listener, Grid grid){
        super(listener, grid);
    }

    public void eat(Fruit Fruit, Cell cell) throws SnakeMalnutrition {
        switch (cell.getFruit()){
            case Apple apple:
                body.getLast().removeSnake();
                body.removeLast();
                onFruitEatenListener.onFruitEaten(Fruit,cell);
                if (apple.getStatus() == org.snakeinc.snake.model.Fruit.Status.N) {
                    this.score+=2;
                }
                else {
                    incrementState();
                }
                if (this.getSize()==0){
                    throw new SnakeMalnutrition();
                }
                break;
            case Lemon lemon:
                onFruitEatenListener.onFruitEaten(Fruit,cell);
                cell.addSnake(this);
                body.addFirst(cell);
                body.getLast().removeSnake();
                body.removeLast();
                if (lemon.getStatus() == org.snakeinc.snake.model.Fruit.Status.N) {
                    this.score+=1;
                }
                else {
                    decrementState();
                }
                break;
        }
    }
}
