package io.github.Guimaraes131.strong_api.service;

import io.github.Guimaraes131.strong_api.model.Activity;
import io.github.Guimaraes131.strong_api.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository repository;

    public void create(Activity activity) {
        repository.save(activity);
    }

    public Optional<Activity> get(UUID id) {
        return repository.findById(id);
    }
}
