package com.ecommerce.feignclients.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "product", url = "${clients.product.url}")
public interface ProductClient {

    @GetMapping(path = "/getById/{id}")
    ProductDTO getProduct(@PathVariable("id") Long id);
    
    @PutMapping("/update/{id}")
    void updateProductQuantity(@PathVariable("id") Long id, UpdateProductRequest request);
}
