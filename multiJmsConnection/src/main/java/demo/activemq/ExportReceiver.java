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

import demo.ExportJmsConnectionProperties;

@Component
@Configuration
public class ExportReceiver {
	
	@Autowired
	@Qualifier("exportConnectionFactory")
	private UserCredentialsConnectionFactoryAdapter autoPricingRequestConnectionFactory;
	
	private JmsTemplate jmsTemplate;

	@Autowired
	private ExportJmsConnectionProperties properties;
	
	private ExportMessageListener listener = new ExportMessageListener();
	
	
	@Bean
	public DefaultMessageListenerContainer exportListener() {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(autoPricingRequestConnectionFactory);
		container.setPubSubDomain(false);
		container.setDestinationName(MessageFormat.format(properties.getRequestQueueName(), properties.getUser()));
		MessageListenerAdapter adapter = new MessageListenerAdapter();
		adapter.setDelegate(listener);
		adapter.setDefaultListenerMethod("handleRequest");
		adapter.setDefaultResponseTopicName(MessageFormat.format(properties.getReplyTopicName(), properties.getUser()));
		container.setMessageListener(adapter);
		return container;
	}
	
	private class ExportMessageListener {
		
		void handleRequest(String request) {
			System.out.println(request);
		}
		
		void handleRequestReply(String reply) {
			
		}
		
	}

}
