package org.snakeInc.api.controller;
import jakarta.validation.Valid;
import org.snakeInc.api.entities.Player;
import org.snakeInc.api.entities.PlayerParams;
import org.snakeInc.api.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public Player postPlayer(@Valid @RequestBody PlayerParams params) {
        Player player = new Player(params.getName(), params.getAge());
        playerService.addPlayer(player);
        return player;
    }

    @GetMapping("{id}")
    public Optional<Player> getPlayer(@PathVariable int id) {
        return playerService.getPlayer(id);
    }

    @DeleteMapping("{id}")
    public void delPlayer(@PathVariable int id) {
        playerService.delPlayer(id);
    }
}
