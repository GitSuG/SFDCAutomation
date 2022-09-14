package com.sfdc.pkg;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sfdc.utilities.Utilities;
import com.sfdc.utilities.UtilityLogin;

public class UserNavMenu extends UtilityLogin  {

	static String filepath = "/Users/ghorpade/Desktop/Backgrounds/red.png";
	static String lastname = "Salesforce";
	static String postMessage = "My post" + System.currentTimeMillis();
	static String emailName = "Sujata";
	static String emailAddress = "sujata.a.ghorpade@gmail.com";
	static String photoPath = "/Users/ghorpade/Desktop/Files/Salesforce/Logos/pngtree-wolf-logo-png-image_2306634.jpeg";

	@BeforeMethod
	public static void T05_UserMenuDisplay() {

//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		UtilityLogin.T00_LoginToSFDC();
		System.out.println("User Menu: After Login.java executed");
		Assert.assertTrue(Utilities.verifyUserNavOptions(driver), "Error: User Navigation Menu");

	}

	@Test(priority = 0)
	public static void T06_MyProfileUserMenu() throws InterruptedException {

		String lastName = "SalesForce";
		String expectedOption = "My Profile";

		Assert.assertTrue(Utilities.findOptionInUserNavMenu(driver, expectedOption),
				"Error: 'My Profile' option not as expected");
		Assert.assertTrue(Utilities.selectProfileEditFrame(driver, "About"),
				"Error About Section not as expected");
		Assert.assertTrue(Utilities.editSaveProfileAboutSection(driver, lastName),
				"Error Edit/Save lastname field");
		// ------------------------------------------------------------
		// Verification of LastName Change Activity
		// ------------------------------------------------------------

		String actualName = driver.findElement(By.id("userNavLabel")).getText();
		String[] actual = actualName.split(" ");
		Assert.assertEquals(actual[1], lastName, "Error: User Name not as expected");

		Assert.assertTrue(Utilities.shareAPost(driver, postMessage), "Error: Sharing post");
		// ------------------------------------------------------------
		// Verification of Post Activity
		// ------------------------------------------------------------
		Thread.sleep(5000);
		String actualPost = driver.findElement(By.xpath("//span[@class='feeditemtext cxfeeditemtext']/p"))
				.getText();
		Assert.assertEquals(actualPost, postMessage, "Error: Posted message not as expected");

		Assert.assertTrue(Utilities.uploadAFile(driver, filepath), "Error: File upload not complete");
		Assert.assertTrue(Utilities.uploadProfilePicture(driver, photoPath), "Error: Photo Upload not complete");

	}

	public void T07_MySettingsUserMenu(String emailName, String emailAddress) throws InterruptedException {

		String expectedOption = "My Settings";

		Assert.assertTrue(Utilities.findOptionInUserNavMenu(driver, expectedOption),
				"Error: 'My Settings' option not as expected");

		/*
		 * Step 3: Click on personal link Select Login History link. Click on Download
		 * login history link.
		 */

		driver.findElement(By.xpath("//div[@id='PersonalInfo']/a")).click();
		driver.findElement(By.xpath("//div[@class='setupLeaf']/a[@id='LoginHistory_font']")).click();
		driver.findElement(By.xpath("//div[@class='pShowMore']/a")).click();

		/*
		 * Step 4: Click on Display & Layout link and select Customize My Tabs link.
		 * Select "Salesforce Chatter" option from custom App: drop down. Select Reports
		 * tab from Available Tabs list. Click on >(Add) button.
		 */

		driver.findElement(By.xpath("//div[@id='DisplayAndLayout']/a")).click();
		driver.findElement(By.xpath("//div[@class='setupLeaf']/a[@id='CustomizeTabs_font']")).click();
		driver.findElement(By.xpath("//select[@id='duel_select_0']/option[@value='report']")).click();
		driver.findElement(By.xpath("//div[@class='zen-mbs text']/a[@id='duel_select_0_right']")).click();

		// ------------------------------------------------------------
		// Verification of Reports Added to Selected Tab
		// ------------------------------------------------------------
		List<WebElement> listOptions = driver.findElements(By.xpath("//select[@id='duel_select_1']/option"));
		boolean reportsSelected = false;

		for (WebElement w : listOptions) {
			String selected = w.getText();
			if (selected.equals("Reports")) {
				System.out.println("Reports is added to Selected Tabs");
				reportsSelected = true;
			}
		}

		if (!reportsSelected)
			System.err.println("Reports not selected");

		/*
		 * Step 5: Click on Email link Click on my email settings link Provide
		 * <EmailName> in Email Name field, <EmailAddress> in Email Address field,
		 * Select automatic BCC radio button and click on save button
		 */

		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id='EmailSetup']/a[@class='header setupFolder']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='setupHighlightLeaf']/a[@id='EmailSettings_font']")).click();
		driver.findElement(By.cssSelector("#sender_name")).sendKeys(emailName);
		driver.findElement(By.cssSelector("#sender_email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("#auto_bcc1")).click();
		driver.findElement(By.xpath("//input[@name='save']"));

	}

//	@Test(priority = 1)
	public void T08_DeveloperConsoleUserMenu() {

		String expectedOption = "Developer Console (New Window)";

		Assert.assertTrue(Utilities.findOptionInUserNavMenu(driver, expectedOption),
				"Error: 'Developer Console' option not as expected");

	}

//	@Test(priority = 2)
	public void T09_LogoutUserMenu() {

		String expectedOption = "Logout";

		Assert.assertTrue(Utilities.findOptionInUserNavMenu(driver, expectedOption),
				"Error: 'Logout' option not as expected");

	}

}

