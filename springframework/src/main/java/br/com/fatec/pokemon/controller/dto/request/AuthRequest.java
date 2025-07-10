package br.com.fatec.pokemon.controller.dto.request;

public record AuthRequest(
        String username,
        String password
) {
}
