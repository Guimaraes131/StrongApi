package io.github.Guimaraes131.strong_api.controller.dto;

import io.github.Guimaraes131.strong_api.model.enums.Category;
import io.github.Guimaraes131.strong_api.model.enums.Intensity;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PostActivityDTO(
        @NotBlank(message = "Type field cannot be blank or null.")
        String type,

        @NotNull(message = "Category field cannot be null.")
        Category category,

        @NotNull(message = "Duration field cannot be null")
        @Min(value = 5, message = "Duration field cannot have less than 5 minutes")
        Integer duration,

        @NotNull(message = "Date field cannot be null.")
        @PastOrPresent(message = "Date field cannot be in the future")
        LocalDate date,

        @NotNull(message = "Type field cannot be null.")
        Intensity intensity,

        @Size(max = 255, message = "Description field only accepts 255 characters")
        String description) {
}
