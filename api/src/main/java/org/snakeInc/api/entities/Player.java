package org.snakeInc.api.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected final String name;
    protected final Integer age;
    protected final String category;
    protected final LocalDateTime createdAt;
    @OneToMany(mappedBy = "playerId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Score> scores = new ArrayList<>();

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
    }

    public Player() {
        this.name = null;
        this.age = null;
        this.category = null;
        this.createdAt = LocalDateTime.now();
    }

    public void addScore(Score score) {
        scores.add(score);
    }
}
