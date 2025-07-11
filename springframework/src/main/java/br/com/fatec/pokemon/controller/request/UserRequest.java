package br.com.fatec.pokemon.controller.request;

import br.com.fatec.pokemon.entity.enumerable.UserRole;

import java.util.List;

public record UserRequest(
        String username,
        String password,
        List<UserRole> roles
) {
}
