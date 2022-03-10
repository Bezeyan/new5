package org.javamentor.social.email_service.valid_interface_email_service.date_create_valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.Temporal;

class DateTodayValidator implements ConstraintValidator<Today, Temporal> {
    @Override
    public void initialize(Today constraintAnnotation) {
    }

    @Override
    public boolean isValid(Temporal value, ConstraintValidatorContext context) {
        return value == null || LocalDate.from(value).isEqual(LocalDate.now());
    }
}
