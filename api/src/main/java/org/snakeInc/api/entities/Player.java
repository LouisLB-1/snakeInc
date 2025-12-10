package org.snakeInc.api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@Entity
public class Player {
    private static Integer idcount = 0;
    @Id
    protected final Integer id;
    protected final String name;
    protected final Integer age;
    protected final String category;
    protected final LocalDateTime createdAt;

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

    public Player() {
        this.name = null;
        this.age = null;
        this.category = null;
        this.createdAt = LocalDateTime.now();
        idcount++;
        this.id = idcount;
    }
}
