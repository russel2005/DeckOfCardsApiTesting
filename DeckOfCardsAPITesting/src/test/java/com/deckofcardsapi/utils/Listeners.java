package com.deckofcardsapi.utils;


import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter{
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public static ExtentTest test;
	
	@Override
	public void onStart(ITestContext context) {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/extentReports/APIReport.html");
		htmlReporter.config().setDocumentTitle("Automation Report");    //title of the Report
		htmlReporter.config().setReportName("API Testing Report"); //name of the Report
		//htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);//location of the chart
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Project name: ","Employee Database API");
		extent.setSystemInfo("Host name","DeckOfCards");
		extent.setSystemInfo("Enviroinment: ","SAT");
		extent.setSystemInfo("User: ","Touhidul Islam");
		
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName()); //create new entry in the report
		test.log(Status.PASS, "TestCase PASSED is " + result.getName());
		//test = extent.createTest(result.getClass().getName());
		//test.createNode(result.getName());		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());  //create new entry in the report
		test.log(Status.FAIL, "TestCase FAILED is " + result.getName());	  // to add name in extent report
		test.log(Status.FAIL, "TestCase FAILED is " + result.getThrowable()); //to add error/exception in extent
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());  //create new entry in the report
		test.log(Status.SKIP, "TestCase SKIPPED is " + result.getName());		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		
	}

}
