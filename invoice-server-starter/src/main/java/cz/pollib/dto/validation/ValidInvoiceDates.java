package cz.pollib.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DueDateAfterIssuedValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidInvoiceDates {
    String message() default "Datum splatnosti nemůže být před datem vystavení faktury";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}