package com.async.test.executor;

import org.springframework.scheduling.annotation.Async;

import com.async.test.model.User;

import java.util.concurrent.Future;

public interface UserService {

    void createUserWithDefaultExecutor();

    Future<User> createAndReturnUser();

    void createUserWithThreadPoolExecutor();

    void createUserWithConcurrentExecutor();
}
