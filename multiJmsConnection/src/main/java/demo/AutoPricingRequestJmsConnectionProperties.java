package demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.jms.autopricing")
public class AutoPricingRequestJmsConnectionProperties {

	private String brokerUrl;

	private String user;

	private String password;

	private String requestTopicName;

	private String replyTopicName;

	public String getBrokerUrl() {
		return brokerUrl;
	}

	public void setBrokerUrl(String brokerUrl) {
		this.brokerUrl = brokerUrl;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRequestTopicName() {
		return requestTopicName;
	}

	public void setRequestTopicName(String requestTopicName) {
		this.requestTopicName = requestTopicName;
	}

	public String getReplyTopicName() {
		return replyTopicName;
	}

	public void setReplyTopicName(String replyTopicName) {
		this.replyTopicName = replyTopicName;
	}

}
