package com.example.conditionalvalidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SSNConstraintValidator.class)
public @interface ValidSSN {
    String message() default "{ssn.not.null}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}