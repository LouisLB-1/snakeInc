package org.snakeInc.api.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PlayerParams {
    @NotBlank(message = "Tu dois entrer un nom")
    private final String name;
    @Min(value = 13, message = "Il faut avoir 13 ans au moins, retourne sur Adibou")
    private final int age;

    public PlayerParams(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
