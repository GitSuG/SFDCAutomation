package com.sfdc.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sfdc.utilities.WaitUtilities;

public class ContactNewPage {

	static WebDriver driver;
	GeneralButtonsLinks g;
	
	Logger logger = LogManager.getLogger(ContactNewPage.class);
	public ContactNewPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		g = new GeneralButtonsLinks(this.driver);
	}
	
	
	@FindBy(css = "#name_lastcon2")
	public WebElement lastname_c;
	
	@FindBy(css = "#con4")
	public WebElement account_c;
	private String pageName = "New Contact";
	
	
	public boolean clickSaveAndCreateNewContact() {
		
		return g.clickSaveNewButton();
	}
	
	public boolean verifyNewContactPage() {
		
		System.out.println(g.getPageHeaderName());
		System.out.println(pageName);
		if(g.getPageHeaderName().equals(pageName ))
			return true;
		else
			return false;
	}

	private boolean isContactLastnameSeen() {

		WaitUtilities.waitUntil(driver, lastname_c, 30);
		if (lastname_c.isDisplayed()) {
			logger.info("Contact lastname: Visible");
			return true;
		} else {
			logger.info("Contact lastname: Not Visible");
			return false;
		}

	}


	public boolean editContactPage(String lastname, String accountName) {
		
		boolean editStatus = false;
		
		if(enterAccountName(accountName))
			if(enterContactLastname(lastname))
				editStatus = true;
		
		return editStatus;
	}
	
	public boolean enterContactLastname(String lastname) {
		
		if (isContactLastnameSeen()) {
			WaitUtilities.waitUntil(driver, lastname_c, 30);
			lastname_c.sendKeys(lastname);
			logger.info("Contact lastname: Entered");
			return true;
		} else {
			logger.info("Contact lastname: Not Entered");
			return false;
		}
		
	}
	
	private boolean isAccountNameSeen() {
		
		WaitUtilities.waitUntil(driver, account_c, 30);
		if (account_c.isDisplayed()) {
			logger.info("Account name: Visible");
			return true;
		} else {
			logger.info("Account name: Not Visible");
			return false;
		}
		
	}
	
	public boolean enterAccountName(String accountName) {

		if (isAccountNameSeen()) {
			WaitUtilities.waitUntil(driver, account_c, 30);
			account_c.sendKeys(accountName);
			logger.info("Account name: Entered");
			return true;
		} else {
			logger.info("Account name: Not Entered");
			return false;
		}

	}


	
	
}
