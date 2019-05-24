package demo;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.core.MessageSelector;
import org.springframework.messaging.Message;

class MyFilter implements MessageSelector {

	@Override
	public boolean accept(Message<?> msg) {
		System.out.println("Accept method of MyFilter " + msg);
		if (msg.getPayload() instanceof File) {
			if (msg.getHeaders().get("file_name").toString().startsWith("aa"))
				return true;
			else
				return false;
		} else
			return false;
	}

}

@SpringBootApplication
@ImportResource(value = "demo1.xml")
public class Application {

	@Bean
	public MyFilter filter1() {
		return new MyFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
