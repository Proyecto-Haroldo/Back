package itm.proyectoharoldo.backend.Controllers;

import itm.proyectoharoldo.backend.Models.DTO.Auth.*;
import itm.proyectoharoldo.backend.Services.AuthService;

import itm.proyectoharoldo.backend.Services.RateLimiterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RateLimiterService rateLimiterService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request, HttpServletRequest httpServletRequest) {

        String ip = httpServletRequest.getRemoteAddr();
        if(!rateLimiterService.tryConsume(ip)){
            log.warn("Login controller rate limit exceeded for IP: {}", ip);
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }

        return ResponseEntity.ok(authService.processLogin(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request, HttpServletRequest httpServletRequest) {

        String ip = httpServletRequest.getRemoteAddr();
        if(!rateLimiterService.tryConsume(ip)){
            log.warn("Register controller rate limit exceeded for IP: {}", ip);
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(authService.processRegister(request));
    }

}
