//package org.example.config.aspect;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//@Slf4j
//public class LoggingAspect {
//    @Before("execution()")
//    public void logBefore(JoinPoint joinPoint){
//        log.info("Before: " + joinPoint.getSignature().getName()); // 현재 실행중인 메서드 이름 반환
//    }
//    @After("execution()")
//    public void logAfter(JoinPoint joinPoint) {
//        log.info("After: " + joinPoint.getSignature().getName());
//    }
//    @AfterReturning("execution()")
//    public void logAfterReturning(JoinPoint joinPoint){
//        log.info("After Returning: " + joinPoint.getSignature().getName());
//    }
//    @AfterThrowing(pointcut = "execution()", throwing = "e")
//    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
//        log.info("AfterThrowing: " + joinPoint.getSignature().getName() + " exception: " + e.getMessage());
//    }
//    @Around("execution()")
//    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        log.info("Around before: " + joinPoint.getSignature().getName());
//        Object result = joinPoint.proceed();
//        log.info("Around after: " + joinPoint.getSignature().getName());
//        return result;
//    }
//
//}
