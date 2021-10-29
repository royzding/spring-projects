package com.sample.microservices.kafka.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import com.sample.microservices.kafka.model.User;

public interface UserService {

    void createUserWithDefaultExecutor();

    Future<User> createAndReturnUser();

    void createUserWithThreadPoolTaskExecutor();

    void createUserWithConcurrentTaskExecutor();
    
    CompletableFuture <User> findUser(String user) throws InterruptedException;
}
