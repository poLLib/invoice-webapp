package cz.pollib.dto.validation.annotation;

import cz.pollib.dto.validation.validator.PersonSellerBuyerNotSameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PersonSellerBuyerNotSameValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonSellerBuyerNotSame {
    String message() default "Dodavatel a odběratel nemůžou být totožný";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
