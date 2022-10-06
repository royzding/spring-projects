package com.sample.microservices.common.annotation.aspect;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Class for any method in the class with annotation @LoggableType.
 */
@Aspect
@Component
public final class LoggableTypeAspect {
    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * @param jp - ProceedingJointPoint
     * @return returns the next executable point to proceed in target
     * @throws Throwable - throws exception when proceeding with joint point
     */
    @Around("@within(com.sample.microservices.common.annotation.LoggableType)")
    public Object loggingAroundMethod(ProceedingJoinPoint jp) throws Throwable {
        final String signature = jp.getTarget().getClass().getName() + '.' + jp.getSignature().getName();
        final List<Object> arguments = Arrays.asList(jp.getArgs());
        
        System.out.println("LoggableTypeAspect: loggingAroundMethod");
        
        StopWatch timer = new StopWatch();
        
        timer.start();

        final Object result;
        
        try {

        	result = jp.proceed();
        	
        	timer.stop();
        	
        	log.info("LoggableTypeAspect: executed {} successfully. Time taken: {}", signature, timer.getTotalTimeMillis());

        } catch (Exception e) {
            log.error("[AFTER] {}{} exception={}", signature, arguments, e);
            throw e;
        }

        return result;
    }

}
