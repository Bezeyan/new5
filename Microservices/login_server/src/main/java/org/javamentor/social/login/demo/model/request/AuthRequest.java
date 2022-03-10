package org.javamentor.social.login.demo.model.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;

}
