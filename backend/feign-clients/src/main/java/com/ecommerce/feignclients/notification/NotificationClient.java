package com.ecommerce.feignclients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification", url = "${clients.notification.url}")
public interface NotificationClient {

    @PostMapping(path = "/send")
    NotificationResponse sendNotification(@RequestBody NotificationRequest notificationRequest);
}