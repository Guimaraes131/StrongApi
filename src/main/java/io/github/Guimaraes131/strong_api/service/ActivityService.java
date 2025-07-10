package io.github.Guimaraes131.strong_api.service;

import io.github.Guimaraes131.strong_api.controller.dto.ByCategory;
import io.github.Guimaraes131.strong_api.controller.dto.ByIntensity;
import io.github.Guimaraes131.strong_api.controller.dto.Period;
import io.github.Guimaraes131.strong_api.controller.dto.Stats;
import io.github.Guimaraes131.strong_api.model.Activity;
import io.github.Guimaraes131.strong_api.model.enums.Category;
import io.github.Guimaraes131.strong_api.model.enums.Intensity;
import io.github.Guimaraes131.strong_api.repository.ActivityRepository;
import io.github.Guimaraes131.strong_api.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository repository;
    private final SecurityService service;

    public void create(Activity activity) {
        var user = service.getLoggedInUser();

        activity.setUser(user);
        repository.save(activity);
    }

    public Optional<Activity> get(UUID id) {
        var user = service.getLoggedInUser();

        return repository.findByIdAndUser(id, user);
    }

    public void delete(Activity activity) {
        repository.delete(activity);
    }

    public List<Activity> index(String type, Intensity intensity, Category category) {
        var activity = new Activity();
        var user = service.getLoggedInUser();

        activity.setType(type);
        activity.setIntensity(intensity);
        activity.setCategory(category);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();

        Example<Activity> example = Example.of(activity, matcher);
        List<Activity> activities = repository.findAll(example);

        return activities.stream()
                .filter(ac -> ac.getUser().equals(user))
                .toList();
    }

    public void update(Activity activity) {
        repository.save(activity);
    }

    public Stats stats() {
        LocalDate now = LocalDate.now();
        LocalDate start = now.with(DayOfWeek.MONDAY);
        LocalDate end = now.with(DayOfWeek.SUNDAY);
        var user = service.getLoggedInUser();

        List<Activity> activities = repository.findAllByDateBetweenAndUser(start, end, user);

        Integer totalDurationMinutes = activities
                .stream()
                .mapToInt(Activity::getDuration)
                .sum();

        Period period = new Period(start, end);

        Integer activitiesCount = activities.size();

        Map<Category, Integer> categoryTotals = activities
                .stream()
                .collect(Collectors.groupingBy(
                        Activity::getCategory,
                        Collectors.summingInt(Activity::getDuration)
                ));

        ByCategory byCategory = new ByCategory(
                categoryTotals.getOrDefault(Category.CARDIO, 0),
                categoryTotals.getOrDefault(Category.MOBILITY, 0),
                categoryTotals.getOrDefault(Category.BODYBUILDING, 0),
                categoryTotals.getOrDefault(Category.CALISTHENICS, 0),
                categoryTotals.getOrDefault(Category.FIGHT, 0)
        );

        Map<Intensity, Integer> intensityTotals = activities.stream()
                .collect(Collectors.groupingBy(
                        Activity::getIntensity,
                        Collectors.summingInt(Activity::getDuration)
                ));

        ByIntensity byIntensity = new ByIntensity(
                intensityTotals.getOrDefault(Intensity.LIGHT, 0),
                intensityTotals.getOrDefault(Intensity.MODERATE, 0),
                intensityTotals.getOrDefault(Intensity.HEAVY, 0)
        );

        return new Stats(totalDurationMinutes, period, activitiesCount, byCategory, byIntensity);
    }
}
