package com.async.test.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.async.test.model.User;

import java.util.concurrent.Future;

@Service
public class UserServiceImpl implements UserService {

    @Override
    @Async
    public void createUserWithDefaultExecutor(){
        //SimpleAsyncTaskExecutor
        System.out.println("createUserWithDefaultExecutor: Currently Executing thread name - " + Thread.currentThread().getName());
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
    @Async("threadPoolExecutor")
    public void createUserWithThreadPoolExecutor(){
        System.out.println("createUserWithThreadPoolExecutor: Currently Executing thread name - " + Thread.currentThread().getName());
    }

    @Override
    @Async("ConcurrentTaskExecutor")
    public void createUserWithConcurrentExecutor(){
        System.out.println("createUserWithConcurrentExecutor: Currently Executing thread name - " + Thread.currentThread().getName());
    }

}
