package br.com.fatec.pokemon.repository.client;

import br.com.fatec.pokemon.repository.dto.UserOrm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryWithMongodb extends MongoRepository<UserOrm, String> {
    Optional<UserOrm> findByUsername(String username);
}
