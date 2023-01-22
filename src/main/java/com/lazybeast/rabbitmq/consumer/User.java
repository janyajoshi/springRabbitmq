package com.lazybeast.rabbitmq.consumer;

import com.lazybeast.rabbitmq.config.AppConstants;
import com.lazybeast.rabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

    @RabbitListener(queues = AppConstants.QUEUE_1)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.println("Message recieved from queue : " + orderStatus);
    }
}
