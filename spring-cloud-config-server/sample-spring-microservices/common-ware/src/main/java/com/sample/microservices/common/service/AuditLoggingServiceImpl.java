package com.sample.microservices.common.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.sample.microservices.common.enums.ActivityType;

@Service
public class AuditLoggingServiceImpl implements AuditLoggingService {

	@Override
	public Object auditActivity(ProceedingJoinPoint point, ActivityType type) throws Throwable {
		
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
			
			System.out.println("AuditActivity:" + type + ":time["+ timer.getTotalTimeMillis()+"millis]");
			
		}

	}

}
