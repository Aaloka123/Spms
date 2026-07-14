package com.spms.service.impl;

import com.spms.entity.RefreshToken;
import com.spms.entity.User;
import com.spms.exception.InvalidRefreshTokenException;
import com.spms.repository.RefreshTokenRepository;
import com.spms.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refresh.expiration}")
    private long refreshExpiration;

    @Override
    @Transactional
    public void saveRefreshTokenService(User user, String refreshToken) {
        refreshTokenRepository.deleteByUser(user);

        RefreshToken token = new RefreshToken(
                refreshToken,
                LocalDateTime.now().plusSeconds(refreshExpiration / 1000),
                user
        );

        refreshTokenRepository.save(token);
    }

    @Override
    @Transactional(readOnly = true)
    public RefreshToken validateRefreshToken(String refreshToken) {
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(InvalidRefreshTokenException::new);

        if (token.isRevoked() || token.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new InvalidRefreshTokenException();
        }

        return token;
    }

    @Override
    @Transactional
    public void revokeRefreshToken(String refreshToken) {
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(InvalidRefreshTokenException::new);

        token.setRevoked(true);
        refreshTokenRepository.save(token);
    }
}