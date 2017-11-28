package es.restserver;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import es.restserver.service.IKafkaService;


@RestController
public class SymbioRestController {
	
	private final Logger logger = LoggerFactory.getLogger(SymbioRestController.class);
	private static final ExecutorService ex = Executors.newFixedThreadPool(10);
	
	@Autowired
	private IKafkaService kafkaService;
	
	@Value("${server.topic}") //--server.topic=server_1
	private String serverTopic;
	
	@Value("${client.topic}") //--client.topic=client_1
	private String clientTopic;

	/**
	 * AsyncProcesSymbio 
	 * @param requestWrapper
	 * @return
	 */
	
	/*
	@RequestMapping(value = "/asyncprocessymbiopost", method = RequestMethod.POST)
	public DeferredResult<String> asyncProcessSymbioPost(@RequestBody String requestWrapper){
		logger.info("[asyncProcessSymbio] -- asyncProcessSymbio POST");
		DeferredResult<String> dr = new DeferredResult<String>();
		CompletableFuture.supplyAsync(()->{return kafkaService.processKafkaService(serverTopic, clientTopic.concat("_").concat(requestWrapper));},ex).thenAccept((String responseWrapper)->{dr.setResult(responseWrapper);});
		return dr;
	}
	*/
	
	/**
	 * AsyncProcesSymbio 
	 * @param requestWrapper
	 * @return
	 */
	
	@RequestMapping(value = "/asyncprocessymbioget/{data}", method = RequestMethod.GET)
	public DeferredResult<String> asyncProcessSymbioGet(@PathVariable String data){
		logger.info("[asyncProcessSymbio] -- asyncProcessSymbio GET");
		DeferredResult<String> dr = new DeferredResult<String>();
		CompletableFuture.supplyAsync(()->{return kafkaService.processKafkaService(serverTopic, clientTopic.concat("_").concat(data));},ex).thenAccept((String responseWrapper)->{dr.setResult(responseWrapper);});
		return dr;
	}
	
	

	/**
	 * procesSymbio 
	 * @param requestWrapper
	 * @return
	 */
	
	@RequestMapping(value = "/processymbioget/{data}", method = RequestMethod.GET)
	public ResponseEntity<String>  processSymbioGet(@PathVariable String data){
		logger.info("[processSymbio] -- processSymbio GET");
		String output = kafkaService.processKafkaService(serverTopic, clientTopic.concat("_").concat(data));
		return new ResponseEntity<String>(output, HttpStatus.OK);
	}
	
	
	
}
