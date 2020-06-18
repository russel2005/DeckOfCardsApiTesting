package com.deckofcardsapi.testcases;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.deckofcardsapi.base.BaseTest;
import com.deckofcardsapi.utils.APIVerification;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class GetNewDeckAPITests extends BaseTest {
	
	@BeforeClass
	void getAllEmployees() throws InterruptedException {
		logger.info("********** Started GetNewDeckAPITests **********");
		RestAssured.basePath = "/api/deck";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/new/");		
		Thread.sleep(3000);
	}
		
	@Test
	void checkHeaderContent() {
		APIVerification.checkHeader(response, "Content-Type", "application/json");
		APIVerification.checkHeader(response, "Content-Encoding", "gzip");
		//all other header can be validated as well
	}
	
	@Test
	void checkStatusCode() {		
		logger.info("*******Checking Status code ********");
		APIVerification.responseStatusCodeValidation(response, 200);		
	}
	
	@Test
	void checkResponseTime() {		
		logger.info("*******Checking Response Time ********");
		APIVerification.responseTimeValidation(response);
	}
	
	@Test
	void checkResponseKey(){		
		logger.info("*******Checking Response key ********");
		APIVerification.responseKeyValidation(response, "success");
		APIVerification.responseKeyValidation(response, "deck_id");
		APIVerification.responseKeyValidation(response, "remaining");
		APIVerification.responseKeyValidation(response, "shuffled");
	}
	
	@Test
	void checkResponseKeyAndValue(){		
		logger.info("*******Checking Response key ********");
		APIVerification.responseKeyAndValueValidation(response, "success", "true");
		APIVerification.responseKeyAndValueValidation(response, "shuffled", "false");		
	}
	
	@AfterMethod
	public void teardown() {		
		logger.info("*******Test finished ********");
	}
	
	@AfterClass
	public void reset() {
		RestAssured.reset();
	}

}
