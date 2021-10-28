package com.sample.microservices.kafka.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.sample.microservices.kafka.model.User;

@Service
public class UserServiceImpl implements UserService {

    @Override
    @Async
    public void createUserWithDefaultExecutor(){
        //SimpleAsyncTaskExecutor
        System.out.println("createUserWithDefaultExecutor: Currently Executing thread name - " + Thread.currentThread().getName());
    }

    @Override
    @Async("threadPoolTaskExecutor")
    public void createUserWithThreadPoolTaskExecutor(){
        System.out.println("createUserWithThreadPoolTaskExecutor: Currently Executing thread name - " + Thread.currentThread().getName());
    }

    @Override
    @Async("ConcurrentTaskExecutor")
    public void createUserWithConcurrentTaskExecutor(){
        System.out.println("createUserWithConcurrentTaskExecutor: Currently Executing thread name - " + Thread.currentThread().getName());
    }
    
    @Override
    @Async
    public Future<User> createAndReturnUser() {
        System.out.println("createAndReturnUser: Currently Executing thread name - " + Thread.currentThread().getName());
        try {
            User user = new User();
            user.setFirstName("John");
            user.setLastName("Doe");
            user.setGender("Male");
            Thread.sleep(5000);
            return new AsyncResult<User>(user);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    @Async("threadPoolTaskExecutor")
    public CompletableFuture <User> findUser(String user) throws InterruptedException {
        System.out.println("findUser: Currently Executing thread name - " + Thread.currentThread().getName());
        
        User userx = new User();
        userx.setFirstName(user + "-fn");
        userx.setLastName(user + "-ln");
        userx.setGender(user + "-g");
        
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);
        
        return CompletableFuture.completedFuture(userx);
    }    

}
