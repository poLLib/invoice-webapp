package cz.pollib.dto.validation.validator;

import cz.pollib.dto.validation.annotation.UniqueInvoiceIdentificationNumber;
import cz.pollib.entity.repository.PersonRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueInvoiceIdentificationNumberValidator implements ConstraintValidator<UniqueInvoiceIdentificationNumber, String> {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public void initialize(UniqueInvoiceIdentificationNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String identificationNumber, ConstraintValidatorContext constraintValidatorContext) {
        return !personRepository.existsByIdentificationNumber(identificationNumber);
    }
}
