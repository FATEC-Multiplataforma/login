package br.com.fatec.pokemon.repository;

import br.com.fatec.pokemon.entity.User;
import br.com.fatec.pokemon.entity.enumerable.UserRole;
import br.com.fatec.pokemon.repository.adapter.UserRepositoryImplAdapter;
import br.com.fatec.pokemon.repository.client.UserRepositoryWithMongodb;
import br.com.fatec.pokemon.repository.dto.UserOrm;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final PasswordEncoder encoder;
    private final UserRepositoryWithMongodb repository;

    public UserRepositoryImpl(
            PasswordEncoder encoder,
            UserRepositoryWithMongodb repository) {
        this.encoder = encoder;
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        try {
            UserOrm orm = repository.save(UserRepositoryImplAdapter.cast(user));
            return UserRepositoryImplAdapter.cast(orm, encoder);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public User findByUser(final String user) {
        try {
            Optional<UserOrm> optional = repository.findByUsername(user);

            if(optional.isEmpty()) {
                throw new UsernameNotFoundException("Usuario não encontrado");
            }

            return UserRepositoryImplAdapter.cast(optional.get(), encoder);

//            return new User(
//                    "1",
//                    "admin",
//                    encoder.encode("123456"),
//                    List.of(UserRole.USER));


        } catch (InternalAuthenticationServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
