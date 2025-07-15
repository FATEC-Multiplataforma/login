package br.com.fatec.pokemon;

import br.com.fatec.pokemon.entity.User;
import br.com.fatec.pokemon.entity.enumerable.UserRole;
import br.com.fatec.pokemon.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.List;

@EnableWebSecurity
@EnableMethodSecurity
@SpringBootApplication
public class LoginApplication implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(LoginApplication.class);

    private final UserRepository repository;

    public LoginApplication(UserRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.save(new User(
                "1",
                "admin",
                "123456",
                List.of(UserRole.ADMIN)));

        LOG.info("APLICACÃO DE LOGIN INICIOU COM SUCESSO");
        LOG.error("ACONTECEU UM ERRO NA API DE LOGIN");
    }
}