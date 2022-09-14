package com.sfdc.test;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.sfdc.listeners.SFDCListeners;
import com.sfdc.pages.AccountsPage;
import com.sfdc.pages.ContactsPage;
import com.sfdc.pages.HomePage;
import com.sfdc.pages.LeadsPage;
import com.sfdc.pages.LoginPage;
import com.sfdc.pages.MyProfilePage;
import com.sfdc.pages.MySettingsPage;
import com.sfdc.pages.OpportunityPage;

import test.pkg.IOUtilities;

@Listeners(SFDCListeners.class)
public class UserMenuTest extends BaseTest {

	/*
	 * IMPORTANT: User lastname must be edited before TC06
	 */

	private static HomePage hp;
	private static LoginPage lp;
	private MyProfilePage mp;
	private MySettingsPage ms;
//	private AccountsPage ap;
//	private ContactsPage cp;
//	private LeadsPage leadsPAge;
//	private OpportunityPage op;
	private static Logger logger = LogManager.getLogger(LoginTest.class.getName());

	@BeforeMethod
	public void driverSetup(ITestContext iTest) {

		this.context = setContext(iTest, driver);

	}

	
	@AfterMethod
	public void tearDown() {
		driver.close();
		
	}

	@Test
	public void TC05_UserMenuDropDownFromUserMenu() throws IOException {

		logger.info("Executing: TC05_UserMenuDropDownFromUserMenu");
		WebDriver driver = getBrowser("chrome", false);

		hp = new HomePage(driver);
		hp.launchApp(driver);
		test.info("App launched");

		lp = new LoginPage(driver);
		Assert.assertTrue(lp.loginToSFDC(), "SFDC Home page should be seen");
		test.info("Successful login to SFDC");

		String expectedProfileName = IOUtilities.readPropertiesFile("usertestdata", "prod.user.current.name");
		Assert.assertEquals(hp.getUserProfileName(), expectedProfileName, "Error: Profile name not as expected");
		test.info("User Profile name verified");

		Assert.assertTrue(hp.isUserMenuSeen(), "User menu should be seen");
		test.info("User menu is seen");

		Assert.assertTrue(hp.verifyUserMenuOptionsAll(), "All User Menu options must be verified");
		test.info("All User Menu Options successfully verified");

	}

	@Test
	public void TC06_AccessMyProfileFromUserMenu() throws IOException, InterruptedException {

		logger.info("Executing: TC06_AccessMyProfileFromUserMenu");
		WebDriver driver = getBrowser("chrome", true);
		
		hp = new HomePage(driver);
		hp.launchApp(driver);
		test.info("App launched");
		
		lp = new LoginPage(driver);
		Assert.assertTrue(lp.loginToSFDC(), "SFDC Home page should be seen");
		test.info("Successful login to SFDC");

		hp = new HomePage(driver);

		Assert.assertTrue(hp.selectMyProfile(), "My Profile page should be seen");
		test.info("My Profile from User Menu selected");

		mp = new MyProfilePage(driver);
		Assert.assertTrue(mp.clickProfileEditLink(), "Pop-up window with About and Contact tabs is seen");
		test.info("Profile edit link is selected");

		String newLastName = IOUtilities.readPropertiesFile("usertestdata", "prod.user.new.lastname");
		Assert.assertTrue(mp.editProfilelastName(newLastName), "Last name should be changed to new selected value");
		test.info("User last name changed in My Profile section");

		String expectedUserName = IOUtilities.readPropertiesFile("usertestdata", "prod.user.current.firstname") + " "
				+ IOUtilities.readPropertiesFile("usertestdata", "prod.user.new.lastname");
		String actualUserName = hp.userProfileName.getText();
		Assert.assertEquals(actualUserName, expectedUserName, "User Name: Expected name should be seen");
		test.info("User Name changed: Last name field edited in My Profile");

		String newMessage = IOUtilities.readPropertiesFile("usertestdata", "prod.user.new.post")
				+ System.currentTimeMillis();

		boolean ismessagePosted = mp.makeAPost(newMessage);
		Assert.assertTrue(ismessagePosted, "New message posted");
		test.info("New message posted in My Profile");

		if (ismessagePosted)
			Assert.assertEquals(mp.verifyNewPost(), newMessage, "New message must be seen in post");
		test.info("New post in My Profile verified ");

//		String uploadFilepath = IOUtilities.readPropertiesFile("usertestdata", "prod.upload.filepath");
//		Assert.assertTrue(mp.uplaodAFile(uploadFilepath), "File should be uploaded");
//		test.info("New file uploaded");
//
//		String uploadPhotopath = IOUtilities.readPropertiesFile("usertestdata", "prod.upload.photopath");
//		Assert.assertTrue(mp.uploadProfilePicture(uploadPhotopath), "Photo should be uploaded");
//		test.info("Profile photo uploaded");

	}

	/*
	 * Make sure Reports tab is in the Available Tabs not Selected Tabs
	 */
	@Test
	public void TC07_AccessMySettingsFromUserMenu() throws IOException {

		logger.info("Executing: TC07_AccessMySettingsFromUserMenu");
		driver = getBrowser("chrome", false);

		hp = new HomePage(driver);
		hp.launchApp(driver);
		test.info("App launched");

		lp = new LoginPage(driver);
		Assert.assertTrue(lp.loginToSFDC(), "SFDC Home page should be seen");
		test.info("Successful login to SFDC");

		hp = new HomePage(driver);

		Assert.assertTrue(hp.selectMySettings(), "My Settings page should be seen");
		test.info("My Settings from User Menu selected");


		ms = new MySettingsPage(driver);
		Assert.assertTrue(ms.downloadLoginHistory(), "My Settings: Login history must be downloaded");
		test.info("My Settings: Login History downloaded");

		String chosenTab = IOUtilities.readPropertiesFile("usertestdata", "prod.add.tab");
		Assert.assertTrue(ms.customizeMyTabs(chosenTab), "My Settings: " + chosenTab + " must be added");
		test.info(chosenTab + " added using My Settings");

		Assert.assertTrue(hp.verifyNavigationTab(chosenTab), "Tabs must be available on the Selected side");
		test.info(chosenTab + " present in the selected section");

		String senderName = IOUtilities.readPropertiesFile("usertestdata", "prod.add.sender.name");
		String senderEmail = IOUtilities.readPropertiesFile("usertestdata", "prod.add.sender.email");
		Assert.assertTrue(ms.customizeEmailSettings(senderName, senderEmail),
				"Reports tab must be added to My Settings");
		test.info("My Settings: Sender name and email updated");

		Assert.assertTrue(ms.testActivityCalendarReminders(), "Pop-window must be displayed");
		test.info("My Settings: Calendar Reminder tested ");

	}

	@Test
	public void TC08_AccessDevConsoleFromUserMenu() throws IOException {

		UserMenuTest u = new UserMenuTest();

		logger.info("Executing: TC08_AccessDevConsoleFromUserMenu");
		WebDriver driver = getBrowser("chrome", false);

		hp = new HomePage(driver);
		hp.launchApp(driver);
		test.info("App launched");

		lp = new LoginPage(driver);
		Assert.assertTrue(lp.loginToSFDC(), "SFDC Home page should be seen");
		test.info("Successful login to SFDC");

		String expectedProfileName = IOUtilities.readPropertiesFile("usertestdata", "prod.user.current.name");
		Assert.assertEquals(hp.getUserProfileName(), expectedProfileName, "Error: Profile name not as expected");
		Assert.assertTrue(hp.isUserMenuSeen(), "User menu should be seen");
		Assert.assertTrue(hp.selectDevConsole(), "Developer console must be selected");
		test.info("Devloper console is seen");

		Assert.assertTrue(hp.closeDevConsole(), "Developer Console should be closed");
		test.info("Developer Console closed");
	}

	@Test
	public void TC09_SelectLogoutFromUserMenu() throws IOException {

		logger.info("Executing: TC09_SelectLogoutFromUserMenu");
		WebDriver driver = getBrowser("chrome", false);

		hp = new HomePage(driver);
		hp.launchApp(driver);
		test.info("App launched");

		lp = new LoginPage(driver);
		Assert.assertTrue(lp.loginToSFDC(), "SFDC Home page should be seen");
		test.info("Successful login to SFDC");

		String expectedProfileName = IOUtilities.readPropertiesFile("usertestdata", "prod.user.current.name");
		Assert.assertEquals(hp.getUserProfileName(), expectedProfileName, "Error: Profile name not as expected");
		test.info("User Profile name verified");

		Assert.assertTrue(hp.isUserMenuSeen(), "User menu should be seen");
		test.info("User menu is seen");

		Assert.assertTrue(hp.logout(), "Application must logout");
		test.info("Application successfully logged out");
	}
}
