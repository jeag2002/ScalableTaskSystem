package es.restserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class SymbioRestApplication {

	public static void main(String[] args) {
	    SpringApplication.run(SymbioRestApplication.class, args);
	 }
	
}
