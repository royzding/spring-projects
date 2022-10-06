package com.sample.microservices.common.annotation.test;

import com.sample.microservices.common.annotation.JsonField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @JsonField("Maker")
    private String make;

    @JsonField("MODEL")
    private String model;

    @JsonField
    private int year;


    @Override
    public String toString() {
        return year + " " + make + " " + model;
    }
}