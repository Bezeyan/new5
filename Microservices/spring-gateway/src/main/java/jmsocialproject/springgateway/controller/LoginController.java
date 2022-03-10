package jmsocialproject.springgateway.controller;

import jmsocialproject.springgateway.model.Response;
import jmsocialproject.springgateway.model.request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
public class LoginController {
    private final WebClient.Builder loadBalancedWebClientBuilder;


    public LoginController(WebClient.Builder webClientBuilder) {
        this.loadBalancedWebClientBuilder = webClientBuilder;

    }

    @PostMapping("/refreshToken")
    public Mono<Response> getRefreshTokenByRefreshToken(@Valid @RequestBody request request) {

        return loadBalancedWebClientBuilder.build().post().uri("http://login-service/api/rest/auth/token/refresh")
                .bodyValue(request)
                .retrieve().bodyToMono(Response.class);
    }

}