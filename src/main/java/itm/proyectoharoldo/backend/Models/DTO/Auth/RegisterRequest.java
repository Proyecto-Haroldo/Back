package itm.proyectoharoldo.backend.Models.DTO.Auth;

import itm.proyectoharoldo.backend.Models.Role;
import itm.proyectoharoldo.backend.Models.Enums.ClientType;
import itm.proyectoharoldo.backend.Models.DTO.CategoryDTO;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8)
    @Pattern(
            regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{8,}$",
            message = "La contraseña requiere: mínimo 8 caracteres, 1 minúscula, 1 mayúscula, 1 número, 1 caracter especial"
    )
    private String password;

    @NotBlank
    private String cedulaOrNIT;

    @NotBlank
    private String legalName;

    private ClientType clientType;

    private Role role;

    private String sector;

    private String phone;

    private String network;

    private String location;

    private Set<CategoryDTO> specialities;

}
