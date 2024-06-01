package com.ecommerce.feignclients.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.feignclients.product.ProductResponse;

@FeignClient(name = "client", url = "${clients.client.url}")
public interface CustomerClient {

    @GetMapping(path = "/getById/{id}")
    ProductResponse getCustomer(@PathVariable("id") Long id);
}
