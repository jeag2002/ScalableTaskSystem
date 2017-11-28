package es.restserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import es.restserver.component.MessageStorage;
import es.restserver.service.producer.KafkaProducer;

@Service
public class KafkaService implements IKafkaService {

	private static final Logger log = LoggerFactory.getLogger(KafkaService.class);
	
	@Autowired
	KafkaProducer producer;
	
	@Autowired
	MessageStorage storage;
	
	@Override
	@HystrixCommand(fallbackMethod = "reliable")
	public String processKafkaService(String topic, String payload) {
		
		producer.send(topic, payload);
		
		String data = "";
		while ((data = storage.toString()).equalsIgnoreCase(""));
		storage.clear();
	
		return data;
	}
	
	public String reliable(String topic, String payload) {
		return "Error Kafka Connection topic=(" + topic + ")::payload=(" + payload + ")";
	}
	
	
	

}
