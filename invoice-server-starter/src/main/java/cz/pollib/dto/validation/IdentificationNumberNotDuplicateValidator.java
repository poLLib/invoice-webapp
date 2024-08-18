package cz.pollib.dto.validation;

import cz.pollib.entity.repository.PersonRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class IdentificationNumberNotDuplicateValidator implements ConstraintValidator<IdentificationNumberNotDuplicateValidAnnotation, String> {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public void initialize(IdentificationNumberNotDuplicateValidAnnotation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String identificationNumber, ConstraintValidatorContext constraintValidatorContext) {
        return !personRepository.existsByIdentificationNumber(identificationNumber);
    }
}
