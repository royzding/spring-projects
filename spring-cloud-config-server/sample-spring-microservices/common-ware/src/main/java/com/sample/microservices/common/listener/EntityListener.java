package com.sample.microservices.common.listener;

import java.lang.reflect.Field;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.springframework.beans.factory.annotation.Value;

import com.sample.microservices.common.model.UserInfoStore;

public class EntityListener {
    
    private final UserInfoStore userInfoStore;
    
    @Value("${environment}")
    private String activeEnvironment;
    
    public EntityListener(final UserInfoStore userInfoStore) {
    	this.userInfoStore = userInfoStore;
    }
    
    @PrePersist
    @PreUpdate
    @PreRemove
    private void beforeAnyUpdate(Object target) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

    	String userName = "default-userName";
    	
    	System.out.println("[Entity AUDIT] About to update/delete target: " 
    	+ target.getClass().getSuperclass().getCanonicalName());
    	
    	Field field = target.getClass().getSuperclass().getDeclaredField("modifiedBy");
    	   	
    	if(activeEnvironment == null || activeEnvironment.equals("dev")) {
    		userName = "dev-";
    		userName += this.userInfoStore.getUserName();
    	} else {
    		userName = "prod-";
    		userName += this.userInfoStore.getUserName();    		
    	}
    	
    	field.setAccessible(true);
    	field.set(target, userName);

    }
    
    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate(Object target) {
        System.out.println("[Entity AUDIT] add/update/delete complete target: \" \r\n"
        		+ target.getClass().getSuperclass().getCanonicalName());
    }
    
    @PostLoad
    private void afterLoad(Object target) {
        System.out.println("[Entity AUDIT] Entity loaded from database: " 
        		+ target.getClass().getSuperclass().getCanonicalName());
    }

}
