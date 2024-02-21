package com.drone.api.drone.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = CustomValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomValidation {

    String message() default "Custom validation failed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

