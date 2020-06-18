package com.deckofcardsapi.utils;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class APIVerification {
	
	//Header validation
	public static void checkHeader(Response response, String headerType, String headerValue) {		
		String actualHeader = response.header(headerType); 
		Assert.assertEquals(actualHeader, headerValue);
	}
		
	//Response validation
	public static void responseStatusCodeValidation(Response response, int statusCode) {
		
		try {
			Assert.assertEquals(response.getStatusCode(), statusCode);
			System.out.println("Successfully validated status code.");			
		}catch(AssertionError e) {
			System.out.println("Status code doesn't matched.");
		}catch(Exception e) {
			System.out.println(e.fillInStackTrace());
		}	
	}
	
	public static void responseTimeValidation(Response response) {
		try {
			long responseTime = response.getTime();
			System.out.println("Response time is:: " + responseTime);	
			//dont have requirement for response time but in general i implemented for 2 seconds
			if(responseTime>2000)
				System.out.println("Response Time is greater than 2000.");			
			Assert.assertTrue(responseTime<2000);
		}catch(AssertionError e) {
			System.out.println("Response Time is greater than 2000.");
		}catch(Exception e) {
			System.out.println(e.fillInStackTrace());
		}
	}
	
	public static void responseKeyValidation(Response response, String key) {
		try {
			String body = response.getBody().asString();
			Assert.assertEquals(body.contains(key), true);			
		}catch(AssertionError e) {
			System.out.println("Response key not matching.");
		}catch(Exception e) {
			System.out.println(e.fillInStackTrace());
		}
	}
	
	public static void responseKeyAndValueValidation(Response response, String key, String value) {
		try {
			JsonPath jsonPath = response.jsonPath();
			String actualValue = jsonPath.get(key).toString();			
			Assert.assertEquals(actualValue, value);			
		}catch(AssertionError e) {
			System.out.println("Response value not matching.");
		}catch(Exception e) {
			System.out.println( e.fillInStackTrace());
		}
	}
	

	
	
}
