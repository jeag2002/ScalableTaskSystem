package es.kafkaserver;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import es.kafkaserver.service.producer.KafkaProducer;

@Service
public class SymbioKafkaService {
	
	private static final Logger log = LoggerFactory.getLogger(SymbioKafkaService.class);
	
	public final CountDownLatch countDownLatch1 = new CountDownLatch(1);
	
	@Value("${server.topic}") //--client.topic=client_1
	private String serverTopic;
	
	@Value("${client.topic}")
	private String clientTopic;
	
	@Autowired
	private KafkaProducer producer;
	
	@KafkaListener(topics="${client.topic}", group="symbio-group")
	public void processData(String data){
		log.info("[KafkaConsumerServer-GET] topic='{}' data='{}'",clientTopic,data);
		String comp[] = data.split("_"); 
		//producer.send(serverTopic,data);
		producer.send(comp[0], comp[1]);
		countDownLatch1.countDown();
	}
	
}
