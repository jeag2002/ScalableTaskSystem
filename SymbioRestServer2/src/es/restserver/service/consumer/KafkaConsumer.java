package es.restserver.service.consumer;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import es.restserver.component.MessageStorage;

@Component
public class KafkaConsumer {
	
	private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
	
	public final CountDownLatch countDownLatch1 = new CountDownLatch(1);
	
	@Autowired
	MessageStorage storage;
	
	@Value("${client.topic}") //--client.topic=client_1
	private String clientTopic;
	
	@KafkaListener(topics="${client.topic}", group="${spring.kafka.consumer.group-id}")
    public void processMessage(String content) {
		log.info("[KafkaConsumerREST-GET] topic='{}' data='{}'",clientTopic,content);
		storage.put(content);
		countDownLatch1.countDown();
    }
	

}
