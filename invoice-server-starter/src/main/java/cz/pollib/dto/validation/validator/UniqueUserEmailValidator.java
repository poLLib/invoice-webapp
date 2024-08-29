package cz.pollib.dto.validation.validator;

import cz.pollib.dto.validation.annotation.UniqueUserEmail;
import cz.pollib.entity.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UniqueUserEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email != null && userRepository.findByEmail(email).isEmpty();
    }
}
