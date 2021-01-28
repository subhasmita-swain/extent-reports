package com.tekarch.ExtentReportsDemo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestExtentReport {

	
	public static void main(String[] args) {
		
		String dateformat =new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	ExtentReports extent = new ExtentReports();
	//HTML report
	String Filepath = System.getProperty("user.dir")+"//Reports//"+dateformat+"FirtReport.html";
	
	ExtentHtmlReporter report = new ExtentHtmlReporter(Filepath);
	
	extent.attachReporter(report);
	
	//From here creating test reports..(Ecah testcase)
	
	ExtentTest test = extent.createTest("Login_TC01");
	test.info("Entered username");
	test.info("Entered password");
	
	test.log(Status.PASS, "Login_TC01 Passed");
	
	ExtentTest test2 = extent.createTest("Login_TC02");
	test2.info("Entered username");
	test2.log(Status.INFO, "Entered username");
	test2.info("Entered password");
	
	test.log(Status.FAIL, "Login_TC02 Failed");
	System.out.println("Testcase complete");
	
	//This should be given at the end .i.eafter all TC are executed
	extent.flush();
	
	}

}
