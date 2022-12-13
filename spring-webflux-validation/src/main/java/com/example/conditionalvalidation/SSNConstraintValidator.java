package com.example.conditionalvalidation;

import com.example.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class SSNConstraintValidator implements ConstraintValidator<ValidSSN, UserDto> {
    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext context) {
        if("US".equals(userDto.getCountry()))
            return Objects.nonNull(userDto.getSsn());
        return true;
    }
}