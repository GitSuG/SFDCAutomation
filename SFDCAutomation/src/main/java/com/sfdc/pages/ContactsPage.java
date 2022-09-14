package com.sfdc.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sfdc.utilities.WaitUtilities;

public class ContactsPage extends BaseSFDCPage {

	final String pageName = "Contacts";

	private static WebDriver driver;

	HomePage hp;
	GeneralButtonsLinks g;

	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		hp = new HomePage(driver);
		g = new GeneralButtonsLinks(driver);
	}

	@FindBy(xpath = "//div[@class='pbBody']/table/tbody/tr[2]/th/a")
	public WebElement recentContact;
		
	
	/**
	 * @return
	 */
	public boolean clickContacts() {
		return hp.selectNavigationTab(pageName);
	}

	public boolean selectFromContactsDropdown(String option) {
		
		return g.selectViewOption(option);
		
	}

	public boolean selectFilterFromContactsDropdown(String option) {
		
		return g.selectFilterOverviewOption(option);
		
	}
	
	public boolean verifyContactPage() {

		if (g.getPageTitleName().equals(pageName))
			return true;
		else
			return false;
	}

	public boolean clickNewContactViewButton() {

		return g.clickCreateNewViewLink();
	}

	public boolean clickNewContactButton() {

		return g.clickNewButton();
	}

	public boolean clickARecentContact() {

		if (isRecentContactSeen()) {
			WaitUtilities.waitUntil(driver, recentContact, 30);
			recentContact.click();
			logger.info("Recent Contact Link: Clicked");
			return true;
		} else {
			logger.info("Recent Contact Link: Not Clicked");
			return false;
		}
	}

	private boolean isRecentContactSeen() {

		WaitUtilities.waitUntil(driver, recentContact, 30);
		if (recentContact.isDisplayed()) {
			logger.info("Recent Contact Link: Visible");
			return true;
		} else {
			logger.info("Recent Contact Link: Not Visible");
			return false;
		}
	}


}
