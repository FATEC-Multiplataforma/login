package br.com.fatec.pokemon.controller;

import br.com.fatec.pokemon.controller.adapter.UserControllerAdapter;
import br.com.fatec.pokemon.controller.dto.request.AuthRequest;
import br.com.fatec.pokemon.controller.dto.request.UserRequest;
import br.com.fatec.pokemon.controller.dto.response.AuthResponse;
import br.com.fatec.pokemon.entity.Token;
import br.com.fatec.pokemon.entity.User;
import br.com.fatec.pokemon.repository.UserRepository;
import br.com.fatec.pokemon.security.TokenSecurity;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fretec")
public class UserController {
    private final UserRepository repository;
    private final TokenSecurity tokenSecurity;

    public UserController(UserRepository repository, TokenSecurity tokenSecurity) {
        this.repository = repository;
        this.tokenSecurity = tokenSecurity;
    }

    @PostMapping("/auth/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse login(@RequestBody AuthRequest request) {
        Token token = tokenSecurity.gerarToken(UserControllerAdapter.cast(request));
        return UserControllerAdapter.cast(token);
    }

    @PostMapping("/auth/save")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody UserRequest request) {
        User user = UserControllerAdapter.cast(request);
        repository.save(user);
    }

    @GetMapping("/auth/forget/{username}")
    @ResponseStatus(HttpStatus.OK)
    public String forget(@PathVariable("username") String username) {
        return "Olá " + username + " enviamos a sua senha para o seu email.";
    }
}