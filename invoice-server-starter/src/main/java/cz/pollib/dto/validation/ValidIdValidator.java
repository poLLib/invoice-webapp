package cz.pollib.dto.validation;

import cz.pollib.dto.PersonDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidIdValidator implements ConstraintValidator<ValidId, PersonDTO> {
    @Override
    public void initialize(ValidId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(PersonDTO personDTO, ConstraintValidatorContext context) {
        if (personDTO == null) {
            return true;
        }

        if (personDTO.getId() == null) {
            context.disableDefaultConstraintViolation();

            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("id")
                    .addConstraintViolation();

            return false;
        }
        return true;
    }
}