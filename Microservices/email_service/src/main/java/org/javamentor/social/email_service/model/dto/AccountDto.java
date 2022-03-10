package org.javamentor.social.email_service.model.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javamentor.social.email_service.model.entity.AccountPrivilegeStatus;
import org.javamentor.social.email_service.valid_interface_email_service.date_create_valid.Today;
import org.javamentor.social.email_service.valid_interface_email_service.email_account_valid.EmailConstraintValid;
import org.javamentor.social.email_service.valid_interface_email_service.password_account_valid.ValidPassword;
import org.javamentor.social.email_service.valid_interface_email_service.enum_privilege_status_valid.ValueOfEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {


    @NotBlank(message = "Поле email не может быть пустым!")
    @EmailConstraintValid  // проверка на регулярное выражение типа a@x.y
    private String email;

    @NotBlank(message = "Введите пароль!")
    @ValidPassword // regex password
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)  // хранение перечисления в базе в String
    @ValueOfEnum(enumClass = AccountPrivilegeStatus.class)  //проверка на valueOf
    private AccountPrivilegeStatus accountPrivilegeStatus;

    @NotNull
    @Today //проверка даты на сегодняшний день
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfCreation;
}
