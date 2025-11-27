package org.snakeinc.snake.model;

import lombok.Getter;

@Getter
public abstract sealed class Fruit permits Apple, Lemon {
    public enum Status { Y, N};
    public Fruit() { }

    public abstract Status getStatus();
}
