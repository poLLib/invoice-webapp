package cz.pollib.service.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SellerAndBuyerNotSameValidator implements ConstraintValidator<SellerAndBuyerNotSame, SellerAndBuyerValidatable> {
    @Override
    public void initialize(SellerAndBuyerNotSame constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(SellerAndBuyerValidatable InvoiceRequest, ConstraintValidatorContext context) {
        if (InvoiceRequest == null) {
            return true;
        }

        Long buyerId = InvoiceRequest.getBuyerId() != null ? InvoiceRequest.getBuyerId() : null;
        Long sellerId = InvoiceRequest.getSellerId() != null ? InvoiceRequest.getSellerId() : null;

        if (buyerId == null || sellerId == null) {
            return true;
        }

        boolean isValid = !buyerId.equals(sellerId);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("buyerId")
                    .addConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("sellerId")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
