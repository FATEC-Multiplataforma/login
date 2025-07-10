package br.com.fatec.pokemon.security;

import br.com.fatec.pokemon.entity.User;
import br.com.fatec.pokemon.repository.UserRepository;
import br.com.fatec.pokemon.security.dto.AuthUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsSecurity implements UserDetailsService {
    private final UserRepository repository;

    public UserDetailsSecurity(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUser(username);
        return new AuthUserDetails(user);
    }
}