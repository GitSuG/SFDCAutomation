package com.sfdc.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	/*
	 * REQUIRED - Normal run code
	 */
	public static WebDriver driver = null;

	String dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	String reportPath = System.getProperty("user.dir")+"/src/main/resources/reports/"+"POMRun_WithExclude_Sep13_Login_"+dateFormat+"_sfdcTestReport.html";

	public static ExtentReports extent;
	public static ExtentTest test;
	public static Logger logger= LogManager.getLogger(BaseTest.class.getName());
	
	public static ITestContext context;
	public static ThreadLocal<WebDriver> threadLocalDriver;
	/*
	 * REQUIRED - Normal run code
	 */
	public static WebDriver getDriver() {
		
		return threadLocalDriver.get();
	}
	

	/*
	 * IMPORTANT only for parallel-execution
	 */
//	public WebDriver driver = null;
	/*
	 * IMPORTANT only for parallel-execution
	 */
//	public WebDriver getDriver() {
//		
//		return threadLocalDriver.get();
//	}
	
	public static ITestContext setContext(ITestContext iTestContext, WebDriver driver) {
		iTestContext.setAttribute("driver", driver);
		return iTestContext;
	}
	/*
	 * REQUIRED - Normal run code
	 */
	@BeforeSuite
	/*
	 * IMPORTANT only for parallel-execution
	 */
//	@BeforeMethod
	public void setUp(ITestContext result) {
		
			
		logger.info("*************Initializing Report Configuration************");
		extent = new ExtentReports();
		
		logger.info("Initialize: Extent Report instance");
		ExtentHtmlReporter html = new ExtentHtmlReporter(reportPath);
		
		logger.info("Configure: Html report path");
		extent.attachReporter(html);
		logger.info("Configure: Html report path to extent report instance");
		
	}
	
	@AfterSuite
	public void tearDown(ITestContext name) {
		
		extent.flush();
		logger.info("Report flushed: Name: "+name.getName());
		logger.info("Report flushed: Path: \n"+reportPath);
		
	}
	
	public WebDriver getBrowser(String browserName, boolean isHeadless) {

		String browser = browserName.toLowerCase();

		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			if(isHeadless) {
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--headless","--disable-gpu","--window-size=1920,1080","--ignore-certificate");
				driver = new ChromeDriver(co);
			}else {
				driver = new ChromeDriver();
			}
			break;
			
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
			
		default:
			System.out.println(browserName+" : Browser support not available: Choose chrome / firefox");
		}
		

		logger.info("Browser: "+browser);
		return driver;

	}

}
