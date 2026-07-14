package com.spms.service;

import com.spms.entity.RefreshToken;
import com.spms.entity.User;

public interface RefreshTokenService {

    void saveRefreshTokenService(User user , String refreshToken);

    RefreshToken validateRefreshToken(String refreshToken);

    void revokeRefreshToken(String refreshToken);

}
