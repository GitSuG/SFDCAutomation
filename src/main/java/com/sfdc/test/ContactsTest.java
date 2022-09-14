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
import com.sfdc.pages.ContactNewPage;
import com.sfdc.pages.ContactsPage;
import com.sfdc.pages.ContactsViewPage;
import com.sfdc.pages.HomePage;
import com.sfdc.pages.LoginPage;

import com.sfdc.utilities.IOUtilities;

@Listeners(SFDCListeners.class)
public class ContactsTest extends BaseTest {

	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactPage;
	ContactNewPage contactNewPage;
	ContactsViewPage contactNewViewPage;
	Logger logger = LogManager.getLogger(ContactsTest.class.getName());

	@BeforeMethod
	public void setup() {

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
	public void TC25_CreateNewContact() {

		logger.info("TC25_CreateNewContact");
		
		homePage = new HomePage(driver);
		Assert.assertTrue(homePage.clickContacts(), "Contacts Page must be seen");
		test.info("Contacts page is seen");

		contactPage = new ContactsPage(driver);
		Assert.assertTrue(contactPage.clickNewContactButton(), "New Contact Page must be seen");
		test.info("New Contact Page is seen");

		contactNewPage = new ContactNewPage(driver);
		String lastname = IOUtilities.readPropertiesFile("contactstestdata", "prod.contacts.new.contactLastname");
		Assert.assertTrue(contactNewPage.enterContactLastname(lastname),
				"Last name for Contacts must be entered");
		test.info("Last name for Contacts entered");

		String accountName = IOUtilities.readPropertiesFile("contactstestdata", "prod.contacts.new.contactAccountName");
		Assert.assertTrue(contactNewPage.enterAccountName(accountName),
				"Account name for Contacts must be entered");
		test.info("Account name for Contacts entered");

	}

	@Test
	public void TC26_CreateNewContact() {

		logger.info("TC26_CreateNewContact");

		homePage = new HomePage(driver);
		Assert.assertTrue(homePage.clickContacts(), "Contacts Page must be seen");
		test.info("Contacts page is seen");

		contactPage = new ContactsPage(driver);
		Assert.assertTrue(contactPage.clickNewContactViewButton(), "New Contact View Page must be seen");
		test.info("New Contact View Page seen");
		
		contactNewViewPage = new ContactsViewPage(driver);
		String viewname = IOUtilities.readPropertiesFile("contactstestdata", "prod.contacts.new.contactViewname");
		Assert.assertTrue(contactNewViewPage.enterContactViewName(viewname),
				"View name for Contacts must be entered");
		test.info("View name for Contacts entered");
		
		String viewUniquename = IOUtilities.readPropertiesFile("contactstestdata", "prod.contacts.new.contactViewUniquename");
		Assert.assertTrue(contactNewViewPage.enterContactUniqueViewName(viewUniquename),
				"View Unique name for Contacts must be entered");
		test.info("View Unique name for Contacts entered");
		
		Assert.assertTrue(contactNewViewPage.clickSaveContactViewButton(), "Contact View must be saved ");
		test.info("Contact View is saved");
		
	}
	
	@Test
	public void TC27_SelectFilterForContacts() {

		logger.info("TC27_SelectFilterForContacts");

		homePage = new HomePage(driver);
		Assert.assertTrue(homePage.clickContacts(), "Contacts Page must be seen");
		test.info("Contacts page is seen");

		contactPage = new ContactsPage(driver);
		String option = IOUtilities.readPropertiesFile("contactstestdata", "prod.contacts.new.contactFilterOption");
		Assert.assertTrue(contactPage.selectFilterFromContactsDropdown(option),
				"Selected Filter Contacts must be seen");
		test.info("Selected Filter Contacts are seen");

	}

	@Test
	public void TC28_SelectMyContacts() {

		logger.info("TC28_SelectMyContacts");
		
		homePage = new HomePage(driver);
		Assert.assertTrue(homePage.clickContacts(), "Contacts Page must be seen");
		test.info("Contacts page is seen");

		contactPage = new ContactsPage(driver);
		String option = IOUtilities.readPropertiesFile("contactstestdata", "prod.contacts.new.contactViewOption");
		Assert.assertTrue(contactPage.selectFromContactsDropdown(option), "Selected Contact details must be seen");
		test.info("Selected Contact details is seen");

	}

	@Test
	public void TC29_ViewAContact() {

		logger.info("TC29_ViewAContact");

		homePage = new HomePage(driver);
		Assert.assertTrue(homePage.clickContacts(), "Contacts Page must be seen");
		test.info("Contacts page is seen");

		contactPage = new ContactsPage(driver);
		Assert.assertTrue(contactPage.clickARecentContact(), "Selected Contact details must be seen");
		test.info("Selected Contact details is seen");

	}

	@Test
	public void TC30_TestErrorCreateContactsView() {

		logger.info("TC30_TestErrorCreateContactsView");

		homePage = new HomePage(driver);
		Assert.assertTrue(homePage.clickContacts(), "Contacts Page must be seen");
		test.info("Contacts page is seen");

		contactPage = new ContactsPage(driver);
		Assert.assertTrue(contactPage.clickNewContactViewButton(), "New Contact View page must be seen");
		test.info("New Contact View page is seen");

		contactNewViewPage = new ContactsViewPage(driver);

		String contactUniqueViewName = IOUtilities.readPropertiesFile("contactstestdata",
				"prod.contacts.new.contactViewUniqueName");
		Assert.assertTrue(contactNewViewPage.enterContactUniqueViewName(contactUniqueViewName),
				"New Contact View Unique Name must be entered");
		test.info("New Contact View Unique Name entered");

		Assert.assertTrue(contactNewViewPage.clickSaveContactViewButton(), "Contact View Name should be entered");
		test.info("Contact View Name is not entered");

		Assert.assertTrue(contactNewViewPage.isContactViewNameErrorMessageSeen(), "Error Message should be seen");
		test.info("Error Message is seen");
	}

	@Test
	public void TC31_TestCancelCreateContactsView() {

		logger.info("TC31_TestCancelCreateContactsView");

		homePage = new HomePage(driver);
		Assert.assertTrue(homePage.clickContacts(), "Contacts Page must be seen");
		test.info("Contacts page is seen");

		contactPage = new ContactsPage(driver);
		Assert.assertTrue(contactPage.clickNewContactViewButton(), "New Contact View page must be seen");
		test.info("New Contact View page is seen");

		contactNewViewPage = new ContactsViewPage(driver);
		String contactViewName = IOUtilities.readPropertiesFile("contactstestdata",
				"prod.contacts.new.contactViewName");
		String contactUniqueViewName = IOUtilities.readPropertiesFile("contactstestdata",
				"prod.contacts.new.contactViewUniqueName");
		Assert.assertTrue(contactNewViewPage.editContactViewPage(contactViewName, contactUniqueViewName),
				"New Contact View details must be entered");
		test.info("New Contact View details entered");

		Assert.assertTrue(contactNewViewPage.cancelCreateContactView(),
				"Creating View for Contacts should be cancelled, Contacts home page should be seen");
		test.info("Contacts home page seen");

		Assert.assertTrue(contactPage.verifyContactPage(), "Contacts Page should be seen");
		test.info("Contacts Page is seen");

	}

	@Test
	public void TC32_TestSaveAndNewInContacts() {

		logger.info("TC32_TestSaveAndNewInContacts");

		homePage = new HomePage(driver);
		Assert.assertTrue(homePage.clickContacts(), "Contacts Page must be seen");
		test.info("Contacts page is seen");

		contactPage = new ContactsPage(driver);
		Assert.assertTrue(contactPage.clickNewContactButton(), "New contact page must be seen");
		test.info("New Contact page is seen");

		contactNewPage = new ContactNewPage(driver);
		String lastname = IOUtilities.readPropertiesFile("contactstestdata", "prod.contacts.new.lastname");
		String accountName = IOUtilities.readPropertiesFile("contactstestdata", "prod.contacts.new.accountName");
		Assert.assertTrue(contactNewPage.editContactPage(lastname, accountName), "New Contact details must be entered");
		test.info("New Contact details entered");

		Assert.assertTrue(contactNewPage.clickSaveAndCreateNewContact(),
				"Contact saved and New Contact page should be seen");
		test.info("Contact saved and New Contact page should be seen");

		Assert.assertTrue(contactNewPage.verifyNewContactPage(), "New Contact Page must be seen");
		test.info("New Contact Page must be seen");
	}

}
