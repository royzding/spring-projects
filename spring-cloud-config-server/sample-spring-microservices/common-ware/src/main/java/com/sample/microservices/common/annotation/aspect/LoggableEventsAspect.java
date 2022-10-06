package com.sample.microservices.common.annotation.aspect;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sample.microservices.common.annotation.EventType;
import com.sample.microservices.common.annotation.LoggableEvents;

/**
 * Class for logging input and output parameters of any method with annotation @LoggableEvents.
 */
@Aspect
@Component
public final class LoggableEventsAspect {
    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * @param jp - ProceedingJointPoint
     * @param loggable - LoggableEvents
     * @return returns the next executable point to proceed in target
     * @throws Throwable - throws exception when proceeding with joint point
     */
    @Around("@annotation(loggableEvents)")
    public Object loggingAroundMethod(ProceedingJoinPoint jp, LoggableEvents loggableEvents) throws Throwable {
        final String signature = jp.getTarget().getClass().getName() + '.' + jp.getSignature().getName();
        final List<Object> arguments = Arrays.asList(jp.getArgs());
        
        System.out.println("LoggableEventsAspect: loggingAroundMethod");

        final Object result;
        try {
            doLog(jp, loggableEvents.type(), arguments);
            result = jp.proceed();
            doLog(jp, loggableEvents.type(), arguments);
        } catch (Exception e) {
            log.error("[AFTER] {}{} exception={}", signature, arguments, e);
            throw e;
        }

        return result;
    }

    /**
     * Logs the message with appropriate log level.
     * @param level - level to log
     * @param format - format for logging
     * @param arguments - arguments for logging
    */
    private void doLog(ProceedingJoinPoint jp, EventType eventType, final Object... arguments) {
       switch (eventType) {
            case CREATE:
                System.out.println("doLog:" + eventType);
                return;

            case READ:
                System.out.println("doLog:" + eventType);
                return;

            case UPDATE:
                System.out.println("doLog:" + eventType);
                return;

            case DELETE:
                System.out.println("doLog:" + eventType);
                return;

            default:
                 log.error("Unable to appropriately handle given log level={}", eventType);
         }
       
       System.out.println("doLog:" + eventType);
       return;
       
      }
    }
