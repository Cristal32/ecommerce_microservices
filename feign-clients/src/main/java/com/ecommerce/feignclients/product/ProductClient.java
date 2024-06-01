package com.ecommerce.feignclients.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product", url = "${clients.product.url}")
public interface ProductClient {

    @GetMapping(path = "/getById/{id}")
    ProductResponse getProduct(@PathVariable("id") Long id);
}
