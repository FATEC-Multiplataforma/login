package br.com.fatec.pokemon.repository.adapter;

import br.com.fatec.pokemon.entity.User;
import br.com.fatec.pokemon.repository.dto.UserOrm;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserRepositoryImplAdapter {
    private UserRepositoryImplAdapter() {
    }

    public static User cast(UserOrm orm, PasswordEncoder encoder) {
        return new User(
                orm.id(),
                orm.username(),
                encoder.encode(orm.password()),
                orm.rules());
    }

    public static UserOrm cast(User user) {
        return new UserOrm(
                user.id(),
                user.username(),
                user.password(),
                user.rules());
    }
}