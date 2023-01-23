package com.sample.microservices.common.annotation.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sample.microservices.common.enums.ActivityType;
import com.sample.microservices.common.service.AuditLoggingService;

/**
 * Class for audit logging.
 */
@Aspect
@Component
public final class AuditLoggingAspect {
    private final Logger log = LoggerFactory.getLogger(getClass());
    
    private final AuditLoggingService auditLoggingService;
    
    AuditLoggingAspect(AuditLoggingService auditLoggingService) {
    	this.auditLoggingService = auditLoggingService;
    	
    }
    
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMapping() {}
    
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMapping() {}
    
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void putMapping() {}
    
    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void deleteMapping() {}
    
    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void transactionalMapping() {}
    
    @Pointcut("execution(* *(..)) && ("
    		+ "within(com.sample.microservices.department.controller.*) || "
    		+ "within(com.sample.microservices.employee.controller.*) " 
    		+ ")")
    public void withinController() {
    	//is called by auditAllMethods
    }

    @Around("getMapping() && withinController()")
    public Object auditgetMappingMethods(ProceedingJoinPoint point) throws Throwable 
    {
    	return this.auditLoggingService.auditActivity(point, ActivityType.GET);
    }	
    
    @Around("postMapping() && withinController()")
    public Object auditPostMappingMethods(ProceedingJoinPoint point) throws Throwable 
    {
    	return this.auditLoggingService.auditActivity(point, ActivityType.POST);
    }	
    
}