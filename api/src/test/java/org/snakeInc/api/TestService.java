package org.snakeInc.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.snakeInc.api.entities.Player;
import org.snakeInc.api.service.PlayerService;

public class TestService {
    @InjectMocks
    PlayerService playerService;
    @BeforeEach
    public void initMock(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testAddPlayer() {
        Player player = new Player("Louis", 21);
        playerService.addPlayer(player);
        assert(playerService.getPlayers().get(player.getId()) == player);
    }

    @Test
    public void testGetPlayer(){
        Player player = new Player("Louis",21);
        playerService.addPlayer(player);
        assert(playerService.getPlayer(player.getId()) == player);
        }
    }
