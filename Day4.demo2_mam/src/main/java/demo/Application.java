package demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.annotation.Transformer;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

@SpringBootApplication
@EnableJms
@ImportResource(value="demo1.xml")
public class Application {
	
	
	
	@Transformer(inputChannel="input",outputChannel="fileout")
	String generateOrder(String filename) {
		//
		System.out.println("Filename = " + filename);
		 String content = "";
		    try
		    {
		        content = new String ( Files.readAllBytes( Paths.get(filename) ) );
		    }
		    catch (IOException e)
		    {
		        e.printStackTrace();
		    }
		    return content;
	}
	
	
	
	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
	    ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
	    factory.setTrustedPackages(Arrays.asList("demo"));
	    return factory;
	}
	
 
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx =SpringApplication.run(Application.class, args);
		String[] arr= ctx.getBeanDefinitionNames();
		for (String string : arr) {
			System.out.println(string);
		}
		//while(true){}
	}

}
