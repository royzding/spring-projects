package com.sample.microservices.batch.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.sample.microservices.batch.exception.ResourceNotFoundException;
import com.sample.microservices.batch.exception.ValidationFailureException;

@Service
public class RetryableTaskServiceImpl implements RetryableTaskService{

	public RetryableTaskServiceImpl() {}
	
	static int count = 0;

	@Override
	public void resetCounter() {		
		count = 0;
	}
	
	@Override
	@Retryable(value=ValidationFailureException.class, maxAttempts = 5, backoff= @Backoff(delay = 1))
	public void executeTaskWithException(Integer times) {
				
		if(times <= count) {			
			throw new ResourceNotFoundException("try times: count = " + count);
		} else {
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$ executeTask: count=" + ++count);
			throw new ValidationFailureException("try times: count = " + count);
		}

	}
	
	@Override
	@Retryable(value=ValidationFailureException.class, maxAttempts = 5, backoff= @Backoff(delay = 1))
	public void executeTaskWithRecover() {
				
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$ executeTask: count=" + ++count);
		throw new ValidationFailureException("try times: count = " + count);

	}
	
	@Override
	@Retryable(value=ValidationFailureException.class, maxAttempts = 5, backoff= @Backoff(delay = 1))
	public Integer executeTaskWithSuccess(Integer times) {
				
		if(times <= count) {			
			return count;
		} else {
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$ executeTask: count=" + ++count);
			throw new ValidationFailureException("try times: count = " + count);
		}
		
	}
	
	@Recover
	void recover(ValidationFailureException e) {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$ recover with exception:" + e.getMessage());
	}
	
}
