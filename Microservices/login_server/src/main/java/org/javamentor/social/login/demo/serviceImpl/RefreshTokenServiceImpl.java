package org.javamentor.social.login.demo.serviceImpl;

import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.javamentor.social.login.demo.config.JwtTokenProvider;
import org.javamentor.social.login.demo.config.RefreshTokenProvider;
import org.javamentor.social.login.demo.dao.AccountDao;
import org.javamentor.social.login.demo.dao.RefreshTokenRepository;
import org.javamentor.social.login.demo.exceptions.NoSuchTokenExeption;
import org.javamentor.social.login.demo.model.Account;
import org.javamentor.social.login.demo.model.RefreshToken;
import org.javamentor.social.login.demo.model.request.TokenRefreshRequest;
import org.javamentor.social.login.demo.model.response.ResponseJwtToken;
import org.javamentor.social.login.demo.model.response.ResponseRefreshToken;
import org.javamentor.social.login.demo.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Slf4j
@Getter
@Data
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Value("${jwt.expirationTimeOfRefresh}")
    private Long refreshTokenDurationMs;

    private final RefreshTokenProvider refreshTokenProvider;

    private final RefreshTokenRepository refreshTokenRepository;

    private final AccountDao accountDao;

    private final JwtTokenProvider jwtTokenProvider;

    public RefreshTokenServiceImpl(RefreshTokenProvider refreshTokenProvider
            , RefreshTokenRepository refreshTokenRepository, AccountDao accountDao, JwtTokenProvider jwtTokenProvider) {
        this.refreshTokenProvider = refreshTokenProvider;
        this.refreshTokenRepository = refreshTokenRepository;
        this.accountDao = accountDao;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public void generateRefreshTokenByAccount(Account account) {
        String token = refreshTokenProvider.generateRefreshToken
                (account.getEmail(), account.getRole());
        RefreshToken refreshToken = new RefreshToken(account, token
                , Instant.now().plusMillis(refreshTokenDurationMs));
        account.getTokens().add(refreshToken);
        save(refreshToken);
    }

    @Override
    public String generateJwtTokenByAccount(Account account) {

        return jwtTokenProvider.generateJwtToken (account.getEmail()
                , account.getRole(), account.getId());

    }

    public void save(RefreshToken refreshToken) {
        refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken verifyExpiration(String refreshToken) {
        RefreshToken token = refreshTokenRepository.findByRefreshTokenContent(refreshToken)
                .orElseThrow(NoSuchTokenExeption::new);
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            Account account = token.getAccount();
            generateRefreshTokenByAccount(account);
            List<RefreshToken> list = account.getTokens();
            token = list.get(list.size() - 1);
        }
        return token;
    }

    @Override
    public ResponseJwtToken getJwtTokenByRefreshTokenContent(TokenRefreshRequest request) {

        String refreshTokenContent = request.getRefreshToken();
        RefreshToken token = verifyExpiration(refreshTokenContent);
        String jwtToken = generateJwtTokenByAccount(token.getAccount());
        ResponseJwtToken responseJwtToken = new ResponseJwtToken();
        responseJwtToken.setJwtToken(jwtToken);
        return responseJwtToken;

    }

    @Override
    public ResponseRefreshToken getRefreshTokenByRefreshTokenContent(TokenRefreshRequest request) {

        String refreshTokenContent = request.getRefreshToken();
        RefreshToken token = verifyExpiration(refreshTokenContent);
        refreshTokenContent = token.getRefreshTokenContent();
        ResponseRefreshToken responseRefreshToken = new ResponseRefreshToken();
        responseRefreshToken.setRefreshToken(refreshTokenContent);
        return responseRefreshToken;

    }
}


//    @Transactional
//    public int deleteByUserId(Long userId) {
//        return refreshTokenRepository.deleteByAccount(userRepository.findById(userId).get());
//    }

