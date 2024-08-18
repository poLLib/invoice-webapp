package cz.pollib.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation for validating that the due date is after the issued date in an invoice.
 */
@Constraint(validatedBy = InvoiceDatesValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface InvoiceDatesValidAnnotation {
    String message() default "Datum splatnosti nemůže být před datem vystavení faktury";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}