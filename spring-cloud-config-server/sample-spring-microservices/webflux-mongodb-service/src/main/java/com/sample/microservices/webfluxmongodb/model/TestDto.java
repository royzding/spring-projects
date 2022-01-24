package com.sample.microservices.webfluxmongodb.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TestDto {

    @NotEmpty
    private String message;

    @NotEmpty
    private String name;

    @NotNull
    private Integer age;

}