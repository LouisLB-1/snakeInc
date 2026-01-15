package org.snakeInc.api.repository;

import org.snakeInc.api.entities.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepo extends CrudRepository<Player, Integer> {
    Optional<Player> findByName(String name);
}
