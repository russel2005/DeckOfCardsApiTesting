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
	void getNewDeck() throws InterruptedException {
		logger.info("********** Started GetNewDeckAPITests **********");
		RestAssured.basePath = "/api/deck";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/new/");		
		Thread.sleep(3000);
	}
	
		
	@Test(groups = {"functional"})
	void checkHeaderContent() {
		APIVerification.checkHeader(response, "Content-Type", "application/json");
		APIVerification.checkHeader(response, "Content-Encoding", "gzip");
		//all other header can be validated as well
	}
	
	@Test(groups = {"functional"})
	void checkStatusCode() {		
		logger.info("*******Checking Status code ********");
		APIVerification.responseStatusCodeValidation(response, 200);		
	}
	
	@Test(groups = {"functional"})
	void checkResponseTime() {		
		logger.info("*******Checking Response Time ********");
		APIVerification.responseTimeValidation(response);
	}
	
	@Test(groups = {"functional"})
	void checkResponseKey(){		
		logger.info("*******Checking Response key ********");
		APIVerification.responseKeyValidation(response, "success");
		APIVerification.responseKeyValidation(response, "deck_id");
		APIVerification.responseKeyValidation(response, "remaining");
		APIVerification.responseKeyValidation(response, "shuffled");
	}
	
	@Test(groups = {"functional"})
	void checkResponseKeyAndValue(){		
		logger.info("*******Checking Response key and value ********");
		APIVerification.responseKeyAndValueValidation(response, "success", "true");
		APIVerification.responseKeyAndValueValidation(response, "shuffled", "false");		
	}
	
	@Test(groups = {"functional"})
	void checkResponseValueIsNotNullOrEmpty(){
		logger.info("*******Checking Response value empty or null ********");
		APIVerification.responseValueIsNotNullOrEmpty(response, "success");
		APIVerification.responseValueIsNotNullOrEmpty(response, "shuffled");	
		APIVerification.responseValueIsNotNullOrEmpty(response, "deck_id");
		APIVerification.responseValueIsNotNullOrEmpty(response, "remaining");
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
