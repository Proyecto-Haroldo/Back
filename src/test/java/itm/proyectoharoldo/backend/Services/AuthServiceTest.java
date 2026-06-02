package itm.proyectoharoldo.backend.Services;

import itm.proyectoharoldo.backend.Models.DTO.Auth.AuthRequest;
import itm.proyectoharoldo.backend.Models.DTO.Auth.RegisterRequest;
import itm.proyectoharoldo.backend.Models.Enums.ClientType;
import itm.proyectoharoldo.backend.Models.User;
import itm.proyectoharoldo.backend.Repositories.UserRepository;
import itm.proyectoharoldo.backend.Utility.UserAlreadyExistsException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    @Test
    void processLogin_shouldThrowUsernameNotFoundException_whenUserEmailNotFound(){

        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("test@domain.com");
        authRequest.setPassword("test");

        when(userRepository.findByEmail(authRequest.getEmail())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () ->  authService.processLogin(authRequest));

    }

    @Test
    void processRegister_shouldThrowUserAlreadyExistException_whenUserEmailAlreadyExist(){

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("existant@domain.com");
        registerRequest.setPassword("test");

        when(userRepository.findByEmail(registerRequest.getEmail())).thenReturn(Optional.of(new User()));

        assertThrows(UserAlreadyExistsException.class, () ->  authService.processRegister(registerRequest));

    }

    @Test
    void processRegister_shouldThrowUserAlreadyExistException_whenUserCedulaNITAlreadyExist(){

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("test@domain.com");
        registerRequest.setPassword("test");
        registerRequest.setCedulaOrNIT("12345");
        registerRequest.setClientType(ClientType.PERSONA);

        User existingUserWithSameCedulaOrNIT = new User();
        existingUserWithSameCedulaOrNIT.setCedulaOrNIT("12345");
        existingUserWithSameCedulaOrNIT.setClientType(ClientType.PERSONA);

        when(userRepository.findAllByCedulaOrNIT(registerRequest.getCedulaOrNIT())).thenReturn(List.of(existingUserWithSameCedulaOrNIT));

        assertThrows(UserAlreadyExistsException.class, () ->  authService.processRegister(registerRequest));

    }

}