package org.snakeinc.snake.model;

import lombok.Getter;

import java.util.Random;

public final class Lemon extends Fruit{
    private final Status lime;
    public Lemon() {
        var random = new Random();
        int type = random.nextInt(0,2);
        if (type == 0){
            this.lime = Status.Y;
        }
        else{
            this.lime = Status.N;
        }
    }
    public Status getStatus() {return(this.lime);}
}
