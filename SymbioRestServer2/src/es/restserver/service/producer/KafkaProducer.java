package es.restserver.service.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
	
	private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
	
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	public void send(String topic, String data){
		log.info("[KafkaProducerREST-SEND] topic='{}' data='{}'",topic,data);
		kafkaTemplate.send(topic,data);
	}

}
