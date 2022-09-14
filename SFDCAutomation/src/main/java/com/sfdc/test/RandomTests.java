package com.sfdc.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sfdc.listeners.SFDCListeners;
import com.sfdc.pages.CustomizeMyTabsPage;
import com.sfdc.pages.EventCalendarPage;
import com.sfdc.pages.HomePage;
import com.sfdc.pages.LoginPage;
import com.sfdc.pages.MyProfilePage;
import com.sfdc.pages.NewEventPage;
import com.sfdc.utilities.IOUtilities;

@Listeners(SFDCListeners.class)
public class RandomTests extends BaseTest {

	/*
	 * IMPORTANT: Username field is compared TC33 - edit before running
	 * IMPORTANT: Add 'Reports' tab from selected tabs - before TC35
	 * IMPORTANT: Remove the previous events from the time-slots - TC36 , TC37
	 */
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	CustomizeMyTabsPage customTabs;
	private MyProfilePage myProfile;
	EventCalendarPage calendarPage;
	NewEventPage eventPage;
	Logger logger = LogManager.getLogger(RandomTests.class.getName());

	@BeforeMethod
	public void setup() {
		driver = getBrowser("chrome", false);
		logger.info("Browser is closed");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
		
	}

	@Parameters({"username"})
	@Test
	public void TC33_TestFirstLastNameLink(@Optional("Admin Salesforce") String username) {

//		String username = "Admin Force123";
		
		logger.info("TC33_TestFirstLastNameLink");
		
		loginPage = new LoginPage(driver);
		loginPage.launchApp(driver);
		loginPage.loginToSFDC();

		homePage = new HomePage(driver);
		Assert.assertTrue(homePage.selectNavigationTab("Home"), "HomePage should be seen");
		test.info("Home Page is seen");

		Assert.assertEquals(homePage.getPageName(), username,
				"Page name should be same as Account Holder name");
		test.info("Pagename and Account Holder name is same");

		Assert.assertTrue(homePage.isFirstLastNameLinkSeen(), "User Profile page should be seen");
		test.info("User profile page is seen");

	}

	/**
	 * Last name of the account is changed to ABCD Check the lastname field
	 */
	@Test
	public void TC34_VerifyLastNameUpdates() {

		logger.info("TC34_VerifyLastNameUpdates");
		
		loginPage = new LoginPage(driver);
		loginPage.launchApp(driver);
		loginPage.loginToSFDC();

		homePage = new HomePage(driver);
		Assert.assertTrue(homePage.clickHome(), "Home Page should be seen");
		test.info("Home Page is seen");

		Assert.assertTrue(homePage.isFirstLastNameLinkSeen(), "User Profile page should be seen");
		test.info("User profile page is seen");

//		Assert.assertTrue(homePage.selectMyProfile(), "My Profile selected, page should be seen");
//		test.info("My Profile page is seen");

		myProfile = new MyProfilePage(driver);

		Assert.assertTrue(myProfile.clickProfileEditLink(), "Pop-up window with About and Contact tabs is seen");
		test.info("Profile edit link is selected");

		String newLastName = "ABCD";
		Assert.assertTrue(myProfile.editProfilelastName(newLastName),
				"Last name should be changed to new selected value");
		test.info("User last name changed in My Profile section");

		String updatedName = "Admin ABCD";
		String actualUserPageName = homePage.getUserProfilePageName().trim();
		Assert.assertEquals(actualUserPageName, updatedName,
				"Edited Name should reflect in Home Page firstLast name link");
		test.info("Edited name seen in FirstLast name Link");

		Assert.assertEquals(homePage.getUserProfileName(), updatedName,
				"Edited Name should reflect in Home Page UserMenu");
		test.info("Edited name seen in UserMenu");

		boolean lastnameVerified = false;
		if (homePage.getUserProfilePageName().contains(newLastName))
			lastnameVerified = true;
		else
			lastnameVerified = false;
		Assert.assertTrue(lastnameVerified, "Lastname should be updated in the FirstLast name link");
		test.info("Lastname is updated in the Homae page title");
	}

	/**
	 * Add Reports<Any other tab> to the home Page Tab before running this code Change 'option'
	 * value to a existing tab to be removed
	 */
	@Parameters({"tabName"})
	@Test
	public void TC35_VerifyTabCustomizations(String option) {

		logger.info("TC35_VerifyTabCustomizations");
		
		loginPage = new LoginPage(driver);
		loginPage.launchApp(driver);
		loginPage.loginToSFDC();

		homePage = new HomePage(driver);
		Assert.assertTrue(homePage.clickAllTabs(), "All Tabs page must be seen");
		test.info("All Tabs page is seen");

		Assert.assertTrue(homePage.clickCustomizeTabs(), "'Customize my Tabs' page should be seen");
		test.info("'Customize My Tabs' is seen");

//		String option = "Chatter";
		customTabs = new CustomizeMyTabsPage(driver);
		Assert.assertTrue(customTabs.removeTabFromSelectedList(option),
				option + " should be removed from Selected Tabs List");
		test.info(option + " removed from Selected Tabs List");

		Assert.assertTrue(customTabs.verifyTabInAvailableList(option),
				option + " should be seen in Available Tabs List");
		test.info(option + "seen in Available Tabs List");

		Assert.assertTrue(customTabs.clickCustomSaveButton(), "Changes should be saved in Customize My Tabs");
		test.info("Changes saved in Customize My Tabs");

		Assert.assertFalse(homePage.selectNavigationTab(option), option + " tab should not be present among the Tabs");
		test.info(option + " tab is removed from the Tabs");

		Assert.assertTrue(homePage.logout(), "Login Page must be seen");
		test.info("Login Page is seen");

		Assert.assertTrue(loginPage.loginToSFDC(), "SFDC Home Page should be seen");
		test.info("SFDC Home Page is seen");

		Assert.assertFalse(homePage.selectNavigationTab(option), option + " tab should not be present among the Tabs");
		test.info(option + " tab is removed from the Tabs");

	}

	@Test
	public void TC36_TestCalendarEventBlock() {

		logger.info("TC36_TestCalendarEventBlock");
		
		loginPage = new LoginPage(driver);
		loginPage.launchApp(driver);
		loginPage.loginToSFDC();

		homePage = new HomePage(driver);
		Assert.assertTrue(homePage.selectNavigationTab("Home"), "HomePage should be seen");
		test.info("Home Page is seen");

		Assert.assertTrue(homePage.clickEventCalendar(), "Event Calendar must be seen");
		test.info("Event Calendar is seen");

		String eventTime = "8:00 PM";
		calendarPage = new EventCalendarPage(driver);
		Assert.assertTrue(calendarPage.selectEventHour(eventTime),
				"New Event page with time-slot " + eventTime + " should be seen");
		test.info("New Event page for " + eventTime + " seen");

		eventPage = new NewEventPage(driver);
		Assert.assertTrue(eventPage.clickComboBox(), "New Event: ComboBox Subjects must be seen");
		test.info("New Event: ComboBox Subjects seen");

		String option = "Other";
		Assert.assertTrue(eventPage.selectcomboBoxSubject(option), option + " must be seen in Subject field");
		test.info("'" + option + "' seen in Subject field");

		System.out.println("TC36:: Selecting end-time...");

		String endHour = "9:00 PM";
		Assert.assertTrue(eventPage.selectEventEndHour(endHour), endHour + "must be entered");
		test.info("End time " + endHour + " is entered");

		Assert.assertTrue(eventPage.saveNewEvent(), "Event must be saved");
		test.info("New Event is saved");

		Assert.assertEquals(calendarPage.getEventDetails(), option, option + " must be entered in Subject field");
		test.info(option + " entered in Subject field");

	}

	@Test
	public void TC37_TestCalendarWeeklyRecurrance() {

		logger.info("TC37_TestCalendarWeeklyRecurrance");
		
		loginPage = new LoginPage(driver);
		loginPage.launchApp(driver);
		loginPage.loginToSFDC();

		homePage = new HomePage(driver);
		Assert.assertTrue(homePage.selectNavigationTab("Home"), "HomePage should be seen");
		test.info("Home Page is seen");

		Assert.assertTrue(homePage.clickEventCalendar(), "Event Calendar must be seen");
		test.info("Event Calendar is seen");

		String eventTime = "4:00 PM";
		calendarPage = new EventCalendarPage(driver);
		Assert.assertTrue(calendarPage.selectEventHour(eventTime),
				"New Event page with time-slot " + eventTime + " should be seen");
		test.info("New Event page for " + eventTime + " seen");

		eventPage = new NewEventPage(driver);
		Assert.assertTrue(eventPage.clickComboBox(), "New Event: ComboBox Subjects must be seen");
		test.info("New Event: ComboBox Subjects seen");

		String option = "Other";
		Assert.assertTrue(eventPage.selectcomboBoxSubject(option), option + " must be seen in Subject field");
		test.info("'" + option + "' seen in Subject field");

		String endHour = "7:00 PM";
		Assert.assertTrue(eventPage.selectEventEndHour(endHour), endHour + "must be entered");
		test.info("End time " + endHour + " is entered");

		Assert.assertTrue(eventPage.clickRecurranceCheckbox(), "Frequency, Start and End Dates must be seen");
		test.info("Frequency, Start and End Dates is seen");
		
		Assert.assertTrue(eventPage.clickWeeklyFrequency(), "Weekly frequency radio button must be selected");
		test.info("Weekly frequency radio button is selected");
		
		String DATE_FORMAT = "MM/dd/yyyy";
		String currentDate = new SimpleDateFormat(DATE_FORMAT).format(new Date());
		String endDate = IOUtilities.addDaysToDate(currentDate, "14", DATE_FORMAT);
		Assert.assertTrue(eventPage.enterRecurranceEndDate(endDate), "End Date should be enterd");
		test.info("End Date entered");
		
		Assert.assertTrue(eventPage.saveNewEvent(), "Event must be saved");
		test.info("New Event is saved");

		//Verification Pending
//		Assert.assertTrue(calendarPage.clickMonthView(), "Month View must be seen");
//		test.info("Month View is seen");
		
		

	}

}
