package org.example.util.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.util.validation.validator.UserPasswordValidator;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Constraint(validatedBy = UserPasswordValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "password is not allow";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    public int min() default 5;
    public int max() default 20;
}
