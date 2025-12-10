package org.snakeInc.api.controller;
import lombok.ToString;
import org.snakeInc.api.entities.Player;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/players")
public class PlayerController {
    private record PlayerParams(String name, Integer age){}
    @PostMapping
    public Player postPlayer(@RequestBody PlayerParams params) {
        Player player = new Player(params.name, params.age);
        return player;
    }
}
