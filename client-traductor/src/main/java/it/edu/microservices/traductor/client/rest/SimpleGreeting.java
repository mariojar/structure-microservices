package it.edu.microservices.traductor.client.rest;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/greeting/client", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class SimpleGreeting {
	
	private Logger LOGGER = LogManager.getLogger();
	
	@RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public ResponseEntity<String> getGreetings(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String,String> requestParameters){
		
		String name=requestParameters.get("name");
		LOGGER.debug("start traductor client {} " ,name);
		
		ResponseEntity<String> response1 = new ResponseEntity<String>("Ciao " + name + " tutto bene?", HttpStatus.OK);
		
		LOGGER.debug("end traductor client response recived" ,response1);
		return response1;
	} 
}
