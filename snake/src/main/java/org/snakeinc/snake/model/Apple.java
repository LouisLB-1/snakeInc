package org.snakeinc.snake.model;

import lombok.Getter;

import java.util.Random;

public final class Apple extends Fruit {
    private final Status poisoned;
    public Apple(){
        var random = new Random();
        int type = random.nextInt(0,2);
        if (type == 0){
            this.poisoned = Status.Y;
        }
        else{
            this.poisoned = Status.N;
        }
    }
    public Status getStatus() {return(this.poisoned);}

}
