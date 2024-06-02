package com.ecommerce.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {
                "com.ecommerce.notification",
                "com.ecommerce.amqp",
        }
)
@EnableEurekaClient
@PropertySources({
        @PropertySource("classpath:amqp-${spring.profiles.active}.properties")
})
public class NotificationApplication {
	public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }
}
