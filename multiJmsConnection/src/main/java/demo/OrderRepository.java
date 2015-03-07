package demo;

import java.util.Collection;

import demo.model.Order;

public interface OrderRepository {
	
	Collection<Order> findAll();
	
	Order save(Order order);

	Order modify(Order order);
}
