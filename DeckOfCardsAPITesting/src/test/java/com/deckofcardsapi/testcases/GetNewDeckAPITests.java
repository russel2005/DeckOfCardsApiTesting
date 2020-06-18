package com.deckofcardsapi.testcases;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.deckofcardsapi.base.BaseTest;
import com.deckofcardsapi.utils.APIVerification;

import configs.Endpoint;
import io.restassured.RestAssured;

public class GetNewDeckAPITests extends BaseTest {
	
	@BeforeMethod
	public void getAPITest() {		
		
		response = RestAssured.given().when().get(Endpoint.GET_NEW_DECK);		
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

}
