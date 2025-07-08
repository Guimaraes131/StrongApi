package io.github.Guimaraes131.strong_api.service;

import io.github.Guimaraes131.strong_api.model.Activity;
import io.github.Guimaraes131.strong_api.model.enums.Category;
import io.github.Guimaraes131.strong_api.model.enums.Intensity;
import io.github.Guimaraes131.strong_api.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void delete(Activity activity) {
        repository.delete(activity);
    }

    public List<Activity> index(String type, Intensity intensity, Category category) {
        var activity = new Activity();

        activity.setType(type);
        activity.setIntensity(intensity);
        activity.setCategory(category);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();

        Example<Activity> example = Example.of(activity, matcher);

        return repository.findAll(example);
    }
}
