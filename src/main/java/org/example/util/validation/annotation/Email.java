package org.example.util.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.util.validation.validator.UserEmailValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;

@Documented                                                                 //애노테이션 정보를 문서에 같이 보여줌
@Constraint(validatedBy = UserEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)                                         //해당 어노테이션을 언제까지 유지할지
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })   //해당 어노테이션이 어디에 사용될 수 있는지
public @interface Email {
    // annotation member method
    String message() default "Email is not allow";       //default message
    Class<?>[] groups() default {};                         //targeted group을 customize하기 위해 사용
    Class<? extends Payload>[] payload() default {};        //확장성을 위해 사용
}
