package org.example.util.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.util.validation.annotation.Name;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Component
public class UserNameValidator implements ConstraintValidator<Name, String> {
    private int min;
    private int max;
    @Override
    public void initialize(Name constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        value = URLDecoder.decode(value, StandardCharsets.UTF_8);

        if(!StringUtils.hasText(value)) {
            context.buildConstraintViolationWithTemplate("공백일 수 없습니다.")
                    .addConstraintViolation();
            return false;
        }
        if (max < value.length() || value.length() < min) {
            context.buildConstraintViolationWithTemplate("이름은 "+min+"자~"+max+"자 사이로 입력하세요")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
