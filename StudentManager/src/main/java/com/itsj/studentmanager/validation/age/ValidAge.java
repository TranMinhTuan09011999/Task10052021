package com.itsj.studentmanager.validation.age;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, ANNOTATION_TYPE, TYPE_USE })
@Constraint(validatedBy = AgeValidator.class)
public @interface ValidAge {
    String message() default "Age must be from 18 to 80";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}