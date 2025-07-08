package io.github.Guimaraes131.strong_api.service;

import io.github.Guimaraes131.strong_api.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository repository;
}
