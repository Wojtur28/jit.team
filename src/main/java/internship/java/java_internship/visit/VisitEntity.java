package internship.java.java_internship.visit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class VisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //H2 not support UUID

    @NotNull
    private LocalDateTime visitDate;

    @NotNull
    private String personName;

    @NotNull
    private String catName;

    @NotNull
    private int catAge;

    @NotNull
    private String catColor;

}
