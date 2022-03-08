package com.sample.microservices.rabbitmq.model;

import lombok.*;

@Data
public class Payload {
    private String message1;
    private String message2;
    private Context context;
    private Integer taskId;
	@Override
	public String toString() {
		return "Payload [message1=" + message1 + ", message2=" + message2 + ", context=" + context + ", taskId="
				+ taskId + "]";
	}   
}
