package com.deckofcardsapi.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.deckofcardsapi.base.BaseTest;
import com.deckofcardsapi.utils.APIVerification;

import configs.Endpoint;
import io.restassured.RestAssured;

public class GetDrawCardsAPITests extends BaseTest{
	
	@BeforeMethod
	public void getAPITest() {		
		
		response = RestAssured.given().when().get(Endpoint.GET_DRAW_CARDS_FROM_DECK("fanenxycli1v"));		
	}
	
	
	@Test
	void checkResponseKeyAndValue(){		
		logger.info("*******Checking Response key ********");
		APIVerification.responseKeyAndValueValidation(response, "success", "true");
		APIVerification.responseKeyAndValueValidation(response, "deck_id", "fanenxycli1v");
	}

}