package org.snakeinc.snake.model;

import lombok.Getter;

@Getter
public abstract sealed class Fruit permits Apple, Lemon{

    public Fruit() {
    }

}
