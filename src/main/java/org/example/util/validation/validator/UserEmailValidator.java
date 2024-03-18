package org.example.util.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.util.validation.annotation.Email;
import org.springframework.stereotype.Component;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Component
public class UserEmailValidator implements ConstraintValidator<Email, String> {
    String regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
    @Override
    public void initialize(Email constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation(); // 기본 메세지 비활성화
        value = URLDecoder.decode(value, StandardCharsets.UTF_8);
        // url 넘어오는 특수문자 처리
        if(!value.matches(regexp)) {
            context.buildConstraintViolationWithTemplate("이메일이 형식과 맞지 않습니다.")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
