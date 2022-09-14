package com.sfdc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sfdc.utilities.WaitUtilities;

public class AccountNewPage extends BaseSFDCPage {

	private static WebDriver driver;

	public AccountNewPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='contactHeaderRow']//following::h2[@class='topName']")
	public WebElement accountNameTitle;
	
	@FindBy(id = "acc2")
	public WebElement accountNameField;
	
	private boolean isAccountNameFieldSeen() {
		
		WaitUtilities.waitUntil(driver, accountNameField, 30);
		if(accountNameField.isDisplayed()) {
			logger.info("Account Name Field: Visible");
			return true;
		}else{
			logger.info("Account Name Field: Not Visible");
			return false;
		}
	}
	
	public boolean enterAccountName(String accountNameText) {
		
		if(isAccountNameFieldSeen()) {
			WaitUtilities.waitUntil(driver, accountNameField, 30);
			accountNameField.sendKeys(accountNameText);
			logger.info("Account Name Entry: Successful: "+accountNameText);
			return true;
		}else{
			logger.info("Account Name Entry: Not successful");
			return false;
		}
	}
	
	private boolean isAccountNameTitleSeen() {
		
		WaitUtilities.waitUntil(driver, accountNameTitle, 30);
		if(accountNameTitle.isDisplayed()) {
			logger.info("Account Name Title: Visible");
			return true;
		}else{
			logger.info("Account Name Title: Not Visible");
			return false;
		}
	}
	
	public String getAccountNameTitle() {
		
		String accountNameText = null;
		if(isAccountNameTitleSeen()) {
			WaitUtilities.waitUntil(driver, accountNameTitle, 30);
			 accountNameText = accountNameTitle.getText();
			logger.info("Account Name Read: Successful: "+accountNameText);
			
		}else{
			logger.info("Account Name Read: Not successful");
			
		}
		
		return accountNameText;
	}
	
}
