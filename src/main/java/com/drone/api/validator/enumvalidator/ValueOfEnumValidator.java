package com.drone.api.validator.enumvalidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValueOfEnumValidator.class);

    private  List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnum annotation) {
        try {
            Method method = annotation.enumClass().getMethod("getValue", null);
            acceptedValues= Stream.of(annotation.enumClass().getEnumConstants())
                    .map(object->{
                        try {
                            return method.invoke(object, null).toString();
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            LOGGER.error(e.getMessage());
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (NoSuchMethodException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return acceptedValues.contains(value.toString());
    }
}
