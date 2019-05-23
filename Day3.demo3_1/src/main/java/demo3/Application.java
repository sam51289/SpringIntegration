package demo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value="demo1.xml")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		while(true){}
	}

}
