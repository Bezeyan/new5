package org.javamentor.social.email_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javamentor.social.email_service.valid_interface_email_service.email_account_valid.EmailConstraintValid;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailDto {

   @NotBlank(message = "Введите почтовый адрес!")
   @EmailConstraintValid
   private String email;

   @NotBlank(message = "Введите тему сообщения")
   @Max(value = 50, message = "Тема сообщения не должна превышать 50 символов")
   private String subject;

   @NotBlank(message = "Введите сообщение")
   @Max(value = 300, message = "Текст сообщения не должен превышать 300 символов")
   private String text;
}
