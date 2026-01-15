package org.snakeInc.api.service;

import lombok.Data;
import org.snakeInc.api.entities.Player;
import org.snakeInc.api.repository.PlayerRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class PlayerService {
    private final PlayerRepo repo;

    public PlayerService(PlayerRepo repo) {
        this.repo = repo;
    }

    public Optional<Player> getPlayerById(int id) {
        return repo.findById(id);
    }
    public Optional<Player> getPlayerByUsername(String username) { return repo.findByName(username); }

    public void addPlayer(Player player) {
        repo.save(player);
    }

    public void delPlayer(int id) {
        repo.deleteById(id);
    }

    public List<Player> getAllPlayers() {
        return (List<Player>) repo.findAll();
    }
}
