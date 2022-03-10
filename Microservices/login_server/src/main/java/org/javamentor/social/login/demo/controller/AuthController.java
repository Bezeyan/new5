package org.javamentor.social.login.demo.controller;

import org.javamentor.social.login.demo.model.dto.AuthorizeDto;
import org.javamentor.social.login.demo.model.request.AuthRequest;
import org.javamentor.social.login.demo.model.request.TokenRefreshRequest;
import org.javamentor.social.login.demo.model.response.ResponseJwtToken;
import org.javamentor.social.login.demo.model.response.ResponseRefreshToken;
import org.javamentor.social.login.demo.service.AccountService;
import org.javamentor.social.login.demo.service.RefreshTokenService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@RestController
@RequestMapping("/api/rest/auth")
public class AuthController implements Client{

    private final AccountService accountService;
    private final RefreshTokenService refreshTokenService;

    public AuthController(AccountService accountService, RefreshTokenService refreshTokenService) {
        this.accountService = accountService;
        this.refreshTokenService = refreshTokenService;
    }


    @PostMapping("/login")
    public AuthorizeDto auth(@RequestBody AuthRequest request,
                             @RequestHeader Map<String, String> headers) {
        accountService.setLastVisitedDate(request, headers);
        return accountService.getAuthorizeDto(request);
    }

    @PostMapping("/registration")
    public void register(@RequestBody AuthRequest request) {
        accountService.register(request);
    }

    @PostMapping("/token/jwt")
    public ResponseJwtToken getJwtTokenByRefreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        return refreshTokenService.getJwtTokenByRefreshTokenContent(request);
    }

    @PostMapping("/token/refresh")
    public ResponseRefreshToken getRefreshTokenByRefreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        return refreshTokenService.getRefreshTokenByRefreshTokenContent(request);
    }

}