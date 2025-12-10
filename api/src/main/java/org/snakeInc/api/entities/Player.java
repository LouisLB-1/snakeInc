package org.snakeInc.api.entities;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
public class Player {
    private static Integer idcount = 0;
    private final Integer id;
    private final String name;
    private final Integer age;
    private final String category;
    private final LocalDateTime createdAt;

    public Player(String name, Integer age) {
        this.name = name;
        this.age = age;
        if (age >= 18){
            this.category = "Senior";
        }
        else {
            this.category = "Junior";
        }
        this.createdAt = LocalDateTime.now();
        idcount++;
        this.id = idcount;
    }
}
