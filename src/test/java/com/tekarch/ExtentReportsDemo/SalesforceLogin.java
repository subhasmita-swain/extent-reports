package com.tekarch.ExtentReportsDemo;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class SalesforceLogin {

	String dateformat = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	static WebDriver driver;
	static ExtentReports extent;
	static ExtentHtmlReporter report;
	String Filepath = System.getProperty("user.dir") + "//Reports//" + dateformat + "SFDCreport.html";

	public String takescreenshot() throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		String destpath = System.getProperty("user.dir") + "//Reports//Scereenshots//" + dateformat + "SFDC.PNG";
		File srcfile = screenshot.getScreenshotAs(OutputType.FILE);
		File destfile = new File(destpath);
		FileUtils.copyFile(srcfile, destfile);

		return destpath;
	}

	@BeforeClass
	public void initialize() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		extent = new ExtentReports();
		report = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "//Reports//" + dateformat + "SFDCreport.html");
		extent.attachReporter(report);
	}

	@AfterClass
	public void closereport() {
		extent.flush();
		driver.close();
	}

	@Test
	public void Login_01(Method name) throws IOException, InterruptedException {
		ExtentTest test = extent.createTest(name.getName());
		// public void Login_01() throws IOException, InterruptedException {
		// ExtentTest test = extent.createTest("Login_TC01");
		driver.get("https://login.salesforce.com");
		test.log(Status.INFO, "Login Page is launched");
		driver.findElement(By.id("username")).sendKeys("subha@gmail.com");
		test.info("Entered username");
		driver.findElement(By.id("password")).sendKeys("sairam123");
		test.info("Entered password");

		driver.findElement(By.id("Login")).click();
		Thread.sleep(2000);

		// test.addScreenCaptureFromPath(takescreenshot());
		// Assert.assertEquals(driver.findElement(By.id("error")).getText(),"Please
		// check your username and password. If you still can't log in, contact your
		// Salesforce administrator.");
		if (driver.findElement(By.id("error")).getText().equalsIgnoreCase(
				"Please check your username and password. If you still can't log in, contact your Salesforce administrator.")) {
			test.log(Status.PASS, "Login_TC01 Passed");
			Assert.assertEquals(driver.findElement(By.id("error")).getText(),
					"Please check your username and password. If you still can't log in, contact your Salesforce administrator.");
		} else {
			test.addScreenCaptureFromPath(takescreenshot());
			test.log(Status.FAIL, "Login_TC01 FAILED");
			Assert.fail("Login_TC01 FAILED");
		}
	}

	@Test
	// public void Login_02(Method name) throws IOException, InterruptedException {
	// ExtentTest test2 = extent.createTest(name.getName());
	public void Login_02() throws IOException, InterruptedException {
		ExtentTest test2 = extent.createTest("Login_TC02");
		driver.get("https://login.salesforce.com");
		test2.log(Status.INFO, "Login Page is launched");
		driver.findElement(By.id("username")).sendKeys("subha@gmail.com");
		test2.info("Entered username");
		driver.findElement(By.id("password")).sendKeys("sairam123");
		test2.info("Entered password");

		driver.findElement(By.id("Login")).click();
		Thread.sleep(2000);

		// test2.addScreenCaptureFromPath(takescreenshot());
		// Assert.assertEquals(driver.findElement(By.id("error")).getText(),"Password
		// entered in incorrect.");
		if (driver.findElement(By.id("error")).getText().equalsIgnoreCase("Password entered is incorrect")) {
			test2.log(Status.PASS, "Login_TC01 Passed");
			Assert.assertEquals(driver.findElement(By.id("error")).getText(), "Password entered is incorrect");
		} else {
			test2.addScreenCaptureFromPath(takescreenshot());
			test2.log(Status.FAIL, "Login_TC02 FAILED");
			Assert.fail("Login_TC02 FAILED");
		}
	}
}