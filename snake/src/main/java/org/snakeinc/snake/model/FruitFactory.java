package org.snakeinc.snake.model;

public class FruitFactory {

    public static Fruit createFruitInCell(Cell cell, int type) {
        if (type == 1){
            Fruit fruit = new Apple();
            cell.addFruit(fruit);
            return fruit;
        }
        else{
            Fruit fruit = new Lemon();
            cell.addFruit(fruit);
            return fruit;
        }
    }

}
