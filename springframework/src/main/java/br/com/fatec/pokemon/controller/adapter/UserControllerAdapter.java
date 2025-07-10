package br.com.fatec.pokemon.controller.adapter;

import br.com.fatec.pokemon.controller.dto.request.AuthRequest;
import br.com.fatec.pokemon.controller.dto.request.UserRequest;
import br.com.fatec.pokemon.controller.dto.response.AuthResponse;
import br.com.fatec.pokemon.entity.Token;
import br.com.fatec.pokemon.entity.User;

import java.util.List;
import java.util.UUID;

public class UserControllerAdapter {
    private UserControllerAdapter() {
    }

    public static User cast(AuthRequest request) {
        return new User(
                UUID.randomUUID().toString(),
                request.username(),
                request.password(),
                List.of());
    }

    public static User cast(UserRequest request) {
        return new User(
                UUID.randomUUID().toString(),
                request.username(),
                request.password(),
                request.rules());
    }

    public static AuthResponse cast(Token token) {
        return new AuthResponse(token.value());
    }

}
