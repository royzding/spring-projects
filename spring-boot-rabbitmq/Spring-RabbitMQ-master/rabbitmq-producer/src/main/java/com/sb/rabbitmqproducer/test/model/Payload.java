package com.sb.rabbitmqproducer.test.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payload {
    private String message;
}
