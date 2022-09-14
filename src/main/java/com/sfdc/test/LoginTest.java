package com.sfdc.test;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.sfdc.listeners.SFDCListeners;
import com.sfdc.pages.HomePage;
import com.sfdc.pages.LoginPage;
import com.sfdc.utilities.DataUtilities;
import com.sfdc.utilities.WaitUtilities;

@Listeners(SFDCListeners.class)
public class LoginTest extends BaseTest {

	private LoginPage lp;
	private HomePage hp;
	private static Logger logger = LogManager.getLogger(LoginTest.class.getName());

	@BeforeMethod
	public void driverSetup(ITestContext iTest) {

		this.context = setContext(iTest, driver);

	}

	@AfterMethod
	public void tearDown(ITestResult result) {

		driver.close();
		logger.info("Browser is closed");

	}

//	@AfterSuite
	public void closeAllBrowsers() {
//		driver.quit();
	}

	@Test
	public void TC01_loginErrorMessage() throws IOException {

		logger.info("Executing: TC01_loginErrorMessage");
		WebDriver driver = getBrowser("chrome", false);

		lp = new LoginPage(driver);

		lp.launchApp(driver);
		test.info("App launched");

		lp.enterUsername(DataUtilities.readPropertiesFile("logintestdata", "prod.valid.username"));
		test.info("Username is entered");

		lp.clearPassword();
		test.info("Password cleared");

		lp.clickLogin();
		test.info("Login button clicked");

		WaitUtilities.waitUntil(driver, lp.loginErrorMessage, 30);
		Assert.assertTrue(lp.isErrorMessageSeen(), "Error message should be visible");
		test.info("Login error message is seen");

		WaitUtilities.waitUntil(driver, lp.loginErrorMessage, 30);
		Assert.assertEquals(lp.getErrorMessage(),
				DataUtilities.readPropertiesFile("logintestdata", "login.error.emptypasssword"));
		test.info("Empty password error message verified");

	}

	@Test
	public void TC02_loginToSFDCFreeTrial() throws IOException {

		logger.info("Executing: TC02_loginToSFDCFreeTrial");
		WebDriver driver = getBrowser("chrome", false);
		lp = new LoginPage(driver);

		lp.launchApp(driver);
		test.info("App Launched");

		// Edit the properties file
		lp.enterUsername(DataUtilities.readPropertiesFile("logintestdata", "prod.valid.username"));
		test.info("Valid username Entered");

		lp.enterPassword(DataUtilities.readPropertiesFile("logintestdata", "prod.valid.password"));
		test.info("Valid Password Entered");

		WaitUtilities.waitUntil(driver, lp.startFreeTrial, 30);
		Assert.assertTrue(lp.isFreeTrialSeen(), "Free Trial Option should be available");
		test.info("Free Trial is seen");

	}

	@Test
	public void TC03_checkRememberMe() throws IOException {

		logger.info("Executing: TC03_checkRememberMe");
		WebDriver driver = getBrowser("chrome", false);
		lp = new LoginPage(driver);
		hp = new HomePage(driver);

		lp.launchApp(driver);
		test.info("App launched");

		lp.enterUsername(DataUtilities.readPropertiesFile("logintestdata", "prod.valid.username"));
		test.info("Valid username is entered");

		lp.enterPassword(DataUtilities.readPropertiesFile("logintestdata", "prod.valid.password"));
		test.info("Valid password is entered");

		Assert.assertTrue(lp.clickRememberMe(), "Remember me checkbox should be selected");
		test.info("Remember me checkbox selected");

		lp.clickLogin();
		test.info("Clicked on login button");

		WaitUtilities.waitUntil(driver, hp.userNavMenu, 30);
		Assert.assertTrue(hp.logout(), "logout option should be visible and clickable");
		test.info("Clicked on logout");

		WaitUtilities.waitUntil(driver, lp.savedEmail, 30);
		Assert.assertTrue(lp.isSavedEmailSeen(), "username should be saved");
		test.info("Saved Email message is seen");

		WaitUtilities.waitUntil(driver, lp.savedEmail, 30);
		Assert.assertEquals(lp.getSavedEmail(),
				DataUtilities.readPropertiesFile("logintestdata", "prod.valid.username"),
				"Saved username and entered username should be same");
		test.info("User name is saved " + lp.getSavedEmail());

	}

	@Test(groups = "Login")
	public void TC04A_forgotPassword() throws IOException, InterruptedException {

		logger.info("Executing: TC04A_forgotPassword");
		WebDriver driver = getBrowser("chrome", false);
		lp = new LoginPage(driver);
		hp = new HomePage(driver);

		lp.launchApp(driver);
		test.info("App launched");

		WaitUtilities.waitUntil(driver, lp.forgotPasswordLink, 30);
		Assert.assertTrue(lp.isForgotPasswordDisplayed(), "Forgot password screen should be displayed");
		test.info("Forgot Password Link is seen");

		WaitUtilities.waitUntil(driver, lp.forgotPasswordLink, 20);
		lp.clickForgotPassword();
		test.info("Clicked on Forgot Password Link");

		WaitUtilities.waitUntil(driver, lp.forgotUsername, 30);
		lp.enterForgotUsername(DataUtilities.readPropertiesFile("logintestdata", "prod.valid.actualusername"));
		test.info("Enter username for Forgot Password");

		WaitUtilities.waitUntil(driver, lp.continueButton, 30);
		lp.continueButton.click();
		test.info("Clicked on continue button");

		WaitUtilities.waitUntil(driver, lp.passwordResetScreen, 30);
		Assert.assertTrue(lp.isPasswordResetDisplayed(), "password reset message should be seen");
		test.info("Password Reset Screen is seen");

	}

	@Test(groups = "Login")
	public void TC04B_forgotPassword() throws IOException {

		logger.info("Executing: TC04B_forgotPassword");
		WebDriver driver = getBrowser("chrome", false);
		lp = new LoginPage(driver);
		hp = new HomePage(driver);

		lp.launchApp(driver);
		test.info("App launched");

		lp.enterUsername("123");
		test.info("Enter wrong User name");

		lp.enterPassword("22131");
		test.info("Enter wrong Password");

		lp.clickLogin();
		test.info("Clicked on Login button");

		WaitUtilities.waitUntil(driver, lp.loginErrorMessage, 30);
		Assert.assertEquals(lp.getErrorMessage(), DataUtilities.readPropertiesFile("logintestdata", "login.error.up"));
		test.info("Error message is seen");

	}

}
