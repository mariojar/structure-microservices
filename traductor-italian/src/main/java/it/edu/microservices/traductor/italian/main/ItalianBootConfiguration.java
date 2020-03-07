package it.edu.microservices.traductor.italian.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import it.edu.microservices.traductor.italian.rest.ItalianController;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackageClasses = {ItalianController.class})
//@EnableDiscoveryClient
public class ItalianBootConfiguration {
	
	public static void main(String[] args) {
        SpringApplication.run(ItalianBootConfiguration.class, args);
    }

}
