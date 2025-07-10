package br.com.fatec.pokemon;

import br.com.fatec.pokemon.entity.User;
import br.com.fatec.pokemon.entity.enumerable.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@EnableWebSecurity
@EnableMethodSecurity
@SpringBootApplication
public class LoginApplication implements CommandLineRunner {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public LoginApplication(PasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User admin = new User(
                "1",
                "admin",
                "123456",
                List.of(UserRole.USER));
        userRepository.save(admin);
    }
}