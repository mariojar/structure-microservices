package it.edu.microservices.traductor.italian.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.edu.microservices.traductor.bean.Greeting;

@RestController
@RequestMapping(path = "/traductor/italian", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ItalianController {
	
	private Logger LOGGER = LogManager.getLogger(); 
	
	private @Value("${configuration.greeting}") 
	String defaultGreeting;
	
	private static int counter = 0 ;
	
	@RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public List<Greeting> traductor(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String,String> requestParameters){
		
		LOGGER.debug("start traductor italian {} " ,requestParameters);
		
		String greeting = defaultGreeting == null || defaultGreeting.isEmpty() ? " Non ci siamo con la configurazione centralizata del server " : defaultGreeting; 
		
		
		Greeting greet = new Greeting(greeting, counter, "ITALIAN LANGUAGE");
		List<Greeting> regards = new ArrayList<>();
		regards.add(greet);
		
		System.out.println(greeting + ++counter);
		
		LOGGER.debug("end traductor italian with greet {} and counter {} " ,greet, counter);
		
		return regards;
		
	}
}
