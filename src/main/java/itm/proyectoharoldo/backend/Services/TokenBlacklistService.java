package itm.proyectoharoldo.backend.Services;

import itm.proyectoharoldo.backend.Models.TokenBlacklist;
import itm.proyectoharoldo.backend.Repositories.TokenBlacklistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenBlacklistService {

    private final TokenBlacklistRepository tokenBlacklistRepository;

    @Transactional
    public void blacklistToken(String token, LocalDateTime expiry) {
        if (!tokenBlacklistRepository.existsByToken(token)) {
            TokenBlacklist entry = new TokenBlacklist();
            entry.setToken(token);
            entry.setExpiry(expiry);
            tokenBlacklistRepository.save(entry);
            log.info("Token blacklisted successfully");
        }
    }

    @Transactional(readOnly = true)
    public boolean isBlacklisted(String token) {
        return tokenBlacklistRepository.existsByToken(token);
    }

    @Scheduled(cron = "0 0 3 * * *") // 3 AM every day
    @Transactional
    public void cleanExpiredTokens() {
        tokenBlacklistRepository.deleteByExpiryBefore(LocalDateTime.now());
        log.info("Expired blacklisted tokens cleaned up");
    }
}
