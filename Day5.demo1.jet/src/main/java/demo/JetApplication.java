package demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class JetApplication {

	public static void main(String[] args) {
		SpringApplication.run(JetApplication.class, args);
	}

}
@RestController
@RequestMapping(value="/jet")
class MyRest{
	@GetMapping(value="/{from}/{to}")
	public List<JetFlight> getflight(@PathVariable(name="from")String from,@PathVariable(name="to") String to)
	{
		List<JetFlight> fl = new ArrayList<>();
		for (int i = 0; i< from.length();i++)
		{
			fl.add(new JetFlight(from, to, "May" + (i+10)));
		}
		return fl;
	}
	
}