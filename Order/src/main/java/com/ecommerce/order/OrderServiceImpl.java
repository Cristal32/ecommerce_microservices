package com.ecommerce.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired 
	private OrderDao orderDao;
	
	@Autowired
	private RestTemplate restTemplate;
	
	// ---------------------------- get all orders ----------------------------
	@Override
	public List<Order> getAllOrders() {
		return orderDao.findAll();
	}
	
	// ---------------------------- find an order by id ----------------------------
	@Override
	public Order findOrderById(Long id) {
		return orderDao.findOrderById(id).orElse(null);
	}
	
	// ---------------------------- find orders by client ----------------------------
	@Override
	public List<Order> findOrdersByClient(Long clientId) {
		return orderDao.findOrdersByClientId(clientId).orElse(null);
	}
	
	// ---------------------------- add an order ----------------------------
	@Override
	public Order registerOrder(Order order) {
		return orderDao.save(order);
	}
	
	// ---------------------------- cancel an order ----------------------------
	@Override
	public void cancelOrder(Long orderId) {
		orderDao.deleteById(orderId);
	}
	
//	public void addOrder(OrderRegistrationRequest request) {
//        Order order = Order.builder()
//                .date(request.date())
//                .clientId(request.clientId())
//                .productId(request.productId)
//                .statut(request.statut())
//                .build();
//
//        // todo: check if email is valid
//        // todo: check if email not taken
//        // todo: store client in db
//        orderRepository.save(order);
//    }

}
