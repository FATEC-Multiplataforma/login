package br.com.fatec.pokemon.entity;

import br.com.fatec.pokemon.entity.enumerable.UserRole;

import java.util.List;

public record User(
        String id,
        String username,
        String password,
        List<UserRole> roles
) {
}
