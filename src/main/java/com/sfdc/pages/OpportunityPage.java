package com.sfdc.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sfdc.utilities.WaitUtilities;

public class OpportunityPage extends BaseSFDCPage {

	String createType = "Opportunity";
	final String pageTitle = "Opportunities";
	String[] stdOpportunityView = { "All Opportunities", "Closing Next Month", "Closing This Month", "My Opportunities",
			"New Last Week", "New This Week", "Opportunity Pipeline", "Private", "Recently Viewed Opportunities",
			"Won" };
	private static WebDriver driver;
	GeneralButtonsLinks g;
	OpportunityNewPage op;

	public OpportunityPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		g = new GeneralButtonsLinks(driver);
		op = new OpportunityNewPage(driver);
	}

	@FindBy(xpath = "//div[@class='bPageTitle']/div/div/h1")
	public WebElement oppReportTitle;

	@FindBy(linkText = "Opportunity Pipeline")
	public WebElement oppPipelineReport;

	@FindBy(linkText = "Stuck Opportunities")
	public WebElement oppStuckReport;

	@FindBy(xpath = "//select[@id='quarter_q']/option")
	public List<WebElement> interval;

	@FindBy(xpath = "//select[@id='open']/option")
	public List<WebElement> include;

	@FindBy(xpath = "//table[@class='opportunitySummary']/tbody/tr[3]/td/input[@name='go']")
	public WebElement runReport;

	/**
	 * @return
	 */
	
	public boolean createQuarterlySummaryReport(String intervalOption, String includeOption) {
		
		boolean reportGenerated = false;
		
		if(selectReportSummaryIntervalOption(intervalOption))
			if(selectReportSummaryIncludeOption(includeOption))
				if(clickRunReportButton())
					reportGenerated = true;
		
		return reportGenerated;
	}
	
	public boolean verifyOpportunitiesDropDown() {

		return g.verifyAllViewOption(stdOpportunityView);

	}

	public boolean verifyNewOpportunityPage() {
		
		  if(g.getPageHeaderName().equals("New Opportunity"))
			  return true;
		  else
			  return false;
	}

	public boolean createNewOpportunity(String oppName, String accName, String closeDate, String leadSrc, String stage,
			String probabilityValue, String campSrc) {

		if (g.clickNewButton())
			if (op.enterOpportunityName(oppName))
				if (op.enterAccountName(accName))
					if (op.enterCloseDate(closeDate))
						if (op.selectLeadSource(leadSrc))
							if (op.selectStage(stage))
								if (op.enterProbality(probabilityValue))
									if (op.enterPrimaryCampaignSource(campSrc))
										return true;

		return false;
	}

	private boolean isPipelineReportSeen() {

		WaitUtilities.waitUntil(driver, oppPipelineReport, 30);
		if (oppPipelineReport.isDisplayed()) {
			logger.info("Pipeline Report: Visible");
			return true;
		} else {
			logger.info("Pipeline Report: Not Visible");
			return false;
		}

	}

	public boolean clickPipelineReportLink() {

		if (isPipelineReportSeen()) {
			WaitUtilities.waitUntil(driver, oppPipelineReport, 30);
			oppPipelineReport.click();
			logger.info("Pipeline Report: Clicked");
			return true;
		} else {
			logger.info("Pipeline Report: Not Clicked");
			return false;
		}

	}

	public boolean verifyOpportunityReportName(String option) {
		if (oppReportTitle.getText().equals(option))
			return true;
		else
			return false;
	}

	private boolean isStuckReportSeen() {

		WaitUtilities.waitUntil(driver, oppStuckReport, 30);
		if (oppStuckReport.isDisplayed()) {
			logger.info("Stuck Report: Visible");
			return true;
		} else {
			logger.info("Stuck Report: Not Visible");
			return false;
		}

	}

	public boolean clickStuckReportLink() {

		if (isStuckReportSeen()) {
			WaitUtilities.waitUntil(driver, oppStuckReport, 30);
			oppStuckReport.click();
			logger.info("Stuck Report: Clicked");
			return true;
		} else {
			logger.info("Stuck Report: Not Clicked");
			return false;
		}

	}

	private boolean isIntervalDropDownSeen() {

		WaitUtilities.waitUntil(driver, interval.get(0), 30);
		if (interval.get(0).isDisplayed()) {
			logger.info("Quarterly Summary Interval DropDown: Visible");
			return true;
		} else {
			logger.info("Quarterly Summary Interval DropDown: Not Visible");
			return false;
		}

	}

	private boolean clickIntervalDropDown() {

		if (isIntervalDropDownSeen()) {
			WaitUtilities.waitUntil(driver, interval.get(0), 30);
			interval.get(0).click();
			logger.info("Quarterly Summary Interval DropDown: Clicked");
			return true;
		} else {
			logger.info("Quarterly Summary Interval DropDown: Not Clicked");
			return false;
		}

	}

	public boolean selectReportSummaryIntervalOption(String intervalOption) {

		boolean isintervalSelected = false;

		if (clickIntervalDropDown()) {
			for (int i = 0; i < interval.size(); i++) {

				WaitUtilities.waitUntil(driver, interval.get(i), i);
				String currentOption = interval.get(i).getText();

				if (intervalOption.equals(currentOption)) {
					logger.info("Fetch Success: Quarterly Summary Interval option: " + currentOption);
					interval.get(i).click();
					isintervalSelected = true;
				}
			}
		}

		return isintervalSelected;
	}

	private boolean isIncludeDropDownSeen() {

		WaitUtilities.waitUntil(driver, include.get(0), 30);
		if (include.get(0).isDisplayed()) {
			logger.info("Quarterly Summary Include DropDown: Visible");
			return true;
		} else {
			logger.info("Quarterly Summary Include DropDown: Not Visible");
			return false;
		}

	}

	private boolean clickIncludeDropDown() {

		if (isIncludeDropDownSeen()) {
			WaitUtilities.waitUntil(driver, include.get(0), 30);
			include.get(0).click();
			logger.info("Quarterly Summary Include DropDown: Clicked");
			return true;
		} else {
			logger.info("Quarterly Summary Include DropDown: Not Clicked");
			return false;
		}

	}

	public boolean selectReportSummaryIncludeOption(String includeOption) {

		boolean isIncludeSelected = false;

		if (clickIncludeDropDown()) {
			for (int i = 0; i < include.size(); i++) {

				WaitUtilities.waitUntil(driver, include.get(i), i);
				String currentOption = include.get(i).getText();

				if (includeOption.equals(currentOption)) {
					logger.info("Fetch Success: Quarterly Summary Include option: " + currentOption);
					include.get(i).click();
					isIncludeSelected = true;
				}
			}
		}

		return isIncludeSelected;
	}

	private boolean isRunReportButtonSeen() {

		WaitUtilities.waitUntil(driver, runReport, 30);
		if (runReport.isDisplayed()) {
			logger.info("Run Report Button: Visible");
			return true;
		} else {
			logger.info("Run Report Button: Not Visible");
			return false;
		}

	}

	public boolean clickRunReportButton() {

		if (isRunReportButtonSeen()) {
			WaitUtilities.waitUntil(driver, runReport, 30);
			runReport.click();
			logger.info("Run Report Button: Clicked");
			return true;
		} else {
			logger.info("Run Report Button: Not Clicked");
			return false;
		}

	}

}
