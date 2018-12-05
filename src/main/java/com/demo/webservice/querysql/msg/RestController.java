package com.demo.webservice.querysql.msg;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.properties.PropertyHelper;
import com.demo.webservice.querysql.handler.RetrieveRequestHandler;
import com.demo.webservice.querysql.handler.RetrieveResponseHandler;
import com.demo.webservice.querysql.json.dto.GetPlanetRequestDto;
import com.demo.webservice.querysql.json.dto.GetPlanetResponseDto;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	private final Logger logger = Logger.getLogger(org.springframework.web.bind.annotation.RestController.class);
	
	@Autowired
	RetrieveRequestHandler retrieveRequestHandler;
	
	@Autowired
	RetrieveResponseHandler retrieveResponseHandler;
	
	@Autowired
	PropertyHelper errorPropertyHelper;

	
	@RequestMapping("/planet")
	public GetPlanetResponseDto planet(@RequestParam(value="planetName") String planetName){	
		logger.info("service has received a request with parameter " + planetName);
	   	GetPlanetRequestDto getPlanetRequestDto = new GetPlanetRequestDto();
	   	getPlanetRequestDto.setPlanetName(planetName);
	   	
	   	GetPlanetResponseDto planetResponseDto = null;
	
	   try{
		   if(getPlanetRequestDto != null){
	    		 planetResponseDto = retrieveRequestHandler.handleRequest(getPlanetRequestDto);
		   }
   	   }catch (Exception e){
   		logger.error("Exception occured : " + e.getMessage());
   	   }
	   
	   return planetResponseDto;
	}
	
}
