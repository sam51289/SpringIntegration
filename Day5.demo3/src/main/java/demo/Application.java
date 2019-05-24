package demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.CorrelationStrategy;
import org.springframework.integration.annotation.ReleaseStrategy;

@SpringBootApplication
@ImportResource(value = "demo1.xml")
public class Application {
	@Bean
	public FlightAggregator flightAggregator() {
		return new FlightAggregator();
	}

	@Bean
	public MyCorrelationStrategy corr() {
		return new MyCorrelationStrategy();
	}
	@Bean
	public MyReleaseStrategy rel() {
		return new MyReleaseStrategy();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

class FlightAggregator {
	@Aggregator()
	public List<Object> add(List<Object> results) {
		return results;
	}
}

class MyCorrelationStrategy {
	@CorrelationStrategy
	public List<Object> groupNumbersByLastDigit(List<Object> result) {
		return new ArrayList<>();
	}
}

class MyReleaseStrategy {
	@ReleaseStrategy
	public boolean canRelease(List<Object> results) {
		List<Object> list = new ArrayList<>();
		list.addAll(results);
		return results.size() > 10;
	}
}
