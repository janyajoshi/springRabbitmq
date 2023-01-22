package com.lazybeast.rabbitmq.publisher;

import com.lazybeast.rabbitmq.config.AppConstants;
import com.lazybeast.rabbitmq.config.MessagingConfig;
import com.lazybeast.rabbitmq.dto.Order;
import com.lazybeast.rabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
        order.setOrderId(UUID.randomUUID().toString());
        //restaurantservice
        //payment service
        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "order placed succesfully in " + restaurantName);
        rabbitTemplate.convertAndSend(AppConstants.EXCHANGE_1, AppConstants.ROUTING_KEY_1, orderStatus);
        return "Success !!";
    }
}
