package it.edu.microservices.traductor.client.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/health", consumes = MediaType.ALL_VALUE, produces = MediaType.ALL_VALUE)
public class Health {
	
	private Logger LOGGER = LogManager.getLogger();
	
	private @Value("${configuration.greeting:notFoundConfiguration}") 
	String defaultGreeting;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> getGreetings(HttpServletRequest request, HttpServletResponse response){
		
		
		
		ResponseEntity<String> response1 = new ResponseEntity<String>("Hola mundo todo bien : " + defaultGreeting, HttpStatus.OK);
		
		LOGGER.info("health : " + response1.toString());
		return response1;
	} 
}
