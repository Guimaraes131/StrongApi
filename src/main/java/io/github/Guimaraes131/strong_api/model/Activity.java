package io.github.Guimaraes131.strong_api.model;

import io.github.Guimaraes131.strong_api.model.enums.Category;
import io.github.Guimaraes131.strong_api.model.enums.Intensity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String type;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Integer duration;
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Intensity intensity;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
