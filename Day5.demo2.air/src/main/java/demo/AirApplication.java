package demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class AirApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirApplication.class, args);
	}

}
@RestController
@RequestMapping(value="/air")
class MyRest{
	@GetMapping()
	public List<Flight> getflight(@RequestParam(name="from")String from,@RequestParam(name="to") String to)
	{
		List<Flight> fl = new ArrayList<>();
		for (int i = 0; i< from.length();i++)
		{
			fl.add(new Flight(from, to, "june" + (i+10)));
		}
		return fl;
	}
	
}