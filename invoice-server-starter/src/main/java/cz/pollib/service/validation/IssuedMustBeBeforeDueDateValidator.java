package cz.pollib.service.validation;

import cz.pollib.service.model.CreateInvoiceRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IssuedMustBeBeforeDueDateValidator implements ConstraintValidator<IssuedMustBeBeforeDueDate, CreateInvoiceRequest> {

    @Override
    public void initialize(IssuedMustBeBeforeDueDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(CreateInvoiceRequest invoiceRequest, ConstraintValidatorContext context) {
        if (invoiceRequest == null) {
            return true;
        }

        if (invoiceRequest.getIssued() != null && invoiceRequest.getDueDate() != null) {
            if (invoiceRequest.getDueDate().isBefore(invoiceRequest.getIssued())) {
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