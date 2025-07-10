package io.github.Guimaraes131.strong_api.controller;

import io.github.Guimaraes131.strong_api.GenericController;
import io.github.Guimaraes131.strong_api.controller.dto.GetActivityDTO;
import io.github.Guimaraes131.strong_api.controller.dto.PostActivityDTO;
import io.github.Guimaraes131.strong_api.controller.dto.Stats;
import io.github.Guimaraes131.strong_api.controller.mapper.ActivityMapper;
import io.github.Guimaraes131.strong_api.model.Activity;
import io.github.Guimaraes131.strong_api.model.enums.Category;
import io.github.Guimaraes131.strong_api.model.enums.Intensity;
import io.github.Guimaraes131.strong_api.service.ActivityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
public class ActivityController implements GenericController {

    private final ActivityService service;
    private final ActivityMapper mapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid PostActivityDTO dto) {
        Activity activity = mapper.toEntity(dto);

        service.create(activity);

        return ResponseEntity.created(generateLocationHeader(activity.getId())).build();
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);

        return service.get(uuid)
                .map(entity -> {
                    service.delete(entity);

                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<GetActivityDTO>> index(
            @RequestParam(required = false, value = "type") String type,
            @RequestParam(required = false, value = "intensity") Intensity intensity,
            @RequestParam(required = false, value = "category") Category category) {

        List<Activity> activities = service.index(type, intensity, category);

        List<GetActivityDTO> dtos = activities.stream()
                .map(mapper::toDTO)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody @Valid PostActivityDTO dto) {
        UUID uuid = UUID.fromString(id);

        return service.get(uuid)
                .map(entity -> {
                    mapper.updateFromDTO(dto, entity);
                    service.update(entity);

                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/stats")
    public ResponseEntity<Stats> stats() {
        Stats stats = service.stats();

        return ResponseEntity.ok(stats);
    }
}
