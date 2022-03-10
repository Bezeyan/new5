package org.javamentor.social.email_service.valid_interface_email_service.date_create_valid;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateTodayValidator.class)
@Documented
public @interface Today {
    String message() default "Date is not equal to today";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
