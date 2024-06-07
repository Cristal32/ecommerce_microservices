package com.ecommerce.feignclients.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth", url = "${clients.auth.url}")
public interface UserClient {

    @GetMapping(path = "/getById/{id}")
    UserDTO findUserById(@PathVariable("id") Long id);
    
    @GetMapping(path = "/getByUsername/{username}")
    UserDTO findUserByUsername(@PathVariable("username") String username);
}
