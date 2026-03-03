package cz.pollib.service.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom validation controlling that the attribute is unique.
 * Can be used for different entities and fields by specifying the entity and field parameters.
 */
@Constraint(validatedBy = UniqueValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {
    String message() default "FieldIsNotUnique";

    /**
     * The entity types to check uniqueness against (e.g., "person", "invoice")
     */
    String entity();

    /**
     * The field name to check uniqueness against (e.g., "identificationNumber", "invoiceNumber")
     */
    String field();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
