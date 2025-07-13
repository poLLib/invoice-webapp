package cz.pollib.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SellerAndBuyerNotSameValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SellerAndBuyerNotSame {
    String message() default "Dodavatel a odběratel nemůžou být totožný";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
