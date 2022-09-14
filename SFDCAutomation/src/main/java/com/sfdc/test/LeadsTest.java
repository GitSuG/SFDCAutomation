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
import com.sfdc.pages.LeadsNewPage;
import com.sfdc.pages.LeadsPage;
import com.sfdc.pages.LoginPage;

import com.sfdc.utilities.IOUtilities;

@Listeners(SFDCListeners.class)
public class LeadsTest extends BaseTest {

	static WebDriver driver;
	LoginPage loginPage;
	LeadsPage leadsPage;
	LeadsNewPage newLead;
	HomePage hp;
	Logger logger = LogManager.getLogger(LeadsTest.class.getName());

	@BeforeMethod
	public void setupLead() {
		driver = getBrowser("chrome", false);

		loginPage = new LoginPage(driver);
		loginPage.launchApp(driver);
		loginPage.loginToSFDC();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
		
	}

	@Test
	public void TC20_TestLeadsTab() {

		logger.info("TC20_TestLeadsTab");
		
		hp = new HomePage(driver);
		Assert.assertTrue(hp.clickLeads(), "Leads Page must be seen");
		test.info("Leads Page is seen");

		leadsPage = new LeadsPage(driver);
		Assert.assertTrue(leadsPage.verifyLeadsPage(), "'Leads' must be Page Title");
		test.info("'Leads' is seen as Page Title");
	}

	@Test
	public void TC21_ValidateStdViews() {

		logger.info("TC21_ValidateStdViews");
		
		hp = new HomePage(driver);
		Assert.assertTrue(hp.clickLeads(), "Leads Page mst be seen");
		test.info("Leads Page is seen");

		leadsPage = new LeadsPage(driver);
		Assert.assertTrue(leadsPage.verifyStdLeadViews(), "All standard Lead View Options must be seen and verified");
		test.info("All standard Lead View Options are seen and verified");
	}

	@Test
	public void TC22_TestPriorSelectWithGo() {

		logger.info("TC22_TestPriorSelectWithGo");
		
		hp = new HomePage(driver);
		Assert.assertTrue(hp.clickLeads(), "Leads Page mst be seen");
		test.info("Leads Page is seen");

		leadsPage = new LeadsPage(driver);
		Assert.assertTrue(leadsPage.selectLeadsView("Today's Leads"),
				"Option 'Today's Leads' must be selected from dropdown");
		test.info("Option 'Today's Leads' selected from dropdown");

		hp = new HomePage(driver);
		Assert.assertTrue(hp.logout(), "Login Page must be seen after logout from app");
		test.info("Login Page seen after logout from app");

		loginPage = new LoginPage(driver);
		loginPage.loginToSFDC();
		Assert.assertTrue(hp.clickLeads(), "Leads Page mst be seen");
		test.info("Leads Page is seen");

//		leadsPage = new LeadsPage(driver);
		Assert.assertTrue(leadsPage.clickGoButtonInLeads(), "'Today's Leads' page must be seen");
		test.info("'Today's Leads' page seen");

		// Verify selected page
	}

	@Test
	public void TC23_TestLeadViewOption() {

		logger.info("TC23_TestLeadViewOption");
		
		leadsPage = new LeadsPage(driver);

		hp = new HomePage(driver);
		Assert.assertTrue(hp.clickLeads(), "Leads Page must be seen");
		test.info("Leads Page is seen");

		leadsPage = new LeadsPage(driver);
		Assert.assertTrue(leadsPage.clickGoButtonInLeads(), "'Today's Leads' page must be seen");
		test.info("'Today's Leads' page seen");

		// Verify selected page
	}

	@Test
	public void TC24_CreateNewLead() {

		logger.info("TC24_CreateNewLead");
		
		leadsPage = new LeadsPage(driver);

		hp = new HomePage(driver);
		Assert.assertTrue(hp.clickLeads(), "Leads Page mst be seen");
		test.info("Leads Page is seen");

		leadsPage = new LeadsPage(driver);
		Assert.assertTrue(leadsPage.clickNewLeadButton(), "Create New Lead Page must be seen");
		test.info("Create New Lead Page seen");
		/*
		 * edit lastname company name
		 */
		newLead = new LeadsNewPage(driver);
		String leadLastname = IOUtilities.readPropertiesFile("leadstestdata", "prod.leads.new.leadsLastname");
		Assert.assertTrue(newLead.enterLeadLastname(leadLastname),
				"Last name must be entered in Lead lastname field");
		test.info("Last name entered in Lead lastname field");

		String companyName = IOUtilities.readPropertiesFile("leadstestdata", "prod.leads.new.leadCompanyName");
		Assert.assertTrue(newLead.enterCompanyName(companyName),
				"Company name must be entered in Lead company name field");
		test.info("Company name entered in Lead  company name field");

		Assert.assertTrue(leadsPage.clickSaveLeadButton(), "New Lead must be created");
		test.info("New Lead is created");

		// Verify new lead
	}

}
