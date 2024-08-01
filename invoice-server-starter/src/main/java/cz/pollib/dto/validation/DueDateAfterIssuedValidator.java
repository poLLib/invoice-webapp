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
            return false;
        }

        if (invoiceDTO.getIssued() != null && invoiceDTO.getDueDate() != null) {
            return invoiceDTO.getDueDate().isAfter(invoiceDTO.getIssued());
        }
        //TODO: musim odchytnout validaci globalexcephandleru

        return true;
    }
}