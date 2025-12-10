package org.snakeInc.api.controller;
import lombok.ToString;
import org.snakeInc.api.entities.Player;
import org.snakeInc.api.service.PlayerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    private record PlayerParams(String name, Integer age){}
    @PostMapping
    public Player postPlayer(@RequestBody PlayerParams params) {
        Player player = new Player(params.name, params.age);
        playerService.addPlayer(player);
        return player;
    }

    @GetMapping("{id}")
    public Player getPlayer(@PathVariable int id) {
        return playerService.getPlayer(id);
    }
}
