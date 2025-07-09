package io.github.Guimaraes131.strong_api.controller;

import io.github.Guimaraes131.strong_api.GenericController;
import io.github.Guimaraes131.strong_api.controller.dto.PostUserDTO;
import io.github.Guimaraes131.strong_api.controller.mapper.UserMapper;
import io.github.Guimaraes131.strong_api.model.User;
import io.github.Guimaraes131.strong_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController implements GenericController {

    private final UserService service;
    private final UserMapper mapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PostUserDTO dto) {
        User user = mapper.toEntity(dto);

        service.create(user);

        return ResponseEntity.created(generateLocationHeader(user.getId())).build();
    }
}
