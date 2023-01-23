package com.sample.microservices.common.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.sample.microservices.common.enums.ActivityType;

@Service
public class AuditLoggingServiceImpl implements AuditLoggingService {

	@Override
	public Object auditActivity(ProceedingJoinPoint point, ActivityType type) throws Throwable {
		
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        
        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        
        String targetClassName = point.getTarget().getClass().getSimpleName();
        String targetMethodName = point.getSignature().getName();
        Object[] args = point.getArgs();       

        StopWatch timer = new StopWatch();
		
		timer.start();
		
		try {
				Object value = point.proceed();
				
				timer.stop();
				
				return value;
			
		} catch (Throwable t) {
			
			timer.stop();			
			throw t;
			
		} finally {
			
			System.out.println("AuditActivity:" + className +":"+ methodName + "" + type + ":time["+ timer.getTotalTimeMillis()+"millis]");
			
		}

	}

}
