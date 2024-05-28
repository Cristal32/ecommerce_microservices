package com.ecommerce.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.order.model.Client;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.model.Product;
@Service
public class OrderServiceImpl implements OrderService {
	
	private static final String CLIENT_SERVICE_URL = "http://localhost:8222/api/client/getById/{clientId}";
    private static final String PRODUCT_SERVICE_URL = "http://localhost:8222/api/product/getById/{productId}";
	
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
//	@Override
//	public Order addOrder(Order order) {
//		return orderDao.save(order);
//	}
	
	public Order registerOrder(Order order) {
        // Check if the client exists
        ResponseEntity<Client> clientResponse = restTemplate.getForEntity(CLIENT_SERVICE_URL, Client.class, order.getClientId());
        if (clientResponse.getStatusCode() != HttpStatus.OK) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client does not exist");
        }

        // Check if the product exists
        ResponseEntity<Product> productResponse = restTemplate.getForEntity(PRODUCT_SERVICE_URL, Product.class, order.getProductId());
        if (productResponse.getStatusCode() != HttpStatus.OK) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product does not exist");
        }

        // If both client and product exist, save the order
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
