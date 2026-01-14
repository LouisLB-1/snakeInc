package org.snakeInc.api.repository;

import org.snakeInc.api.entities.Score;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreRepo extends CrudRepository<Score, Integer> {
    List<Score> findByPlayerId(Integer playerId);
    List<Score> findBySnakeIgnoreCase(String snake);
    List<Score> findByPlayerIdAndSnakeIgnoreCase(Integer playerId, String snake);

    @Query("SELECT s.snake as snake, MIN(s.value) as min, MAX(s.value) as max, AVG(s.value) as average " + "FROM Score s WHERE s.playerId = :playerId GROUP BY s.snake")
    List<Object[]> findStatsByPlayer(@Param("playerId") Integer playerId);
    Optional<Score> findTopByPlayerIdOrderByValueDesc(Integer playerId);
}
