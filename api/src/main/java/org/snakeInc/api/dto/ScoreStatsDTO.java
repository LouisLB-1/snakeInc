package org.snakeInc.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ScoreStatsDTO {
    private Integer playerId;
    private List<SnakeStats> stats;

    @Getter
    @AllArgsConstructor
    public static class SnakeStats {
        private String snake;
        private Integer min;
        private Integer max;
        private Double average;
    }
}
