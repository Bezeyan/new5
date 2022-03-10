package org.javamentor.social.email_service.valid_interface_email_service.password_account_valid;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword arg0) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                // Длина пароля от 6 до 12 символов
                new LengthRule(6, 12),

                // минимум один символ в верхнем регистре
                new CharacterRule(EnglishCharacterData.UpperCase, 1),

                // минимум один символ в нижнем регистре
                new CharacterRule(EnglishCharacterData.LowerCase, 1),

                // минимум одна цифра
                new CharacterRule(EnglishCharacterData.Digit, 1),

                // минимум один специальный символ
                new CharacterRule(EnglishCharacterData.Special, 1),

                // без пробелов
                new WhitespaceRule()

        ));
        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        List<String> messages = validator.getMessages(result);

        String messageTemplate = messages.stream()
                .collect(Collectors.joining(","));
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}