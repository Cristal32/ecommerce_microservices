package com.ecommerce.order;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.amqp.RabbitMQProducer;
import com.ecommerce.feignclients.customer.CustomerClient;
import com.ecommerce.feignclients.notification.NotificationRequest;
import com.ecommerce.feignclients.order.OrderRequest;
import com.ecommerce.feignclients.product.ProductClient;
import com.ecommerce.feignclients.product.ProductDTO;
import com.ecommerce.feignclients.product.UpdateProductRequest;

@Service
public class OrderServiceImpl implements OrderService {
	
//	private static final String CLIENT_SERVICE_URL = "http://localhost:8222/api/client/getById/{clientId}";
//    private static final String PRODUCT_SERVICE_URL = "http://localhost:8222/api/product/getById/{productId}";
	
	@Autowired 
	private OrderDao orderDao;
	
//	@Autowired
//	private RestTemplate restTemplate;
	
	@Autowired
    private RabbitMQProducer rabbitMQProducer;
	
	@Autowired
	private ProductClient productClient;
	
	@Autowired
	private CustomerClient customerClient;
	
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
//	public Order registerOrder(OrderRequest order) {
//        // Check if the client exists
//		customerClient.getCustomer(order.getClientId());
//
//        // Check if the product exists
//		productClient.getProduct(order.getProductId());
//		
//		// Create order
//        Order orderEntity = orderDao.save(Order.builder()
//        		.id(null)
//                .clientId(order.getClientId())
//                .productId(order.getProductId())
//                .date(LocalDateTime.now())
//				.amount(order.getAmount())
//                .build());
//		
//		// Create notificationRequest
//        NotificationRequest notificationRequest = NotificationRequest.builder()
//                .clientId(order.getClientId())
//                .clientEmail(order.getClientEmail())
//                .sender("ecommerce")
//                .msg("Your order has been successful.")
//                .build();
//        
//        // Send notification
//        rabbitMQProducer.publish("internal.exchange", "internal.notification.routing-key", notificationRequest);
//        
//        return orderEntity;
//    }
	
	@Override
	public Order registerOrder(OrderRequest order) {
	    // Check if the order amount is less than or equal to the product quantity
		// Check if the client exists
		customerClient.getCustomer(order.getClientId());

        // Check if the product exists
	    ProductDTO productDTO = productClient.getProduct(order.getProductId());
	    int newStockQuantity = productDTO.getStockQuantity() - order.getAmount();
	    System.out.println("new stock quantity = " + newStockQuantity);
	    if (newStockQuantity < 0) {
	        throw new IllegalArgumentException("Order amount exceeds available stock quantity");
	    }

	    // Create the order
	    Order orderEntity = orderDao.save(Order.builder()
	            .clientId(order.getClientId())
	            .productId(order.getProductId())
	            .date(LocalDateTime.now())
	            .amount(order.getAmount())
	            .build());
	    
	    // Create notificationRequest
	    NotificationRequest notificationRequest = NotificationRequest.builder()
              .clientId(order.getClientId())
              .clientEmail(order.getClientEmail())
              .sender("ecommerce")
              .msg("Your order has been successful.")
              .build();
      
	      // Send notification
	      rabbitMQProducer.publish("internal.exchange", "internal.notification.routing-key", notificationRequest);
      

	    // Update the product quantity
	      productClient.updateProductQuantity(order.getProductId(), new UpdateProductRequest(
	              null, // Set null for ID as we only need to update the stockQuantity
	              null,
	              null,
	              null,
	              null,
	              null,
	              newStockQuantity, // Set the new stockQuantity value
	              null
	      ));

	    return orderEntity;
	}
	
//	@Override
//	public Order addOrder(Order order) {
//		return orderDao.save(order);
//	}
	
//	public Order registerOrder(Order order) {
//        // Check if the client exists
//        ResponseEntity<ClientDTO> clientResponse = restTemplate.getForEntity(CLIENT_SERVICE_URL, ClientDTO.class, order.getClientId());
//        if (clientResponse.getStatusCode() != HttpStatus.OK) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client does not exist");
//        }
//
//        // Check if the product exists
//        ResponseEntity<ProductDTO> productResponse = restTemplate.getForEntity(PRODUCT_SERVICE_URL, ProductDTO.class, order.getProductId());
//        if (productResponse.getStatusCode() != HttpStatus.OK) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product does not exist");
//        }
//
//        // If both client and product exist, save the order
//        return orderDao.save(order);
//    }
	
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
