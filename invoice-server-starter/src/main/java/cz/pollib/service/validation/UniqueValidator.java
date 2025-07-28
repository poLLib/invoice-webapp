package cz.pollib.service.validation;

import cz.pollib.entity.repository.InvoiceRepository;
import cz.pollib.entity.repository.PersonRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

/**
 * Validator for the {@link Unique} annotation.
 * Checks if a value is unique in the specified entity and field.
 * 
 * <p>This validator supports the following entity and field combinations:</p>
 * <ul>
 *   <li>entity="person", field="identificationNumber" - Checks if a person with the given identification number exists</li>
 *   <li>entity="invoice", field="invoiceNumber" - Checks if an invoice with the given invoice number exists</li>
 * </ul>
 * 
 * <p>To use this validator, add the {@link Unique} annotation to a field in your request class:</p>
 * <pre>
 * {@code @Unique(entity = "person", field = "identificationNumber")}
 * private String identificationNumber;
 * 
 * {@code @Unique(entity = "invoice", field = "invoiceNumber")}
 * private int invoiceNumber;
 * </pre>
 * 
 * <p>The validator handles different types of values:</p>
 * <ul>
 *   <li>Null values are considered valid (use {@code @NotNull} for null validation)</li>
 *   <li>Empty strings are considered valid (use {@code @NotBlank} for empty string validation)</li>
 *   <li>For invoice numbers, both Integer values and String values that can be parsed to integers are supported</li>
 * </ul>
 */
public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    private final PersonRepository personRepository;
    private final InvoiceRepository invoiceRepository;
    
    private String entity;
    private String field;

    public UniqueValidator(PersonRepository personRepository, InvoiceRepository invoiceRepository) {
        this.personRepository = personRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public void initialize(Unique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.entity = constraintAnnotation.entity();
        this.field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        
        // Check uniqueness based on entity and field
        if ("person".equalsIgnoreCase(entity)) {
            if ("identificationNumber".equalsIgnoreCase(field)) {
                String stringValue = value.toString();
                if (!StringUtils.hasText(stringValue)) {
                    return true;
                }
                return !personRepository.existsByIdentificationNumber(stringValue);
            }
        } else if ("invoice".equalsIgnoreCase(entity)) {
            if ("invoiceNumber".equalsIgnoreCase(field)) {
                try {
                    int invoiceNumber;
                    if (value instanceof Integer) {
                        invoiceNumber = (Integer) value;
                    } else {
                        invoiceNumber = Integer.parseInt(value.toString());
                    }
                    return !invoiceRepository.existsByInvoiceNumber(invoiceNumber);
                } catch (NumberFormatException e) {
                    return true;
                }
            }
        }
        return true;
    }
}
