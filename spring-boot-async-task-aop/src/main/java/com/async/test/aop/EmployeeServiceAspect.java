package com.async.test.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class EmployeeServiceAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceAspect.class);
	
    //AOP expression for which methods shall be intercepted
    @Around("execution(* com.async.test.service..*(..)))")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable 
    {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
         
        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        
        String targetClassName = proceedingJoinPoint.getTarget().getClass().getSimpleName();
        String targetMethodName = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();       
         
        final StopWatch stopWatch = new StopWatch();
        
        Object result = null;
        
        try {
        	
            //Measure method execution time
            stopWatch.start();
            result = proceedingJoinPoint.proceed();
            stopWatch.stop();
            
        } catch(Exception ex) {
        	
        } finally {
            //Do Something useful, If you have
        }
 
        //Log method execution time
        LOGGER.info("Execution time of " + className + "." + methodName + " :: " + stopWatch.getTotalTimeMillis() + " ms");
 
        return result;
    }	
    

    @Around("execution(* com.async.test.service.EmployeeService.*(..))")
    public void logAroundAllMethods(ProceedingJoinPoint joinPoint) throws Throwable 
    {
        System.out.println("****LoggingAspect.logAroundAllMethods() : " + joinPoint.getSignature().getName() + ": Before Method Execution");
        try {
            joinPoint.proceed();
        } finally {
            //Do Something useful, If you have
        }
        System.out.println("****LoggingAspect.logAroundAllMethods() : " + joinPoint.getSignature().getName() + ": After Method Execution");
    }
 
    @Around("execution(* com.async.test.service.EmployeeService.getEmployee(..))") 
    public void logAroundGetEmployee(ProceedingJoinPoint joinPoint) throws Throwable 
    {
        System.out.println("****LoggingAspect.logAroundGetEmployee() : " + joinPoint.getSignature().getName() + ": Before Method Execution");
        try {
            joinPoint.proceed();
        } finally {
            //Do Something useful, If you have
        }
        System.out.println("****LoggingAspect.logAroundGetEmployee() : " + joinPoint.getSignature().getName() + ": After Method Execution");
    }
 
    @Around("execution(* com.async.test.service.EmployeeService.createEmployee(..))")
    public void logAroundCreateEmployee(ProceedingJoinPoint joinPoint) throws Throwable 
    {
        System.out.println("****LoggingAspect.logAroundCreateEmployee() : " + joinPoint.getSignature().getName() + ": Before Method Execution");
        
        try {
            joinPoint.proceed();
        } finally {
            //Do Something useful, If you have
        }
        System.out.println("****LoggingAspect.logAroundCreateEmployee() : " + joinPoint.getSignature().getName() + ": After Method Execution");
    }    
}
