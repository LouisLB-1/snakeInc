package org.snakeinc.snake.model;

public class FruitFactory {

    public static Fruit createFruitInCell(Cell cell) {
        Fruit fruit = new Fruit();
        cell.addFruit(fruit);
        return fruit;
    }

}
