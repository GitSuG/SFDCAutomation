package com.sfdc.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.sfdc.listeners.SFDCListeners;
import com.sfdc.pages.HomePage;
import com.sfdc.pages.LoginPage;
import com.sfdc.pages.OpportunityPage;

import com.sfdc.utilities.IOUtilities;

@Listeners(SFDCListeners.class)
public class OpportunitiesTest extends BaseTest{

	
	static WebDriver driver;
	LoginPage loginPage;
	HomePage homepPage;
	OpportunityPage oppPage;
	Logger logger = LogManager.getLogger(OpportunitiesTest.class.getName());
	
	@BeforeMethod
	private boolean launchAndLoginSFDC() {
		driver = getBrowser("chrome", false);
		boolean isSuccess = false;
		loginPage = new LoginPage(driver);
		
		loginPage.launchApp(driver);
		isSuccess = loginPage.loginToSFDC();
		return isSuccess;
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
		
	}

	@Test
	public void TC15_TestCreateNewMenu() {
		
		logger.info("TC15_TestCreateNewMenu");
		
		homepPage = new HomePage(driver);
		Assert.assertTrue(homepPage.clickOpportunities(), "Opportunities Home page must be seen");
		test.info("Opportunites Home page is seen");
		
		oppPage = new OpportunityPage(driver);
		Assert.assertTrue(oppPage.verifyOpportunitiesDropDown(), "All Opportunity View options must be verified");
		test.info("All Opportunity View options verified");
		
	}

	@Test
	public void TC16_TestCreateNewOpportunity() {
		
		logger.info("TC16_TestCreateNewOpportunity");
		
		homepPage = new HomePage(driver);
		Assert.assertTrue(homepPage.clickOpportunities(), "Opportunities Home page must be seen");
		test.info("Opportunites Home page is seen");
		
		oppPage = new OpportunityPage(driver);
		String oppName = IOUtilities.readPropertiesFile("opportunitiestestdata",
				"prod.opportunities.new.opportunitiesName");
		String accName = IOUtilities.readPropertiesFile("opportunitiestestdata",
				"prod.opportunities.new.accountName");
		String closeDate = IOUtilities.readPropertiesFile("opportunitiestestdata",
				"prod.opportunities.new.closeDate"); 
		String leadSrc = IOUtilities.readPropertiesFile("opportunitiestestdata",
				"prod.opportunities.new.leadSource");
		String stage = IOUtilities.readPropertiesFile("opportunitiestestdata",
				"prod.opportunities.new.stage");
		String probabilityValue = IOUtilities.readPropertiesFile("opportunitiestestdata",
				"prod.opportunities.new.probabilityValue");
		String campSrc = IOUtilities.readPropertiesFile("opportunitiestestdata",
				"prod.opportunities.new.campaignSource");
		Assert.assertTrue(oppPage.createNewOpportunity(oppName, accName, closeDate, leadSrc, stage, probabilityValue, campSrc), "New Opportunity must be created");
		test.info("New Opportunity is created");
	
		Assert.assertTrue(oppPage.verifyNewOpportunityPage(), "'New Opportunity' page must be seen");
		test.info("'New Opportunity' page is seen");
		
	}

	@Test
	public void TC17_TestOpportunityPipelineReport() {
		
		logger.info("TC17_TestOpportunityPipelineReport");
		
		homepPage = new HomePage(driver);
		Assert.assertTrue(homepPage.clickOpportunities(), "Opportunities Home page must be seen");
		test.info("Opportunites Home page is seen");
		
		oppPage = new OpportunityPage(driver);
		Assert.assertTrue(oppPage.clickPipelineReportLink(), "Opportunity Pipeline Report must be seen");
		test.info("Opportunity Pipeline Report seen");
		
		//WebElement check
		Assert.assertTrue(oppPage.verifyOpportunityReportName("Opportunity Pipeline"), "Pipeline Report name should be seen");
		test.info("Pipeline Report name is seen");
	}
	
	@Test
	public void TC18_TestStuckOpportunityReport() {
		
		logger.info("TC18_TestStuckOpportunityReport");
		
		homepPage = new HomePage(driver);
		Assert.assertTrue(homepPage.clickOpportunities(), "Opportunities Home page must be seen");
		test.info("Opportunites Home page is seen");
		
		oppPage = new OpportunityPage(driver);
		Assert.assertTrue(oppPage.clickStuckReportLink(), "Stuck Opportunities Report must be seen");
		test.info("Stuck Opportunities Report seen");
		
		//WebElement check
		Assert.assertTrue(oppPage.verifyOpportunityReportName("Stuck Opportunities"), "Stuck Opportunities Report name should be seen");
		test.info("Stuck Opportunities Report name is seen");
		
	}
	
	@Test
	public void TC19_TestQuarterlySummaryReport() {
		
		logger.info("TC19_TestQuarterlySummaryReport");
		
		homepPage = new HomePage(driver);
		Assert.assertTrue(homepPage.clickOpportunities(), "Opportunities Home page must be seen");
		test.info("Opportunites Home page is seen");
		
		oppPage = new OpportunityPage(driver);
		String intervalOption = IOUtilities.readPropertiesFile("opportunitiestestdata",
				"prod.opportunities.report.intervalOption");
		String includeOption = IOUtilities.readPropertiesFile("opportunitiestestdata",
				"prod.opportunities.report.includeOption");
		Assert.assertTrue(oppPage.createQuarterlySummaryReport(intervalOption, includeOption), "Opportunity Report must be seen");
		test.info("Opportunity Report seen");
		
		Assert.assertTrue(oppPage.verifyOpportunityReportName("Opportunity Report"), "Opportunity Report name should be seen");
		test.info("Opportunity Report name is seen");
		
	}
	
}
