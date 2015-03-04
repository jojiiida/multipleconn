package demo;

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
}
