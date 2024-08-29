package cz.pollib.dto.validation.annotation;

import cz.pollib.dto.validation.validator.UniqueUserEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueUserEmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUserEmail {
    String message() default "Uživatel s tímto emailem již existuje";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
