package com.sample.microservices.common.annotation.test;

public class JsonSerializeException extends Exception {

    private static final long serialVersionUID = -8845242379503538623L;

    public JsonSerializeException(String message) {
        super(message);
    }
}