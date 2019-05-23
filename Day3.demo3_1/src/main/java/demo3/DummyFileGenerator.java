package demo3;

import org.springframework.integration.file.FileNameGenerator;
import org.springframework.messaging.Message;

public class DummyFileGenerator  implements FileNameGenerator{

	@Override
	public String generateFileName(Message<?> message) {
		// TODO Auto-generated method stub
		return message.getHeaders().getId() + ".msg";
	}

}
