package cz.pollib.service.validation;

import cz.pollib.entity.repository.PersonRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueIdentificationNumberValidator implements ConstraintValidator<UniqueIdentificationNumber, String> {

    private final PersonRepository personRepository;

    public UniqueIdentificationNumberValidator(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void initialize(UniqueIdentificationNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String identificationNumber, ConstraintValidatorContext constraintValidatorContext) {
        return !personRepository.existsByIdentificationNumber(identificationNumber);
    }
}
