package com.ecommerce.amqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer {
	private final AmqpTemplate amqpTemplate;
	
	public RabbitMQProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void publish(String exchange, String routingKey, Object payload) {
        System.out.println("Publishing to " + exchange + " using routingKey " + routingKey + " with payload " + payload);
        amqpTemplate.convertAndSend(exchange, routingKey, payload);
        System.out.println("Publishing to " + exchange + " using routingKey " + routingKey + " with payload " + payload);
    }
}
