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
@Constraint(validatedBy = DueDateAfterIssuedValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidInvoiceDates {

    /**
     * Default error message when validation fails.
     *
     * @return the error message
     */
    String message() default "Datum splatnosti nemůže být před datem vystavení faktury";

    /**
     * Groups can be used to restrict the set of constraints applied during validation.
     *
     * @return array of groups
     */
    Class<?>[] groups() default {};

    /**
     * Payload can be used by clients to assign custom payload objects to a constraint.
     *
     * @return array of payload classes
     */
    Class<? extends Payload>[] payload() default {};
}