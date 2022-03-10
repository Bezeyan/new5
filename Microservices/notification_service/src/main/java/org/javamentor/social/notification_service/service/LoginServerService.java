package org.javamentor.social.notification_service.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("login-service")
public interface LoginServerService {

	@PostMapping("/api/rest/data/email")
	String getEmailById(long id);
}
