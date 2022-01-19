package com.sample.microservices.batch.service;

public interface RetryableTaskService {

	void resetCounter();
	Integer executeTaskWithSuccess(Integer times);
	void executeTaskWithException(Integer times);
	
}
