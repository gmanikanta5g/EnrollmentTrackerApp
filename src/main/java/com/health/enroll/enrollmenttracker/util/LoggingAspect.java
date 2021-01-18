package com.health.enroll.enrollmenttracker.util;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * Logging Aspect with Before and After advices to perform Logging after method executions 
 * @author gmani
 *
 */
@Aspect
@Component
public class LoggingAspect {

    private static  final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    
    @Before("execution(* com.health.enroll.enrollmenttracker..*.*(..))")
    public void before(JoinPoint joinPoint){
        logger.info("Enter - "+joinPoint.getSignature().getDeclaringTypeName()+"." +joinPoint.getSignature().getName());
    }

    @After("execution(* com.health.enroll.enrollmenttracker..*.*(..))")
    public void after(JoinPoint joinPoint){
        logger.info("Exit - "+joinPoint.getSignature().getDeclaringTypeName()+"." +joinPoint.getSignature().getName());
    }

}
