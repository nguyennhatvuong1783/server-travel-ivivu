package com.projectj2ee.travel_server.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordSpecialCharacterValidator implements ConstraintValidator<PasswordSpecialCharacter,String> {

    @Override
    public void initialize(PasswordSpecialCharacter constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return !password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
    }
}
