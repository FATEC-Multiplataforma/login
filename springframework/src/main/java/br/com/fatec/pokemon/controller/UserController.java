package br.com.fatec.pokemon.controller;

import br.com.fatec.pokemon.controller.adapter.UserControllerAdapter;
import br.com.fatec.pokemon.controller.request.UserRequest;
import br.com.fatec.pokemon.controller.response.AuthResponse;
import br.com.fatec.pokemon.entity.Token;
import br.com.fatec.pokemon.repository.UserRepository;
import br.com.fatec.pokemon.security.TokenSecurity;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fretec/v1")
public class UserController {
    private final UserRepository repository;
    private final TokenSecurity tokenSecurity;

    public UserController(
            UserRepository repository,
            TokenSecurity tokenSecurity) {
        this.repository = repository;
        this.tokenSecurity = tokenSecurity;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/auth/login")
    public AuthResponse login(@RequestBody UserRequest request) {
        Token token = tokenSecurity.gerarToken(UserControllerAdapter.cast(request));
        return new AuthResponse(token.value());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/auth/save")
    public void save(@RequestBody UserRequest request) {
        repository.save(UserControllerAdapter.cast(request));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/auth/forget/{username}")
    public String forgetPassword(@PathVariable("username") String username) {
        return "Olá " + username + " enviamos sua senha para o seu email";
    }

}
