package com.ecommerce.feignclients.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "client", url = "${clients.client.url}")
public interface CustomerClient {

    @GetMapping(path = "/getById/{id}")
    ClientDTO getCustomer(@PathVariable("id") Long id);
    
    @PostMapping(path = "/add")
    ClientDTO createClient(@RequestBody ClientDTO client);
}
