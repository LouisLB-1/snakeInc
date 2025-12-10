package org.snakeInc.api;

import org.junit.jupiter.api.Test;
import org.snakeInc.api.entities.Player;
import org.snakeInc.api.entities.PlayerParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testPostPlayer(){
        PlayerParams playerParams = new PlayerParams("Louis", 21);
        Player player = restTemplate.postForEntity("/api/v1/players", playerParams, Player.class).getBody();
        assert(player.getAge() == 21);
        assert(player.getName().equals("Louis"));
    }

    @Test
    public void testGetPlayer(){
        PlayerParams params = new PlayerParams("Louis", 21);
        Player playerpost = restTemplate.postForEntity("/api/v1/players", params, Player.class).getBody();
        Player playerget = restTemplate.getForEntity("/api/v1/players/" +  playerpost.getId(), Player.class).getBody();
        assert(playerget.getAge() == playerpost.getAge());
        assert(playerget.getName().equals(playerpost.getName()));
    }
}
