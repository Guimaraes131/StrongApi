package io.github.Guimaraes131.strong_api.controller.dto;

public record Stats(
        Integer totalDurationMinutes,
        Period period,
        Integer activitiesCount,
        ByCategory byCategory,
        ByIntensity byIntensity
) {
}
