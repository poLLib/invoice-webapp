package cz.pollib.dto.validation.annotation;

import cz.pollib.dto.validation.validator.PersonNotNullValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PersonNotNullValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonNotNull {
    String message() default "Musíte vybrat jedno z možností";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
