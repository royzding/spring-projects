package com.sample.microservices.common.annotation.aspect;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sample.microservices.common.annotation.Loggable;

/**
 * Class for logging input and output parameters of any method with annotation @Loggable.
 */
@Aspect
@Component
public final class LoggableAspect {
    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * @param jp - ProceedingJointPoint
     * @param loggable - Loggable
     * @return returns the next executable point to proceed in target
     * @throws Throwable - throws exception when proceeding with joint point
     */
    @Around("execution(* *(..)) && @annotation(loggable)")
    public Object loggingAroundMethod(@NotNull final ProceedingJoinPoint jp,
                                      @NotNull final Loggable loggable) throws Throwable {
        final String signature = jp.getTarget().getClass().getName() + '.' + jp.getSignature().getName();
        final List<Object> arguments = Arrays.asList(jp.getArgs());
        
        System.out.println("LoggableAspect: loggingAroundMethod");

        final Object result;
        try {
            doLog(loggable.level(), "[BEFORE] {}{}", signature, arguments);
            result = jp.proceed();
            doLog(loggable.level(), "[AFTER] {}{} result={}", signature, arguments, result);
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
    private void doLog(@NotNull final Loggable.Level level, @NotNull final String format, final Object... arguments) {
       switch (level) {
            case DEBUG:
                 log.debug(format, arguments);
                 break;

            case INFO:
                 log.info(format, arguments);
                 break;

            case WARN:
                 log.warn(format, arguments);
                 break;

            case ERROR:
                 log.error(format, arguments);
                 break;

            default:
                 log.error("Unable to appropriately handle given log level={}", level, format, arguments);
         }
       
       	log.error("end log level={}", level, format, arguments);
       	
      }
    }
