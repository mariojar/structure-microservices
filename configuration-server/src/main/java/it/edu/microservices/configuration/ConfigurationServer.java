package it.edu.microservices.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigurationServer {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationServer.class, args);
	}
}
