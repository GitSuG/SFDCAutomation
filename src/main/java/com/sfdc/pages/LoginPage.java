package com.sfdc.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sfdc.utilities.*;


public class LoginPage extends BaseSFDCPage {

	public static WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "username")
	public WebElement username;

	@FindBy(id = "password")
	public WebElement password;

	@FindBy(id = "Login")
	public WebElement loginButton;

	@FindBy(css = "#error")
	public WebElement loginErrorMessage;

	@FindBy(id = "rememberUn")
	public WebElement rememberMe;

	@FindBy(css = "#idcard-identity")
	public WebElement savedEmail;

	@FindBy(id = "forgot_password_link")
	public WebElement forgotPasswordLink;

	@FindBy(xpath = "//div[@class='message']/p[1]")
	public WebElement mailMessage;

	@FindBy(xpath = "/html/body/div[2]/div/div[6]/div[1]/div/div[8]/div/div/div/a")
	public WebElement startFreeTrial;

	@FindBy(id = "un")
	public WebElement forgotUsername;

	@FindBy(id = "continue")
	public WebElement continueButton;

	@FindBy(id = "forgotPassForm")
	public WebElement passwordResetScreen;

	@FindBy(xpath = "//a[text()='Return to Login']")
	public WebElement returnToLoginButton;

	public void enterUsername(String name) {
		
		WaitUtilities.waitUntil(driver, username, 30);
		username.sendKeys(name);
		logger.info("Entered username");
		
	}

	public String getUsername() {

		WaitUtilities.waitUntil(driver, username, 30);
		return username.getText();
	}

	public void enterPassword(String pswd) {

		WaitUtilities.waitUntil(driver, password, 30);
		password.sendKeys(pswd);
		logger.info("Entered password");
		
	}

	public String getPassword() {

		WaitUtilities.waitUntil(driver, password, 30);
		return password.getText();
	}

	public void clearPassword() {

		WaitUtilities.waitUntil(driver, password, 30);
		password.clear();
	}

	public boolean clickLogin() {

		boolean loginSuccess = false;
		WaitUtilities.waitUntil(driver, loginButton, 30);
		if(loginButton.isDisplayed()) {
			loginButton.click();
			logger.info("Login button clicked");
			loginSuccess = true;
		}
		return loginSuccess;
	}

	
	public boolean loginToSFDC() {
		
		String username = IOUtilities.readPropertiesFile("logintestdata", "prod.valid.username");
		String password = IOUtilities.readPropertiesFile("logintestdata", "prod.valid.password");
//		System.out.println("Username: "+username);
//		System.out.println("Password: "+password);
		enterUsername(username);
		enterPassword(password);
		boolean loginSuccess = clickLogin();
		
		return loginSuccess;
	}
	
	public boolean isErrorMessageSeen() {

		if (loginErrorMessage.isDisplayed()) {
			logger.info("Error Message displayed");
			return true;
		} else {
			logger.info("Error Message is not displayed");
			return false;
		}
	}

	public String getErrorMessage() {

		String errMsg = loginErrorMessage.getText();
		if (errMsg.isEmpty()) {
			logger.info("Fetch Failed: Error message ");
		} else {
			logger.info("Fetch Success: +'" + errMsg + "'");
		}
		return errMsg;

	}

	public boolean isFreeTrialSeen() {

		if (startFreeTrial.isDisplayed())
			return true;
		else
			return false;
	}

	public boolean clickRememberMe() {

		boolean checkboxStatus = false;
		
		if(rememberMe.isSelected()) {
			checkboxStatus = true;
			logger.info("Remember me checkbox already selected");
		}else {
			rememberMe.click();
			if(rememberMe.isSelected())
				checkboxStatus = true;
		}
		return checkboxStatus;
	}

	public boolean isSavedEmailSeen() {
		if(savedEmail.isDisplayed())
			return true;
		else
			return false;
	}
	
	public String getSavedEmail() {

		String savedAddress = savedEmail.getText();
		
		if(savedAddress.isEmpty()) {
			logger.info("Fetch Failed: Saved email field empty");
			return null;
		}
		else {
			logger.info("Fetch Success: Saved Email Address: '"+savedAddress+"'");
			return savedAddress;
		}
	}

	public void clickForgotPassword() {

		forgotPasswordLink.click();
		logger.info("Clicked: Forgot Password Link");
	}

	public String getConfirmMailMessage() {

		return mailMessage.getText();
	}

	
	public void enterForgotUsername(String text) {
		forgotUsername.sendKeys(text);
		logger.info("Forgot Username: Username entered");
	}

	public boolean isForgotPasswordDisplayed() {
		if(forgotPasswordLink.isDisplayed())
			return true;
		else
			return false;
	}

	public boolean isPasswordResetDisplayed() {
		if(passwordResetScreen.isDisplayed())
			return true;
		else
			return false;
	}

}
