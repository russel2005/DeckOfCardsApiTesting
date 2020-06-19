package com.deckofcardsapi.utils;

import org.testng.Assert;


import io.restassured.path.json.JsonPath;
import io.restassured.path.json.exception.JsonPathException;
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
			Assert.assertTrue(false);
		}catch(Exception e) {
			System.out.println(e.fillInStackTrace());
		}
	}
	
	public static void responseKeyAndValueValidation(Response response, String key, String value) {
		try {
			JsonPath jsonPath = response.jsonPath();
			String actualValue = jsonPath.get(key).toString();	
			System.out.println(actualValue);
			Assert.assertEquals(actualValue, value);			
		}catch(AssertionError e) {
			System.out.println("Response value not matching.");
			Assert.assertTrue(false);
		}catch(JsonPathException e) {
			System.out.println("Json response Error!!!");
			Assert.assertTrue(false);
		}
		catch(Exception e) {
			System.out.println( e.fillInStackTrace());
		}
	}
	
	
	//negative test method
	public static void responseValueIsNotNullOrEmpty(Response response, String key) {
		try {
			JsonPath jsonPath = response.jsonPath();
			
			String actualValue = jsonPath.get(key).toString();
			if(isNullOrEmpty(actualValue)) {
				Assert.assertTrue(false);
			}else
				Assert.assertTrue(true);			
		}catch(Exception e) {
			System.out.println( e.fillInStackTrace());
		}
	}
	
	public static void responseValueSize(Response response, String key, int size) {
		try {
			JsonPath jsonPath = response.jsonPath();
			
			int valueSize = jsonPath.get(key).toString().length();
			if(valueSize != size) {
				Assert.assertTrue(false);
			}else
				Assert.assertTrue(true);			
		}catch(AssertionError e) {
			System.out.println("Response data value size is not same.");
		}catch(Exception e) {
			System.out.println( e.fillInStackTrace());
		}
		
	}
	
		
	public static boolean isNullOrEmpty(String str) {
        if(str == null || str.isEmpty())
            return true;
        return false;
    }
		
}
