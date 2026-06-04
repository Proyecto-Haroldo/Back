package itm.proyectoharoldo.backend.Models.DTO.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

}
