package demo;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.model.Order;

@Component
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}
	
	public Collection<Order> findAll() {
		return orderRepository.findAll();
	}
	
}
