package br.com.fatec.pokemon.repository.orm;

import br.com.fatec.pokemon.entity.enumerable.UserRole;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "user")
public record UserOrm(
        @Id
        String id,
        @Indexed
        String username,
        String password,
        List<UserRole> roles
) {
}
