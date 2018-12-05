package com.demo.webservice.querysql;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.demo.properties.PropertyHelper;
import com.demo.webservice.querysql.handler.RetrieveRequestHandler;
import com.demo.webservice.querysql.handler.RetrieveResponseHandler;
import com.demo.webservice.querysql.validation.RetrieveRequestValidator;

/**
 * Message Reader Query SQL Application that provides a web service for requests. On reception
 * It will use Jackson to convert the String to a Java Object, performing to ensure the expected parameters are present
 * , pass the parameters to the stored procedure that exists in SQLServer, receive and convert the response
 * and finally reply with the JSON response to the original web service request. 
 * 
 * SpringBoot:
 * @SpringBootApplication - Convenience annotation that adds the following:
 *			@Configuration - Tags the class as a source of bean definitions for the application context.
 *			@EnableAutoConfiguration - Tells SpringBoot to start adding beans based on classpath settings, other beans, and various property settings.
 *			@ComponentScan - Tells Spring to look for other components, configurations, and services in the package, allowing it to find the controllers.
 * 
 * @author SULWAYJO
 *
 */

@SpringBootApplication(scanBasePackages={"com.demo"})
public class MessageWebServiceQuerySQLApplication {
		
    /**
     * The Application main method starting the Spring Application
     * @param args
     */
	public static void main(String[] args) {
		SpringApplication.run(MessageWebServiceQuerySQLApplication.class, args);
	}
	
	/**
	 * Return a SearchRequestHandler
	 * 
	 * @return
	 */
	@Bean
	public RetrieveRequestHandler seachRequestHandler(){
		return new RetrieveRequestHandler();
	}
	
	/**
	 * Return a SearchResponseHandler
	 * 
	 * @return
	 */
	@Bean
	public RetrieveResponseHandler seachResponseHandler(){
		return new RetrieveResponseHandler();
	}
	
	/**
	 * Return a populated error properties object
	 * 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@Bean
	public PropertyHelper errorPropertyHelper() throws FileNotFoundException, IOException{
		PropertyHelper properties = new PropertyHelper();
		InputStream resourceAsInputStream = MessageWebServiceQuerySQLApplication.class.getClassLoader().getResourceAsStream("errors.properties");
		properties.load(resourceAsInputStream);
		return properties;
	}
	
	/**
	 * Return a RetrieveRequestValidator
	 * 
	 * @return
	 */
	@Bean
	public RetrieveRequestValidator retrieveRequestValidator(){
		return new RetrieveRequestValidator();
	}

}
