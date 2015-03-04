package demo.activemq;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import demo.AutoPricingRequestJmsConnectionProperties;

@Component
@Configuration
public class AutoPricingRequestReceiver {
	
	@Autowired
	@Qualifier("autoPricingRequestConnectionFactory")
	private UserCredentialsConnectionFactoryAdapter autoPricingRequestConnectionFactory;
	
	private JmsTemplate jmsTemplate;

	@Autowired
	private AutoPricingRequestJmsConnectionProperties properties;
	
	private AutoPricingRequestMessageListener listener = new AutoPricingRequestMessageListener();
	
	
	@Bean
	public DefaultMessageListenerContainer autoPricingListener() {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(autoPricingRequestConnectionFactory);
		container.setPubSubDomain(true);
		container.setDestinationName(MessageFormat.format(properties.getRequestTopicName(), properties.getUser()));
		MessageListenerAdapter adapter = new MessageListenerAdapter();
		adapter.setDelegate(listener);
		adapter.setDefaultListenerMethod("handleRequest");
		adapter.setDefaultResponseTopicName(MessageFormat.format(properties.getReplyTopicName(), properties.getUser()));
		container.setMessageListener(adapter);
		return container;
	}
	
	
	
	private class AutoPricingRequestMessageListener {
		
		void handleRequest(String request) {
			System.out.println(request);
		}
		
		void handleRequestReply(String reply) {
			
		}
		
	}

}
