package cz.pollib.dto.validation;

import cz.pollib.dto.InvoiceDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SellerBuyerNotSameValidator implements ConstraintValidator<SellerBuyerNotSameValidAnnotation, InvoiceDTO> {
    @Override
    public void initialize(SellerBuyerNotSameValidAnnotation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(InvoiceDTO invoiceDTO, ConstraintValidatorContext context) {
        if (invoiceDTO == null) {
            return true;
        }

        Long buyerId = invoiceDTO.getBuyer() != null ? invoiceDTO.getBuyer().getId() : null;
        Long sellerId = invoiceDTO.getSeller() != null ? invoiceDTO.getSeller().getId() : null;

        if (buyerId == null || sellerId == null) {
            return true; // Return true if either ID is null, or handle as needed
        }

        boolean isValid = !buyerId.equals(sellerId);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("buyer")
                    .addConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("seller")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
