package it.edu.microservices.traductor.spanish.rest;

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
@RequestMapping(path = "/traductor/spanish", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class SpanishController {
	
	private Logger LOGGER = LogManager.getLogger();
	
	private @Value("${configuration.greeting}") 
	String defaultGreeting;
	
	private static int counter = 0 ;
	
	@RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public  List<Greeting> traductor(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String,String> requestParameters){
		LOGGER.debug("start traductor spanish {} " ,requestParameters);
		
		String greeting = defaultGreeting == null || defaultGreeting.isEmpty() ? " Estamos mal con la configurazione centralizada no funziona " : defaultGreeting;
		
		Greeting greet = new Greeting(greeting, counter, "SPANISH LANGUAGE");
		List<Greeting> regards = new ArrayList<>();
		regards.add(greet);
		
		System.out.println(greeting + ++counter);
		LOGGER.debug("end traductor spanish with greet {} and counter {} " ,greet, counter);
		
		return regards;
	}
}


//http://localhost:8080/traductor/spanish