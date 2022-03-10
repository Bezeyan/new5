package org.javamentor.social.email_service.inform_mail.inform_dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javamentor.social.email_service.valid_interface_email_service.date_create_valid.Today;
import org.javamentor.social.email_service.valid_interface_email_service.email_account_valid.EmailConstraintValid;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InformMailDto {

    @NotBlank(message = "Введите почтовый адрес!")
    @EmailConstraintValid
    private String email;

    @NotBlank(message = "Введите тему сообщения")
    @Max(value = 50, message = "Тема сообщения не должна превышать 50 символов")
    private String subject;

    @NotBlank
    private String textMessage;

    @NotBlank
    private String firstName;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
}
