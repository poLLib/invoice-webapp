package cz.pollib.service.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom validation controlling that the due date is after the issued date in an invoice
 */
@Constraint(validatedBy = IssuedMustBeBeforeDueDateValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IssuedMustBeBeforeDueDate {
    String message() default "IssuedDateMustBeBeforeDueDate";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}