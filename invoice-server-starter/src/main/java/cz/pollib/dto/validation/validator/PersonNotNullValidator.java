package cz.pollib.dto.validation.validator;

import cz.pollib.dto.PersonDTO;
import cz.pollib.dto.validation.annotation.PersonNotNull;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PersonNotNullValidator implements ConstraintValidator<PersonNotNull, PersonDTO> {
    @Override
    public void initialize(PersonNotNull constraintAnnotation) {
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