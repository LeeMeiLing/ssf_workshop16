package ibf2022.batch2.ssf.ssf_workshop16.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.batch2.ssf.ssf_workshop16.model.Order;
import ibf2022.batch2.ssf.ssf_workshop16.repository.OrderRepo;

@Service
public class OrderService {
    
    @Autowired
	private OrderRepo orderRepo;

	public String createNewOrder(Order order) {
        
		// Generating the orderId
		String orderId = UUID.randomUUID().toString().substring(0, 8);
		order.setOrderId(orderId);

		// Save the order
		orderRepo.insertOrder(order);
		orderRepo.insertOrder2(order);

		return orderId;
	}
    
}
