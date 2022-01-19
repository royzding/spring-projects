package com.sample.microservices.batch.service;

public interface RetryableTaskService {

	void resetCounter();
	void executeTaskWithRecover();
	Integer executeTaskWithSuccess(Integer times);
	void executeTaskWithException(Integer times);
	
}
