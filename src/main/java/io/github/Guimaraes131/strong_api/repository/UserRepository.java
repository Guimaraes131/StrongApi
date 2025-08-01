package io.github.Guimaraes131.strong_api.repository;

import io.github.Guimaraes131.strong_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByLogin(String login);

    boolean existsByLogin(String login);
}
