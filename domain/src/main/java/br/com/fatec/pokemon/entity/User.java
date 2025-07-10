package br.com.fatec.pokemon.entity;

import br.com.fatec.pokemon.entity.enumerable.UserRole;

import java.io.Serializable;
import java.util.List;

public record User(
        String id,
        String username,
        String password,
        List<UserRole> rules
) implements Serializable {
}