package io.github.Guimaraes131.strong_api.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record PostUserDTO(

        @NotBlank(message = "Login field cannot be null or blank")
        @Size(min = 3, max = 24, message = "Login field must have between 3 to 24 characters")
        String login,

        @NotBlank(message = "Password field cannot be null or blank")
        String password,

        @NotNull(message = "Roles field cannot be null")
        List<String> roles
) {
}
