package com.gatewayproxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.gatewayproxy.config.SymbioGatewayConfiguration;

@SpringBootApplication
@RestController
@RibbonClient(name = "kafkaservice", configuration = SymbioGatewayConfiguration.class)
public class SpringBootRibbonApplication {
	
	//https://github.com/spring-cloud/spring-cloud-netflix/blob/master/docs/src/main/asciidoc/spring-cloud-netflix.adoc#embedded-zuul-reverse-proxy
	//https://www.java2blog.com/spring-restful-client-resttemplate-example/	
	@LoadBalanced
	@Bean
	RestTemplate restTemplate(){return new RestTemplate();}
	
	@LoadBalanced
	@Bean
	AsyncRestTemplate asyncRestTemplate(){return new AsyncRestTemplate();}
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	AsyncRestTemplate asyncRestTemplate;
	
	
	@RequestMapping("/processymbioget/{data}")
	public String processymbioget(@PathVariable String data){
		String responseStr = this.restTemplate.getForObject("http://kafkaservice/processymbioget/{data}", String.class,data);
		return responseStr;
	}
	
	@RequestMapping("/asyncprocessymbioget/{data}")
	public String asyncprocessymbioget(@PathVariable String data){
		
		String response = "";
		
		try{
			String uriTest_2 = "http://kafkaservice/asyncprocessymbioget/{data}";
			ListenableFuture<ResponseEntity<String>> resp  = this.asyncRestTemplate.getForEntity(uriTest_2, String.class, data);
			ResponseEntity<String> res = resp.get();
			response =  res.getBody();
		}catch(Exception e){
			response = e.getMessage();
		}finally{
			return response;
		}

	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRibbonApplication.class, args);
	}
}
