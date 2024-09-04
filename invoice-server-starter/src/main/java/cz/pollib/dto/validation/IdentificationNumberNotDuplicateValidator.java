package cz.pollib.dto.validation;

import cz.pollib.entity.repository.PersonRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdentificationNumberNotDuplicateValidator implements ConstraintValidator<IdentificationNumberNotDuplicateValidAnnotation, String> {

    private final PersonRepository personRepository;

    public IdentificationNumberNotDuplicateValidator(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void initialize(IdentificationNumberNotDuplicateValidAnnotation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String identificationNumber, ConstraintValidatorContext constraintValidatorContext) {
        return !personRepository.existsByIdentificationNumber(identificationNumber);
    }
}
