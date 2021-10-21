package com.sample.microservices.common.listener;

import java.lang.reflect.Field;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class EntityListener {

    private static String userName = "eUserName";
    
    @PrePersist
    @PreUpdate
    @PreRemove
    private void beforeAnyUpdate(Object target) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

    	System.out.println("[Entity AUDIT] About to update/delete target: " 
    	+ target.getClass().getSuperclass().getCanonicalName());
    	
    	Field field = target.getClass().getSuperclass().getDeclaredField("modifiedBy");
    	
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
