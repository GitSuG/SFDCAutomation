package com.sfdc.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sfdc.utilities.WaitUtilities;

public class LeadsNewPage extends BaseSFDCPage {

	private static WebDriver driver;
	LeadsPage lp;
	
	public LeadsNewPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		lp = new LeadsPage(driver);
	}

	@FindBy(id = "name_lastlea2")
	public WebElement lastName_L;

	@FindBy(id = "lea3")
	public WebElement company_L;

	/**
	 * @param lastName
	 * @param companyName
	 * @return
	 */
	

	private boolean isLeadLastnameSeen() {

		WaitUtilities.waitUntil(driver, lastName_L, 30);
		if (lastName_L.isDisplayed()) {
			logger.info("Lead lastname: Visible");
			return true;
		} else {
			logger.info("Lead lastname: Not Visible");
			return false;
		}

	}

	
	public boolean enterLeadLastname(String lastname) {
		
		if (isLeadLastnameSeen()) {
			WaitUtilities.waitUntil(driver, lastName_L, 30);
			lastName_L.sendKeys(lastname);
			logger.info("Lead lastname: Entered");
			return true;
		} else {
			logger.info("Lead lastname: Not Entered");
			return false;
		}
		
	}
	
	private boolean isCompanyNameSeen() {
		
		WaitUtilities.waitUntil(driver, company_L, 30);
		if (company_L.isDisplayed()) {
			logger.info("Company name: Visible");
			return true;
		} else {
			logger.info("Company name: Not Visible");
			return false;
		}
		
	}
	
	public boolean enterCompanyName(String companyName) {

		if (isCompanyNameSeen()) {
			WaitUtilities.waitUntil(driver, company_L, 30);
			company_L.sendKeys(companyName);
			logger.info("Company name: Entered");
			return true;
		} else {
			logger.info("Company name: Not Entered");
			return false;
		}

	}

}
