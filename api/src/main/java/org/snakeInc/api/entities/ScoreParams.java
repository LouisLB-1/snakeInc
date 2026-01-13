package org.snakeInc.api.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class ScoreParams {
    @Min(value = 0, message = "Le score doit être positif")
    private final Integer value;
    @NotNull(message = "Le snake est obligatoire")
    @Pattern(
            regexp = "Python|Anaconda|Boa",
            message = "Le snake doit être Python, Anaconda ou Boa"
    )
    private final String snake;
    private final Integer playerId;

    public ScoreParams(Integer value, String snake, Integer playerId) {
        this.value = value;
        this.snake = snake;
        this.playerId = playerId;
    }
}
