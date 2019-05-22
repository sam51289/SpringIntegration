package demo;

import javax.jms.ConnectionFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
@EnableJms
public class JMSApplication {
	
	 @Bean
	    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
	                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
	        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	        // This provides all boot's default to this factory, including the message converter
	        configurer.configure(factory, connectionFactory);
	       factory.setPubSubDomain(true);
	        return factory;
	    }
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(JMSApplication.class, args);
		String[] arr = ctx.getBeanDefinitionNames();
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}

		JmsTemplate template = ctx.getBean(JmsTemplate.class);
		template.setPubSubDomain(true);
		for (int i = 0; i < 50; i++)
			template.convertAndSend("Topic1", "two" + i);

	}
}
