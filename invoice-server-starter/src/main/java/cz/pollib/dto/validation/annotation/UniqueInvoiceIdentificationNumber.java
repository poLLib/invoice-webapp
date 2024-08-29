package cz.pollib.dto.validation.annotation;

import cz.pollib.dto.validation.validator.UniqueInvoiceIdentificationNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueInvoiceIdentificationNumberValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueInvoiceIdentificationNumber {
    String message() default "Toto IČO již v databázi společností existuje";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
