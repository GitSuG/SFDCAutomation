package test.pkg;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

@Listeners(LoginListener.class)
public class ReportsLogin {

	public static WebDriver driver;
	private static final String adminUserName = "salesforceadmin@homedev.com";
	private static final String adminPassword = "Admin123";
	
	String URL = "https://homedev-dev-ed.my.salesforce.com/";

	
	static ExtentReports extent;
	static ExtentTest test; 

	private static Logger logger = LogManager.getLogger(ReportsLogin.class);
	
	@BeforeClass
	public static void initReport() {
		
		String dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String reportPath = System.getProperty("user.dir") + "/src/main/resources/test-reports/" + dateFormat + "_sfdc.html";

//		System.out.println(reportPath);
		
		extent = new ExtentReports();
		ExtentHtmlReporter htmlReport = new ExtentHtmlReporter(reportPath);
		logger.info("Attaching the html report");
		extent.attachReporter(htmlReport);
	}
	
	public void driverSetup() {
//		WebDriverManager.chromedriver.setup();
//		ChromeOptions co = new ChromeOptions();
//		co.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080", "--ignore-certificate");
//		driver = new ChromeDriver(co);

	}

	@BeforeMethod
	public void launchApp() {
		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
		logger.debug("Initialized the chromedriver");
		
		driver = new ChromeDriver();
		String expectedPage = "Login | Salesforce";

		driver.get(URL);
		driver.manage().window().maximize();


//		Method name = null; name.getName()+
		test = extent.createTest(" T00_LoginToSFDC ");
		String actualPage = driver.getTitle();
		Assert.assertEquals(actualPage, expectedPage, "Error: Page mismatch: [Login]");
		test.log(Status.INFO, "Login Page: Title verified");
	}


	@AfterTest
	public void tearDown() {
		extent.flush();
		logger.info("Flusing the report");
	}
	
	@Test(dataProvider = "LoginData", dataProviderClass=ReportsLogin.class)
	public void T00_LoginToSFDC(String userName, String Password) throws InterruptedException, IOException {
		
		WebElement userNameElement = driver.findElement(By.id("username"));
		userNameElement.sendKeys(userName);
		test.log(Status.INFO, "Login Page:User Name :: "+userName);		
		String actualUsername = userNameElement.getAttribute("value");

		WebElement pswdElement = driver.findElement(By.id("password"));
		pswdElement.sendKeys(Password);
		test.log(Status.INFO, "Login Page:User Password :: "+Password);
		String actualPassword = pswdElement.getAttribute("value");

		WebElement login = driver.findElement(By.id("Login"));
		login.click();
		String actualPage = driver.getTitle();
		
		boolean checkErrors = LoginUtils.checkLoginErrors(driver, test);
		
		if(!checkErrors) {
			
			Assert.assertEquals(actualUsername, userName);
			test.log(Status.INFO, "Login Page:User Name verified");
			Assert.assertEquals(actualPassword, Password);
			test.log(Status.INFO, "Login Page:User Password verified");
			logger.info("Logger: User name password verified");
			
			Thread.sleep(6000);
			String expectedPage = "Home Page ~ Salesforce - Developer Edition";
			Assert.assertEquals(actualPage, expectedPage, "Error: Page mismatch, Not Home Page");

			test.log(Status.PASS, "PASS: Login successful");
			System.out.println("Login successful...");
		}else {
			System.out.println("Check Username / Password");
			test.log(Status.FAIL, "FAIL: Login information Username/Password error");
			logger.fatal("Logger: Fail");
			test.addScreenCaptureFromPath(IOUtilities.captureScreenShot(driver));
		}
		driver.close();
	}
	
	@AfterClass()
	public void T09_LogoutSFDC() {

		System.out.println("Driver Closed");
		driver.quit();	

	}
	
	@DataProvider(name = "LoginData")
	public Object[][] userCredentials(){
		
		return new Object[][] 
							{{"Sujata", ""},
							{adminUserName, adminPassword},
							{"salesforceadmin@homedev.com", "Admin123"},
							{"121", "22133"}};
	}

}
