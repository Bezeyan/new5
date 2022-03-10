package org.javamentor.social.login.demo.service;

import org.javamentor.social.login.demo.model.Account;
import org.javamentor.social.login.demo.model.RefreshToken;
import org.javamentor.social.login.demo.model.request.TokenRefreshRequest;
import org.javamentor.social.login.demo.model.response.ResponseJwtToken;
import org.javamentor.social.login.demo.model.response.ResponseRefreshToken;

import java.util.Optional;

public interface RefreshTokenService {

    void generateRefreshTokenByAccount(Account account);

    RefreshToken verifyExpiration(String refreshToken);

    ResponseJwtToken getJwtTokenByRefreshTokenContent(TokenRefreshRequest request);

    ResponseRefreshToken getRefreshTokenByRefreshTokenContent(TokenRefreshRequest request);

    String generateJwtTokenByAccount(Account account);

}
