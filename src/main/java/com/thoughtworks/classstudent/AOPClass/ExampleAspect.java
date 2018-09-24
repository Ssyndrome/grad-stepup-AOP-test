package com.thoughtworks.classstudent.AOPClass;

import com.thoughtworks.classstudent.AOPClass.annotation.LoggerStorage;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExampleAspect {

    @Autowired
    LoggerStorage logger;

    @Before("@annotation(com.thoughtworks.classstudent.AOPClass.annotation.DoLoggerBeforeExecution)")
    public void doBeforeLogger(JoinPoint joinPoint){
        logger.store(joinPoint.getSignature().getName());
    }

    @After("@annotation(com.thoughtworks.classstudent.AOPClass.annotation.DoLoggerAfterExecution)")
    public void doAfterLogger(JoinPoint joinPoint) {
        logger.store(joinPoint.getSignature().getName());
    }

    @Around("@annotation(com.thoughtworks.classstudent.AOPClass.annotation.DoLoggerAroundExecution)")
    public Object doAroundLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.store(joinPoint.getSignature().getName());
        Object proceed = joinPoint.proceed();
        logger.store(joinPoint.getSignature().getName());
        return proceed;
    }

    @AfterReturning("@annotation(com.thoughtworks.classstudent.AOPClass.annotation.DoLoggerWhenReturn)")
    public void doReturnLogger(JoinPoint joinPoint) {
        logger.store(joinPoint.getSignature().getName());
    }

    @AfterThrowing("@annotation(com.thoughtworks.classstudent.AOPClass.annotation.DoLoggerWhenThrow)")
    public void doThrowLogger(JoinPoint joinPoint) {
        logger.store(joinPoint.getSignature().getName());
    }
}
