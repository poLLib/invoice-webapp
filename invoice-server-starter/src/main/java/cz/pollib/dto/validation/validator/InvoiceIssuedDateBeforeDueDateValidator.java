package cz.pollib.dto.validation.validator;


import cz.pollib.dto.InvoiceDTO;
import cz.pollib.dto.validation.annotation.InvoiceIssuedDateBeforeDueDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Custom validator to check if the due date is after the issued date in an invoice.
 */
public class InvoiceIssuedDateBeforeDueDateValidator implements ConstraintValidator<InvoiceIssuedDateBeforeDueDate, InvoiceDTO> {

    /**
     * Initializes the validator.
     *
     * @param constraintAnnotation the annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(InvoiceIssuedDateBeforeDueDate constraintAnnotation) {
    }

    /**
     * Validates whether the due date is after the issued date.
     *
     * @param invoiceDTO the InvoiceDTO to validate
     * @param context    the context in which the constraint is evaluated
     * @return true if the due date is after the issued date, false otherwise
     */
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