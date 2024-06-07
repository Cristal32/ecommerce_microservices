package com.ecommerce.notification.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.ecommerce.notification.NotificationRequest;
import com.ecommerce.notification.NotificationService;

@Component
public class NotificationConsumer {
	private final NotificationService notificationService;
	
	public NotificationConsumer(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NotificationRequest notificationRequest) {
        System.out.println("Consumed {} from queue" + notificationRequest);
        notificationService.send(notificationRequest);
    }
}
