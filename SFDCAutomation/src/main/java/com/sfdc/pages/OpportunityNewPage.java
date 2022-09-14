package com.sfdc.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sfdc.utilities.WaitUtilities;

public class OpportunityNewPage extends BaseSFDCPage {

	static WebDriver driver;
	GeneralButtonsLinks g;

	public OpportunityNewPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		g = new GeneralButtonsLinks(driver);
	}

	@FindBy(id = "opp3")
	public WebElement oppName;

	@FindBy(css = "#opp4")
	public WebElement accountName;

	@FindBy(id = "opp9")
	public WebElement closeDate;

	@FindBy(xpath = "//select[@id='opp11']/option")
	public List<WebElement> stage;

	@FindBy(id = "opp12")
	public WebElement probality;

	@FindBy(xpath = "//select[@id='opp6']/option")
	public List<WebElement> leadSource;

	@FindBy(css = "#opp17")
	public WebElement primaryCampaignSource;

	public boolean saveNewOpportunity() {
		return g.clickSaveButton();
	}

	private boolean isOpportunityNameSeen() {

		WaitUtilities.waitUntil(driver, oppName, 30);
		if (oppName.isDisplayed()) {
			logger.info("Opportunity Name: Visible");
			return true;
		} else {
			logger.info("Opportunity Name: Not Visible");
			return false;
		}

	}

	public boolean enterOpportunityName(String oppNameValue) {

		if (isOpportunityNameSeen()) {
			WaitUtilities.waitUntil(driver, oppName, 30);
			oppName.click();
			logger.info("Opportunity Name: Clicked");
			oppName.sendKeys(oppNameValue);
			return true;
		} else {
			logger.info("Opportunity Name: Not Clicked");
			return false;
		}

	}

	private boolean isAccountNameSeen() {

		WaitUtilities.waitUntil(driver, accountName, 30);
		if (accountName.isDisplayed()) {
			logger.info("Account Name: Visible");
			return true;
		} else {
			logger.info("Account Name: Not Visible");
			return false;
		}

	}

	public boolean enterAccountName(String accountNameValue) {

		if (isAccountNameSeen()) {
			WaitUtilities.waitUntil(driver, accountName, 30);
			accountName.click();
			logger.info("Account Name: Clicked");
			accountName.sendKeys(accountNameValue);
			return true;
		} else {
			logger.info("Account Name: Not Clicked");
			return false;
		}

	}

	private boolean isCloseDateSeen() {

		WaitUtilities.waitUntil(driver, closeDate, 30);
		if (closeDate.isDisplayed()) {
			logger.info("Close Date: Visible");
			return true;
		} else {
			logger.info("Close Date: Not Visible");
			return false;
		}

	}

	public boolean enterCloseDate(String closeDateValue) {

		if (isCloseDateSeen()) {
			WaitUtilities.waitUntil(driver, closeDate, 30);
			closeDate.click();
			logger.info("Close Date: Clicked");
			closeDate.sendKeys(closeDateValue);
			return true;
		} else {
			logger.info("Close Date: Not Clicked");
			return false;
		}

	}

	private boolean isStageSeen() {

		WaitUtilities.waitUntil(driver, stage.get(0), 30);
		if (stage.get(0).isDisplayed()) {
			logger.info("Stage: Visible");
			return true;
		} else {
			logger.info("Stage: Not Visible");
			return false;
		}

	}

	public boolean selectStage(String stageValue) {

		boolean stageChosen = false;
		if (isStageSeen()) {
			WaitUtilities.waitUntil(driver, stage.get(0), 30);
			for (WebElement w : stage) {
				if(stageValue.equals(w.getText())) {
					w.click();
					logger.info("Stage: Clicked");

					stageChosen = true;
				}
			}
		} else {
			logger.info("Stage: Not Clicked");
			stageChosen = false;
		}
		return stageChosen;
	}

	private boolean isProbalitySeen() {

		WaitUtilities.waitUntil(driver, probality, 30);
		if (probality.isDisplayed()) {
			logger.info("Probality: Visible");
			return true;
		} else {
			logger.info("Probality: Not Visible");
			return false;
		}

	}

	public boolean enterProbality(String probalityValue) {

		if (isProbalitySeen()) {
			WaitUtilities.waitUntil(driver, probality, 30);
			probality.click();
			logger.info("Probality: Clicked");
			probality.sendKeys(probalityValue);
			return true;
		} else {
			logger.info("Probality: Not Clicked");
			return false;
		}

	}

	private boolean isLeadSourceSeen() {

		WaitUtilities.waitUntil(driver, leadSource.get(0), 30);
		if (leadSource.get(0).isDisplayed()) {
			logger.info("Lead Source: Visible");
			return true;
		} else {
			logger.info("Lead Source: Not Visible");
			return false;
		}

	}

	public boolean selectLeadSource(String leadSrc) {

		boolean leadSrcChosen = false;
		if (isLeadSourceSeen()) {
			WaitUtilities.waitUntil(driver, leadSource.get(0), 30);
			for (WebElement w : leadSource) {
				if (leadSrc.equals(w.getText())) {
					w.click();
					logger.info("Lead Source: Selected");
					leadSrcChosen = true;
				}
			}

		} else {
			logger.info("Lead Source: Not Selected");
			leadSrcChosen = false;
		}
		return leadSrcChosen;
	}

	private boolean isCampaignSourceSeen() {

		WaitUtilities.waitUntil(driver, primaryCampaignSource, 30);
		if (primaryCampaignSource.isDisplayed()) {
			logger.info("Campaign Source: Visible");
			return true;
		} else {
			logger.info("Campaign Source: Not Visible");
			return false;
		}

	}

	public boolean enterPrimaryCampaignSource(String primarySource) {

		if (isCampaignSourceSeen()) {
			WaitUtilities.waitUntil(driver, primaryCampaignSource, 30);
			primaryCampaignSource.click();
			logger.info("Campaign Source: Clicked");
			primaryCampaignSource.sendKeys(primarySource);
			logger.info("Campaign Source: Entered");
			return true;
		} else {
			logger.info("Campaign Source: Not entered");
			return false;
		}

	}

}
