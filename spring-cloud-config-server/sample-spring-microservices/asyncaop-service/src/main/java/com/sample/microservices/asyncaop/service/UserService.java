package com.sample.microservices.asyncaop.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import com.sample.microservices.asyncaop.model.User;

public interface UserService {

    void createUserWithDefaultExecutor();

    Future<User> createAndReturnUser();

    void createUserWithThreadPoolTaskExecutor();

    void createUserWithConcurrentTaskExecutor();
    
    CompletableFuture <User> findUser(String user) throws InterruptedException;
}
