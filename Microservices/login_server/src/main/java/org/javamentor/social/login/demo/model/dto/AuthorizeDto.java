package org.javamentor.social.login.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.javamentor.social.login.demo.model.Account;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@AllArgsConstructor
@Data
public class AuthorizeDto {

    @NotBlank(message = "Отсутствует токен!")
    private String jwtToken;

    @NotEmpty(message = "Не найден пользователь!")
    private Account account;
}
