package com.deckofcardsapi.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import com.deckofcardsapi.utils.FileandEnv;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseTest {
	
	public static Properties prop = new Properties();
	public static RequestSpecification httpRequest;
	public static Response response;
	
	public Logger logger;
	
	@BeforeClass
	public void setup() throws IOException {
		
		//Base URL
		FileInputStream fisDev = new FileInputStream(System.getProperty("user.dir") + "/env/qa.properties");
		prop.load(fisDev);
		RestAssured.baseURI = prop.getProperty("url");
		
		//Logger define
		logger = Logger.getLogger("DeckofCardsAPI");
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);
	}
	
}