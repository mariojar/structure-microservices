package it.edu.microservices.traductot.spanish.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import it.edu.microservices.traductor.spanish.rest.SpanishController;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackageClasses = {SpanishController.class})
public class SpanishBootConfiguration {
	
	public static void main(String[] args) {
        SpringApplication.run(SpanishBootConfiguration.class, args);
    }

}
