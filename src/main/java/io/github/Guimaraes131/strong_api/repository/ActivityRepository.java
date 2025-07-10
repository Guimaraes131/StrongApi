package io.github.Guimaraes131.strong_api.repository;

import io.github.Guimaraes131.strong_api.model.Activity;
import io.github.Guimaraes131.strong_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActivityRepository extends JpaRepository<Activity, UUID> {

    List<Activity> findAllByDateBetweenAndUser(LocalDate start, LocalDate end, User user);

    Optional<Activity> findByIdAndUser(UUID id, User user);
}
