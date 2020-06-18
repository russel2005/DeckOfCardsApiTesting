package com.deckofcardsapi.utils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FileandEnv {
	
	public static Map<String, String> fileandenv = new HashMap<String, String>();
	public static Properties propMain = new Properties();
	public static Properties propPreSet = new Properties();
	
	public static Map<String, String> envAndFile(){
		
		String environment = System.getProperty("env");
		
		try {
			if(environment.equalsIgnoreCase("dev")) {
				FileInputStream fisDev = new FileInputStream(System.getProperty("user.dir") + "/env/dev.properties");
				propMain.load(fisDev);
				fileandenv.put("url", propMain.getProperty("url"));
				fileandenv.put("port", propMain.getProperty("port"));
				fileandenv.put("username", propMain.getProperty("username"));
				fileandenv.put("password", propMain.getProperty("password"));
			}else if(environment.equalsIgnoreCase("qa")) {
				FileInputStream fisQa = new FileInputStream(System.getProperty("user.dir") + "/env/qa.properties");
				propMain.load(fisQa);
				fileandenv.put("url", propMain.getProperty("url"));
				fileandenv.put("port", propMain.getProperty("port"));
				fileandenv.put("username", propMain.getProperty("username"));
				fileandenv.put("password", propMain.getProperty("password"));
			}else if(environment.equalsIgnoreCase("staging")) {
				FileInputStream fisStaging = new FileInputStream(System.getProperty("user.dir") + "/env/staging.properties");
				propMain.load(fisStaging);
				fileandenv.put("url", propMain.getProperty("url"));
				fileandenv.put("port", propMain.getProperty("port"));
				fileandenv.put("username", propMain.getProperty("username"));
				fileandenv.put("password", propMain.getProperty("password"));
			}

		}catch(Exception e) {
			System.out.println("Error:"+e);
		}		
		
		return fileandenv;
	}
	
	
	public static Map<String, String> getConfigReader(){
		if(fileandenv == null) {
			fileandenv = envAndFile();
		}
		
		return fileandenv;
		
	}

}
