/**
 * 
 */
package com.uriel.spring.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

/**
 * @author Uriel Santoyo
 *
 */
@Configuration
@ComponentScan
public class ClientWSConfig {
	
	private static final String URL_COURSES_SERVICE = "http://localhost:8080/CoursesWSApplication";

	@Bean
	public Jaxb2Marshaller marshaller(){
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.uriel.ws.courses.client.model");
		return marshaller;
	}
	
	@Bean
	public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller){
		WebServiceTemplate wsTemplate = new WebServiceTemplate();
		wsTemplate.setMarshaller(marshaller);
		wsTemplate.setUnmarshaller(marshaller);
		wsTemplate.setDefaultUri(URL_COURSES_SERVICE);
		
		HttpComponentsMessageSender messageSender = new HttpComponentsMessageSender();
		messageSender.setConnectionTimeout(100);
		messageSender.setMaxTotalConnections(10);
		wsTemplate.setMessageSender(messageSender);
		
		return wsTemplate;
	}
}
