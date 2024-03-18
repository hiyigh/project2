package org.example.util.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.util.validation.annotation.Password;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class UserPasswordValidator implements ConstraintValidator<Password, String> {
    private int min;
    private int max;
    private final String allowNumber = "(?=.*[0-9])";
    private final String allowEnglish = "(?=.*[a-z])";
    private final String allowSpecial = "(?=.*[!\"#$%&'()*+,-./:;<=>?@^_`{|}\\[\\]~\\\\])(?=\\S+$)";
    private final String NotAllowSpace = "(?=\\S+$)";
    public final String passwordRegexp = "^"+allowNumber+allowEnglish+allowSpecial+NotAllowSpace+".{5,20}$";
    @Override
    public void initialize(Password constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        value = URLDecoder.decode(value, StandardCharsets.UTF_8);

        if(!value.matches(passwordRegexp)){
            context.buildConstraintViolationWithTemplate("비밀번호는 숫자, 영어, 특수문자 각각 1개 이상 포함하고 "+min+"자 ~ "+ max +"자로 입력해주세요").addConstraintViolation();
            return false;
        }
        return true;
    }
}
