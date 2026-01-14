package org.snakeInc.api.controller;

import jakarta.validation.Valid;
import org.snakeInc.api.dto.ScoreStatsDTO;
import org.snakeInc.api.entities.Player;
import org.snakeInc.api.entities.Score;
import org.snakeInc.api.entities.ScoreParams;
import org.snakeInc.api.errors.PlayerNotFoundException;
import org.snakeInc.api.service.PlayerService;
import org.snakeInc.api.service.ScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/scores")
public class ScoreController {
    private final ScoreService scoreService;
    private final PlayerService playerService;

    public ScoreController(ScoreService scoreService, PlayerService playerService) {
        this.scoreService = scoreService;
        this.playerService = playerService;
    }

    @PostMapping
    public Score postScore(@Valid @RequestBody ScoreParams params) {
        Player player = playerService
                .getPlayer(params.getPlayerId())
                .orElseThrow(() -> new PlayerNotFoundException(params.getPlayerId()));
        Score score = new Score(params.getValue(), params.getPlayerId());
        scoreService.addScore(score);
        return score;
    }

    @GetMapping
    public Map<String, List<Score>> getScores(
            @RequestParam(required = false) String snake,
            @RequestParam(required = false) Integer player
    ) {
        List<Score> scores = scoreService.getScores(snake, player);
        Map<String, List<Score>> response = new HashMap<>();
        response.put("scores", scores);
        return response;
    }

    @GetMapping("/stats")
    public ScoreStatsDTO getStats(@RequestParam Integer playerId) {
        return scoreService.getStatsForPlayer(playerId);
    }

    @GetMapping("/best")
    public Score getBestScore(@RequestParam Integer playerId) {
        return scoreService.getBestScore(playerId);
    }

}
