package io.github.Guimaraes131.strong_api.controller;

import io.github.Guimaraes131.strong_api.controller.dto.GetActivityDTO;
import io.github.Guimaraes131.strong_api.controller.dto.PostActivityDTO;
import io.github.Guimaraes131.strong_api.controller.mapper.ActivityMapper;
import io.github.Guimaraes131.strong_api.model.Activity;
import io.github.Guimaraes131.strong_api.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService service;
    private final ActivityMapper mapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PostActivityDTO dto) {
        Activity activity = mapper.toEntity(dto);

        System.out.println(activity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(activity.getId())
                .toUri();

        service.create(activity);

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetActivityDTO> get(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);

        return service.get(uuid)
                .map(entity -> {
                    var dto = mapper.toDTO(entity);

                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
