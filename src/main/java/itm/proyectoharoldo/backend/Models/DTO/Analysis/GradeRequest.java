package itm.proyectoharoldo.backend.Models.DTO.Analysis;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeRequest {

    @NotBlank
    private String contenidoRevision;

    @NotBlank
    private String colorSemaforo;

}