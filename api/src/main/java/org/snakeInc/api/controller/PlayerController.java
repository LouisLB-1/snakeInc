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
        Player player = new Player(params.getName());
        playerService.addPlayer(player);
        return player;
    }

    @GetMapping("/id/{id}")
    public Optional<Player> getPlayerByUsername(@PathVariable int id) {
        return playerService.getPlayerById(id);
    }

    @GetMapping("/username/{username}")
    public Optional<Player> getPlayerById(@PathVariable String username) {
        return playerService.getPlayerByUsername(username);
    }

    @DeleteMapping("{id}")
    public void delPlayer(@PathVariable int id) {
        playerService.delPlayer(id);
    }
}
