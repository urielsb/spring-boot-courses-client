/**
 * 
 */
package com.uriel.spring.client.config;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.uriel.ws.courses.client.model.GetModuleRequest;
import com.uriel.ws.courses.client.model.GetModuleResponse;
import com.uriel.ws.courses.client.model.GetStudentRequest;
import com.uriel.ws.courses.client.model.GetStudentResponse;

/**
 * @author Uriel Santoyo
 *
 */
@Component
public class ClientWS {

	@Autowired
	private WebServiceTemplate wsTemplate;
	
	public GetStudentResponse getStudentResponse(String idStudent){
		GetStudentRequest request = new GetStudentRequest();
		request.setId(idStudent);
		return (GetStudentResponse)wsTemplate.marshalSendAndReceive(request);
	}
	
	public GetModuleResponse getModuleResponse(int studentId, int moduleNumber){
		GetModuleRequest request = new GetModuleRequest();
		request.setId(String.valueOf(studentId));
		request.setModuleNumber(new BigInteger(String.valueOf(moduleNumber)));
		
		return (GetModuleResponse)wsTemplate.marshalSendAndReceive(request);
	}
}
