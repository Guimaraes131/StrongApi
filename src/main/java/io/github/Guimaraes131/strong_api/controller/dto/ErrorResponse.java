package io.github.Guimaraes131.strong_api.controller.dto;

import java.util.List;

public record ErrorResponse(int status, String message, List<FieldError> errors) {

}
