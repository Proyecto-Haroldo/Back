package itm.proyectoharoldo.backend.Utility;

import itm.proyectoharoldo.backend.Services.RateLimiterService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class RateLimitFilter extends OncePerRequestFilter {

    private final RateLimiterService rateLimiterService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String path   = request.getServletPath();

        String ip = request.getRemoteAddr();
        log.info("IP {} tried an authentication request to {}", ip, path);

        if(path.equals("/api/auth/login") || path.equals("/api/auth/register")) {
            if(!rateLimiterService.tryConsume(ip)){
                log.warn("Rate limit exceeded for IP: {}", ip);
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Demasiadas solicitudes, intente más tarde\"}");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

}