package cz.pollib.dto.validation.validator;

import cz.pollib.dto.InvoiceDTO;
import cz.pollib.dto.validation.annotation.PersonSellerBuyerNotSame;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PersonSellerBuyerNotSameValidator implements ConstraintValidator<PersonSellerBuyerNotSame, InvoiceDTO> {
    @Override
    public void initialize(PersonSellerBuyerNotSame constraintAnnotation) {
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
