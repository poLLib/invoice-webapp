package cz.pollib.dto.validation;


import cz.pollib.dto.InvoiceDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DueDateAfterIssuedValidator implements ConstraintValidator<ValidInvoiceDates, InvoiceDTO> {

    @Override
    public void initialize(ValidInvoiceDates constraintAnnotation) {
    }

    @Override
    public boolean isValid(InvoiceDTO invoiceDTO, ConstraintValidatorContext context) {
        if (invoiceDTO == null) {
            return true;
        }

        if (invoiceDTO.getIssued() != null && invoiceDTO.getDueDate() != null) {
            if (invoiceDTO.getDueDate().isBefore(invoiceDTO.getIssued())) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                        .addPropertyNode("dueDate")
                        .addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}