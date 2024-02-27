package org.example.util.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.util.validation.validator.UserNameValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserNameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
ElementType.TYPE_USE, ElementType.PARAMETER})
public @interface Name {

    String message() default "name is not allow";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    public int min() default 2;
    public int max() default 20;
}
