package io.github.Guimaraes131.strong_api.controller.mapper;

import io.github.Guimaraes131.strong_api.controller.dto.GetActivityDTO;
import io.github.Guimaraes131.strong_api.controller.dto.PostActivityDTO;
import io.github.Guimaraes131.strong_api.model.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

    Activity toEntity(PostActivityDTO dto);

    GetActivityDTO toDTO(Activity entity);

    void updateFromDTO(PostActivityDTO dto, @MappingTarget Activity activity);
}
