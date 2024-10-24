package com.projectj2ee.travel_server.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD,PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordSpecialCharacterValidator.class)
public @interface PasswordSpecialCharacter {
    String message() default "Password must not contain special characters";
    Class<?>[] group() default {} ;
    Class<? extends Payload>[] payload() default {};

}
