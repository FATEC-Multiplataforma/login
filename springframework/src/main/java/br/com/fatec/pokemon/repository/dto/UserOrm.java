package br.com.fatec.pokemon.repository.dto;

import br.com.fatec.pokemon.entity.enumerable.UserRole;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "user")
public record UserOrm(
        String id,
        String username,
        String password,
        List<UserRole> rules
) {
}