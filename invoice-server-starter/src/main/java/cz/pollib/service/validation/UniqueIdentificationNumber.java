package cz.pollib.service.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom validation controlling that the identification number is unique
 */
@Constraint(validatedBy = UniqueIdentificationNumberValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueIdentificationNumber {
    String message() default "IdentificationNumberIsNotUnique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
