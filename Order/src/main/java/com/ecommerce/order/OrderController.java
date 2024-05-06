package com.ecommerce.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/order")
public record OrderController(OrderService orderService) {
    private static final Logger logger = Logger.getLogger(OrderController.class.getName());

    @GetMapping("all")
    public List<Order> getAllOrders() {return orderService.getAllOrders();}

    @GetMapping("order/{id}")
    public Order getOrder(@PathVariable("id") Integer id) {return orderService.getOrder(id);}

    @PostMapping("new")
    public Order registerOrder(@RequestBody RegisterOrderRequest registerOrderRequest) throws IOException {
        return orderService.registerOrder(registerOrderRequest);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully!");
    }

    @PutMapping("update/{id}")
    public Order updateOrder(@PathVariable("id") Integer id, @RequestBody UpdateOrderRequest updateOrderRequest) {
        try {
            return orderService.updateOrder(id, updateOrderRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}