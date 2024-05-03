package com.ecommerce.order;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("api/order")
public record OrderController(OrderService orderService) {
    private static final Logger logger = Logger.getLogger(OrderController.class.getName());

    @PostMapping
    public void registerOrder(@RequestBody OrderRegistrationRequest orderRegistrationRequest) {
        orderService.registerOrder(orderRegistrationRequest);
        logger.info("New order registration " + orderRegistrationRequest);
    }
}
