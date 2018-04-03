package it.edu.microservices.traductor.client.rest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import it.edu.microservices.traductor.bean.Greeting;

@RestController
@RequestMapping(path = "/traductor/client", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
	
//	public static final String BASE_URL_ITALIAN = "http://172.17.0.1:8102/traductor/";
//	public static final String BASE_URL_SPANISH = "http://172.17.0.1:8102/traductor/";
	
	
	public static final String BASE_URL_ITALIAN = "http://italian/traductor/italian";
	public static final String BASE_URL_SPANISH = "http://spanish/traductor/spanish";
	
	private static int counter = 0 ;
	
	@Autowired
	private RestTemplate restTemplate;
	
//	@RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
//	public String traductor(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String,String> requestParameters){
//		System.out.println("Hola mundo numero : " + ++counter);
//		
//		String language=requestParameters.get("language");
//		String baseUrl= "spanish".equals(language)?BASE_URL_SPANISH:BASE_URL_ITALIAN;
//		String output = simpleGetWithParameter(baseUrl,language);
//		
//		return output;
////		return "{\"saluto\":\"Clente Mondo numero " + counter +"\"}";
//	}
//	
//	private String simpleGetWithParameter(String baseUrl,String pathService) {
//		String output="";
//		try{
//			Client client = Client.create();
//			WebResource webResource = client.resource(baseUrl + pathService);
//			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
//			output = response.getEntity(String.class);
//			System.out.println(output);
//			if (response.getStatus() != 200) {
//				System.out.println("simpleGetWithParameter Failed : HTTP error code : "+ response.getStatus());
//			}
//		}catch (Exception e) {
//			output="{\"errore\":"+"\""+e.getMessage()+"\"}";
//		}
//		return output;
//	}
	
	
	@RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public List<Greeting> getGreetings(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String,String> requestParameters){
		
		String language=requestParameters.get("language");
		String baseUrl= "spanish".equals(language)?BASE_URL_SPANISH:BASE_URL_ITALIAN;
		
		ResponseEntity<List<Greeting>> greetingResponse = this.restTemplate.exchange(baseUrl,HttpMethod.GET,null,
				new ParameterizedTypeReference<List<Greeting>>() {
		});
		return greetingResponse.getBody();
	} 
}
