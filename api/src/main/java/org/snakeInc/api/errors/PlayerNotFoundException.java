package org.snakeInc.api.errors;

public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException(Integer playerId) {
        super("Le player avec l'id " + playerId + " n'existe pas");
    }
}

