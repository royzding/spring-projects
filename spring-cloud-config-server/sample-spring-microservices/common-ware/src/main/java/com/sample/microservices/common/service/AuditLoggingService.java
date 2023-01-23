package com.sample.microservices.common.service;

import org.aspectj.lang.ProceedingJoinPoint;

import com.sample.microservices.common.enums.ActivityType;

public interface AuditLoggingService {
	
	Object auditActivity(ProceedingJoinPoint point, ActivityType type) throws Throwable;

}
