package org.javamentor.social.login.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.javamentor.social.login.demo.model.AccountStatus;
import org.javamentor.social.login.demo.valid_interface_login_server.enum_privilege_status_valid.ValueOfEnum;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class AccountDTO {

    @NotNull(message = "Статус аккаунта не установлен!")
    @ValueOfEnum(enumClass = AccountStatus.class)
    private AccountStatus status;

}
