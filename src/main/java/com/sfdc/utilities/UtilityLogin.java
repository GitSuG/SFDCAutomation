package com.sfdc.utilities;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class UtilityLogin {

	public static WebDriver driver;
	private static final String adminUserName = "salesforceadmin@homedev.com";
	private static final String adminPassword = "Admin123";

	static String URL = "https://homedev-dev-ed.my.salesforce.com/";
	

	
	public static void driverSetupLaunch() {
		
//		ChromeOptions co = new ChromeOptions();
//		co.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080", "--ignore-certificate-errors");
//		driver = new ChromeDriver(co);
//		WebDriverManager.chromedriver().setup();
//		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");


		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

		driver = new ChromeDriver();
		
		driver.get(URL);
		driver.manage().window().maximize();

		String expectedPage = "Login | Salesforce";
		String actualPage = driver.getTitle();
		Assert.assertEquals(actualPage, expectedPage, "Error: Page mismatch: [Login]");
	}
	

	@Test
	public static void T00_LoginToSFDC() {

		driverSetupLaunch();
		
		String userName = adminUserName;
		String password = adminPassword;
		
		WebElement userNameElement = driver.findElement(By.id("username"));
		userNameElement.sendKeys(userName);
		String actualUsername = userNameElement.getAttribute("value");

		WebElement pswdElement = driver.findElement(By.id("password"));
		pswdElement.sendKeys(password);
		String actualPassword = pswdElement.getAttribute("value");

		Assert.assertEquals(actualUsername, userName);
		Assert.assertEquals(actualPassword, password);

		WebElement login = driver.findElement(By.id("Login"));
		login.click();
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.titleContains("Home Page ~ Salesforce"));
	
		String actualPage = driver.getTitle();
		String expectedPage = "Home Page ~ Salesforce - Developer Edition";

		Assert.assertEquals(actualPage, expectedPage, "Error: Page mismatch, Not Home Page");

		System.out.println("Login successful...");
//		System.out.println("Check Username / Password");

//		driver.close();
	}

	
	public void T09_LogoutSFDC() {
		
		System.out.println("Driver Closed");
//		driver.quit();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@title='Home Tab - Selected']"))));
//		

	}
	
	@DataProvider(name = "LoginData")
	public Object[][] userCredentials(){
		
		return new Object[][] 
							{{adminUserName, adminPassword}};
	}
}
