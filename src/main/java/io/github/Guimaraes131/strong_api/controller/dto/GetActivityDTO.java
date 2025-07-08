package io.github.Guimaraes131.strong_api.controller.dto;

import io.github.Guimaraes131.strong_api.model.enums.Category;
import io.github.Guimaraes131.strong_api.model.enums.Intensity;

import java.time.LocalDate;
import java.util.UUID;

public record GetActivityDTO(
        UUID id,
        String type,
        Category category,
        Integer duration,
        LocalDate date,
        Intensity intensity,
        String description
) {
}
