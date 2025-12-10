package org.snakeInc.api.repository;

import org.snakeInc.api.entities.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepo extends CrudRepository<Player, Integer> {
}
