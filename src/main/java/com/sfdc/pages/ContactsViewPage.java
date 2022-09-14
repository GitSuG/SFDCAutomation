package com.sfdc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sfdc.utilities.WaitUtilities;

public class ContactsViewPage extends BaseSFDCPage {

	static WebDriver driver;
	private GeneralButtonsLinks g;

	public ContactsViewPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		g = new GeneralButtonsLinks(driver);
	}

	@FindBy(css = "#fname")
	public WebElement viewName_c;

	@FindBy(css = "#devname")
	public WebElement viewUniqueName_c;
	
	@FindBy(xpath = "//div[@class='requiredInput']/child::div[@class='errorMsg']")
	public WebElement viewNameErrMsg_c;
		
	private String pageName = "";

	public boolean cancelCreateContactView() {

		return g.clickCancelViewButton();
	}

	public boolean verifyNewContactViewPage() {

		if (g.getPageHeaderName().equals(pageName ))
			return true;
		else
			return false;
	}

	public boolean editContactViewPage(String contactViewName, String contactUniqueViewName) {

		boolean editStatus = false;

		if (enterContactViewName(contactViewName))
			if (enterContactUniqueViewName(contactUniqueViewName))
				editStatus = true;

		return editStatus;
	}

	private boolean isContactUniqueViewnameSeen() {

		WaitUtilities.waitUntil(driver, viewUniqueName_c, 30);
		if (viewUniqueName_c.isDisplayed()) {
			logger.info("Contact Unique View name: Visible");
			return true;
		} else {
			logger.info("Contact Unique View name: Not Visible");
			return false;
		}
	}

	public boolean enterContactUniqueViewName(String contactUniqueViewName) {

		if (isContactUniqueViewnameSeen()) {
			WaitUtilities.waitUntil(driver, viewUniqueName_c, 30);
			viewUniqueName_c.click();
			viewUniqueName_c.sendKeys(contactUniqueViewName);
			logger.info("Contact Unique View name: Entered");
			return true;
		} else {
			logger.info("Contact Unique View name: Not Entered");
			return false;
		}

	}
	
	public boolean isContactViewNameErrorMessageSeen() {
		
		String errMsg = "Error: You must enter a value";
		boolean errStatus = false;
		WaitUtilities.waitUntil(driver, viewNameErrMsg_c, 30);
		if (viewNameErrMsg_c.isDisplayed()) { 
			logger.info("Contact View name:Error Message: Visible");
			String actualMsg = viewNameErrMsg_c.getText();
			if(errMsg.equals(actualMsg))
				errStatus = true;
			else
				errStatus = false;
		}else
			logger.info("Contact View name:Error Message: Not Visible");
		
		return errStatus;
	}

	private boolean isContactViewNameSeen() {

		WaitUtilities.waitUntil(driver, viewName_c, 30);
		if (viewName_c.isDisplayed()) {
			logger.info("Contact View name: Visible");
			return true;
		} else {
			logger.info("Contact View name: Not Visible");
			return false;
		}

	}

	public boolean enterContactViewName(String contactViewName) {

		if (isContactViewNameSeen()) {
			WaitUtilities.waitUntil(driver, viewName_c, 30);
			viewName_c.click();
			viewName_c.sendKeys(contactViewName);
			logger.info("Contact View name: Entered");
			return true;
		} else {
			logger.info("Contact View name: Not Entered");
			return false;
		}

	}
	
	public boolean clickNewContactViewButton() {
		return g.clickCreateNewViewLink();
	}

	public boolean clickSaveContactViewButton() {
		return g.clickSaveViewButton();
	}

	public boolean clickCancelContactViewButton() {
		return g.clickCancelButton();
	}

	public boolean clickSaveNewContactButton() {
		return g.clickSaveNewButton();
	}
}
