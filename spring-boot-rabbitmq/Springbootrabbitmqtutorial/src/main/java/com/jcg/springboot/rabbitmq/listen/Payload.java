package com.jcg.springboot.rabbitmq.listen;

import lombok.*;

@Data
public class Payload {
    private String message1;
    private String message2;
    private Context context;
    private Integer taskId;
}
