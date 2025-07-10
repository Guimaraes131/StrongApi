package io.github.Guimaraes131.strong_api.validator;

import io.github.Guimaraes131.strong_api.exception.DuplicateRecordException;
import io.github.Guimaraes131.strong_api.model.User;
import io.github.Guimaraes131.strong_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository repository;

    public void validate(User user) {
        if (existsByLogin(user)) {
            throw new DuplicateRecordException("This login is already being used");
        }
    }

    private boolean existsByLogin(User user) {
        return repository.existsByLogin(user.getLogin());
    }
}
