package cz.pollib.dto.validation;

import cz.pollib.entity.repository.PersonRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdentificationNumberNotDuplicateValidator implements ConstraintValidator<IdentificationNumberNotDuplicate, String> {

    private final PersonRepository personRepository;

    public IdentificationNumberNotDuplicateValidator(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void initialize(IdentificationNumberNotDuplicate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String identificationNumber, ConstraintValidatorContext constraintValidatorContext) {
        return !personRepository.existsByIdentificationNumber(identificationNumber);
    }
}
