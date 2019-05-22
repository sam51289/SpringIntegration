package demo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener {
	@JmsListener(destination="Topic1", containerFactory="myFactory")
	public void receivedata(String s)
	{
		System.out.println("Received - " + s);
	}
}
