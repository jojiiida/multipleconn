package demo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;

import demo.model.Order;

@Configuration
@EnableAutoConfiguration(exclude=JmsAutoConfiguration.class)
@EnableConfigurationProperties({ActiveMQProperties.class, AutoPricingRequestJmsConnectionProperties.class, ExportJmsConnectionProperties.class})
public class MultiJmsConnectionConfiguration {
	
	@Autowired
	private  ExportJmsConnectionProperties exportProperties;

	@Autowired
	private  AutoPricingRequestJmsConnectionProperties autoPricingRequestProperties;

	
	public MultiJmsConnectionConfiguration() {

	}
	
	@Bean
	@Primary
	public UserCredentialsConnectionFactoryAdapter autoPricingRequestConnectionFactory() {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(autoPricingRequestProperties.getBrokerUrl());
		UserCredentialsConnectionFactoryAdapter adapter = new UserCredentialsConnectionFactoryAdapter();
		adapter.setUsername(autoPricingRequestProperties.getUser());
		adapter.setPassword(autoPricingRequestProperties.getPassword());
		adapter.setTargetConnectionFactory(connectionFactory);
		return adapter;
	}
	
	@Bean
	public UserCredentialsConnectionFactoryAdapter exportConnectionFactory() {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(exportProperties.getBrokerUrl());
		UserCredentialsConnectionFactoryAdapter adapter = new UserCredentialsConnectionFactoryAdapter();
		adapter.setUsername(exportProperties.getUser());
		adapter.setPassword(exportProperties.getPassword());
		adapter.setTargetConnectionFactory(connectionFactory);
		return adapter;
	}
	
	@Bean
	public OrderRepository orderRepository() {
		return new OrderRepositoryMapImpl();
	}
	
	private class OrderRepositoryMapImpl implements OrderRepository {

		private Map<Long, Order> orderMap = new HashMap<Long, Order>();
		
		private Long id = 0L;
		@Override
		public Collection<Order> findAll() {
			return orderMap.values();
		}

		@Override
		public Order save(Order order) {
			Long newId = id++;
			order.setId(newId);
			orderMap.put(newId, order);
			return order;
		}

		@Override
		public Order modify(Order order) {
			orderMap.put(order.getId(), order);
			return order;
		}
		
	}
}
