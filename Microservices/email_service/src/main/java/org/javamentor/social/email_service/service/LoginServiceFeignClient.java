package org.javamentor.social.email_service.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FunctionalInterface
@Component
@FeignClient(name = "login-service")
public interface LoginServiceFeignClient {

    @PostMapping("/api/rest/data/email")
    String getEmailByAccountId(@RequestBody Long userId);
}
