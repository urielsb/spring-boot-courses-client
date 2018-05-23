/**
 * 
 */
package com.uriel.spring.client.app;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.uriel.spring.client.config.ClientWS;
import com.uriel.spring.client.config.ClientWSConfig;
import com.uriel.ws.courses.client.model.GetModuleResponse;
import com.uriel.ws.courses.client.model.GetStudentResponse;


/**
 * @author Uriel Santoyo
 *
 */
@SpringBootApplication
@Import(ClientWSConfig.class)
public class CoursesWSClientApp {
	
	private static final Logger logger = LogManager.getLogger(CoursesWSClientApp.class);
	
	@Autowired
	private ClientWS client;

	public static void main(String args[]){
		SpringApplication.run(CoursesWSClientApp.class, args);
	}
	
	@PostConstruct
	public void init(){
		logger.info("#####################################################");
		logger.info("Courses WS Client");
		logger.info("#####################################################");
		GetStudentResponse studentRequest = client.getStudentResponse("1");
		logger.info(studentRequest.getStudent().getId());
		logger.info(studentRequest.getStudent().getFirstName() + " " + studentRequest.getStudent().getLastName());
		logger.info("\n");
		GetModuleResponse moduleResponse = client.getModuleResponse(1, 4);
		logger.info(moduleResponse.getModule().getInitDate());
		logger.info(moduleResponse.getModule().getNumber());
		logger.info(moduleResponse.getModule().getModuleDuration());
		moduleResponse.getModule().getCourses().stream().forEach(c -> {
			logger.info(c.getName());
			logger.info(c.getGrade());
			logger.info(c.getOportinity());
			logger.info("\n");
		});
	}
}
