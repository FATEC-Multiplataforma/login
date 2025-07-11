package br.com.fatec.pokemon.repository.adapter;

import br.com.fatec.pokemon.entity.User;
import br.com.fatec.pokemon.repository.orm.UserOrm;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserRepositoryImplAdapter {
    private UserRepositoryImplAdapter() {
    }

    public static User cast(UserOrm orm, PasswordEncoder passwordEncoder) {
        return new User(
                orm.id(),
                orm.username(),
                passwordEncoder.encode(orm.password()),
                orm.roles());
    }

    public static UserOrm cast(User orm) {
        return new UserOrm(
                orm.id(),
                orm.username(),
                orm.password(),
                orm.roles());
    }

}
