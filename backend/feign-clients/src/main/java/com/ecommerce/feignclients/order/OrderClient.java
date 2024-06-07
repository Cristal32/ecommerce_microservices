package com.ecommerce.feignclients.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order", url = "${clients.order.url}")
public interface OrderClient {
    @GetMapping(path = "/getById/{id}")
    OrderResponse getOrder(@PathVariable("id") Long orderId);

    @PostMapping(path = "/add")
    OrderResponse createOrder(@RequestBody OrderRequest orderRequest);
}