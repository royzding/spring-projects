package com.sb.rabbitmqconsumer.test.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payload {
    private String message;
}
