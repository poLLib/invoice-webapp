package cz.pollib.service.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom validation controlling that the seller and the buyer are not same
 */
@Documented
@Constraint(validatedBy = SellerAndBuyerNotSameValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SellerAndBuyerNotSame {
    String message() default "BuyerAndSellerNotSame";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
