package org.snakeInc.api.service;

import lombok.Data;
import org.snakeInc.api.dto.ScoreStatsDTO;
import org.snakeInc.api.entities.Score;
import org.snakeInc.api.repository.ScoreRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class ScoreService {
    private final ScoreRepo repo;

    public ScoreService(ScoreRepo repo) {this.repo = repo;}

    public void addScore(Score score) { repo.save(score);}

    public List<Score> getScores(String snake, Integer playerId) {
        List<Score> scores;

        if (snake != null && playerId != null) {
            scores = repo.findByPlayerIdAndSnakeIgnoreCase(playerId, snake);
        } else if (snake != null) {
            scores = repo.findBySnakeIgnoreCase(snake);
        } else if (playerId != null) {
            scores = repo.findByPlayerId(playerId);
        } else {
            scores = (List<Score>) repo.findAll();
        }

        return new ArrayList<>(scores);
    }

    public ScoreStatsDTO getStatsForPlayer(Integer playerId) {
        List<Object[]> results = repo.findStatsByPlayer(playerId);

        List<ScoreStatsDTO.SnakeStats> stats = results.stream().map(row ->
                new ScoreStatsDTO.SnakeStats(
                        (String) row[0],
                        ((Number) row[1]).intValue(),
                        ((Number) row[2]).intValue(),
                        ((Number) row[3]).doubleValue()
                )
        ).collect(Collectors.toList());

        return new ScoreStatsDTO(playerId, stats);
    }

    public Score getBestScore(Integer playerId) {
        return repo.findTopByPlayerIdOrderByValueDesc(playerId)
                .orElse(null);
    }


}
