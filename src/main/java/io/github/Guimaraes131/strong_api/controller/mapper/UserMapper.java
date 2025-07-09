package io.github.Guimaraes131.strong_api.controller.mapper;

import io.github.Guimaraes131.strong_api.controller.dto.PostUserDTO;
import io.github.Guimaraes131.strong_api.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(PostUserDTO dto);
}
