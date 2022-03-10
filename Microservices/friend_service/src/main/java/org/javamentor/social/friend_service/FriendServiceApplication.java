package org.javamentor.social.friend_service;

import org.javamentor.social.friend_service.init.DataInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FriendServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FriendServiceApplication.class, args);
    }

    @Bean
    public DataInit dataInit() {
        return new DataInit();
    }
}
