package org.snakeInc.api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.LocalDateTime;
@Getter
@Entity
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected final Integer value;
    protected final String snake;
    protected final LocalDateTime playedAt;
    protected final Integer playerId;

    public Score(Integer value, String snake, Integer playerId){
        this.value = value;
        this.snake = snake;
        this.playedAt = LocalDateTime.now();
        this.playerId = playerId;
    }

    public Score(){
        this.value = null;
        this.snake = null;
        this.playedAt = LocalDateTime.now();
        this.playerId = null;
    }
}
