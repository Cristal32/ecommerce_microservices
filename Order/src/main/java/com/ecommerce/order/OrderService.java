package com.ecommerce.order;

import java.util.List;

import com.ecommerce.order.model.Order;

public interface OrderService {
	public List<Order> getAllOrders();
	public Order findOrderById(Long orderId);
	public List<Order> findOrdersByClient(Long clientId);
	public Order registerOrder(Order order);
	public void cancelOrder(Long orderId);
}