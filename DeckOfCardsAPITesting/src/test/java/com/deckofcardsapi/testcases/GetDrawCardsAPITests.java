package com.deckofcardsapi.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.deckofcardsapi.base.BaseTest;
import com.deckofcardsapi.utils.APIVerification;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class GetDrawCardsAPITests extends BaseTest{
	
	public static  String GET_DRAW_CARDS_FROM_DECK(String deck_id) {
		return "/api/deck/"+deck_id +"/draw/";
	}
	
	@BeforeClass
	void getAllEmployees() throws InterruptedException {
		logger.info("********** Started GetNewDeckAPITests **********");
		RestAssured.basePath = GET_DRAW_CARDS_FROM_DECK("94t04isyusug");
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/draw/");		
		Thread.sleep(3000);
	}
	

	@Test(groups = {"functional"})
	void checkResponseKeyAndValue(){		
		logger.info("*******Checking Response key ********");
		APIVerification.responseKeyAndValueValidation(response, "success", "true");
		APIVerification.responseKeyAndValueValidation(response, "deck_id", "94t04isyusug");
	}
	
	@Test(groups = {"functional", "negative"})
	void checkResponseValueIsNotNullOrEmpty(){		
		logger.info("*******Checking Response key ********");
		APIVerification.responseValueIsNotNullOrEmpty(response, "success");
		APIVerification.responseValueIsNotNullOrEmpty(response, "deck_id");
	}
	
	@Test(groups = {"functional", "negative"})
	void checkResponseValueSize(){		
		logger.info("*******Checking Response key ********");
		APIVerification.responseValueSize(response, "deck_id", 12);
	}
	
	/*
	 * @DataProvider(name="deckId") public Object[] getDeckId() { return new
	 * Object[] { "ztvz4l7iykco", "bm67jevo8zie", "mu6mcl99zm5g" }; }
	 */

}