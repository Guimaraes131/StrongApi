package io.github.Guimaraes131.strong_api.service;

import io.github.Guimaraes131.strong_api.model.User;
import io.github.Guimaraes131.strong_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public void create(User user) {
        String password = user.getPassword();

        user.setPassword(encoder.encode(password));
        repository.save(user);
    }

    public Optional<User> getByLogin(String login) {
        return repository.findByLogin(login);
    }
}
