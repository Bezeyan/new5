package org.javamentor.social.login.demo;

import org.javamentor.social.login.demo.init.DataInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class JmSocialLoginProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmSocialLoginProjectApplication.class, args);
	}

	@Bean
	public DataInit dataInit() {
		return new DataInit();
	}
}
