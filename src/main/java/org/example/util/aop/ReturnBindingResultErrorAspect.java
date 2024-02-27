package org.example.util.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
@Aspect
@Slf4j
public class ReturnBindingResultErrorAspect {
    @Pointcut("@annotation(org.example.util.aop.ReturnBindingResultError)")
    private void Target() {}

    @Around("Target()")
    public Object aspectAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String type = joinPoint.getSignature().getDeclaringTypeName();
        String method = joinPoint.getSignature().getName();
        log.info("BindingResultAOP Run : type={}", type);
        log.info("BindingResultAOP Run : method={}", method);

        Object[] args = joinPoint.getArgs();

        for(Object arg : args) {
            if(arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg;

                if(bindingResult.hasErrors()){
                    log.info("BindingResultAOP : bindingResult.hasErrors() == true");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult);
                }
            }
        }
        return joinPoint.proceed();
    }
}
