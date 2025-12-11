package org.snakeInc.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.snakeInc.api.entities.Player;
import org.snakeInc.api.entities.PlayerParams;
import org.snakeInc.api.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PlayerControllerIntegrationTest {

    @LocalServerPort
    private Integer port;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @Autowired
    private PlayerService playerService;

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        playerService.getAllPlayers().forEach(p -> playerService.delPlayer(p.getId())); // Nettoyer la DB avant chaque test
    }

    @Test
    void shouldPostAndGetPlayer() {
        PlayerParams playerParams = new PlayerParams("Louis", 21);

        Player createdPlayer =
                given()
                        .contentType(ContentType.JSON)
                        .body(playerParams)
                        .when()
                        .post("/api/v1/players")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Player.class);

        // Vérifications après POST
        assert createdPlayer != null;
        assert createdPlayer.getName().equals("Louis");
        assert createdPlayer.getAge() == 21;

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/players/" + createdPlayer.getId())
                .then()
                .statusCode(200)
                .body("name", equalTo("Louis"))
                .body("age", equalTo(21));
    }
}
