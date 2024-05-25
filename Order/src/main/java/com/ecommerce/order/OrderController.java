package com.ecommerce.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

@RestController
@RequestMapping("api/order")
public class OrderController {
    private static final Logger logger = Logger.getLogger(OrderController.class.getName());

//    @PostMapping
//    public void registerOrder(@RequestBody OrderRegistrationRequest orderRegistrationRequest) {
//        orderService.registerOrder(orderRegistrationRequest);
//        logger.info("New order registration " + orderRegistrationRequest);
//    }
    
    @Autowired
	private final OrderService orderService;
	
	//constructeur
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	// ================================= GET Mapping =================================
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Order>> getAllOrders(){
		List<Order> orders = orderService.getAllOrders();
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id){
		Order order = orderService.findOrderById(id);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	// ================================= POST Mapping =================================
	
	@PostMapping("/add")
	public ResponseEntity<Order> addOrder(@RequestBody Order order){
		Order newOrder = orderService.registerOrder(order);
		return new ResponseEntity<>(newOrder, HttpStatus.CREATED); 
	}
	
	// ================================= DELETE Mapping =================================
	@Transactional
	@DeleteMapping("/cancel/{id}")
	public ResponseEntity<?> cancelOrder(@PathVariable("id") Long orderId){
		orderService.cancelOrder(orderId);
		return new ResponseEntity<>(HttpStatus.OK); 
	}
}