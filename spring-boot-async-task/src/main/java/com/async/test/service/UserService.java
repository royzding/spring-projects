package com.async.test.service;

import java.util.concurrent.Future;

import com.async.test.model.User;

public interface UserService {

    void createUserWithDefaultExecutor();

    Future<User> createAndReturnUser();

    void createUserWithThreadPoolExecutor();

    void createUserWithConcurrentExecutor();
}
