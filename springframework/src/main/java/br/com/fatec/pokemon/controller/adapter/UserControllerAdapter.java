package br.com.fatec.pokemon.controller.adapter;

import br.com.fatec.pokemon.controller.request.UserRequest;
import br.com.fatec.pokemon.entity.User;

import java.util.UUID;

public class UserControllerAdapter {
    private UserControllerAdapter() {
    }

    public static User cast(UserRequest request) {
        return new User(
                UUID.randomUUID().toString(),
                request.username(),
                request.password(),
                request.roles());
    }

}
