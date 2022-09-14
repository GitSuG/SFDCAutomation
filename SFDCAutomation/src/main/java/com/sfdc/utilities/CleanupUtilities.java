package com.sfdc.utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.sfdc.listeners.SFDCListeners;
import com.sfdc.pages.HomePage;
import com.sfdc.pages.LoginPage;
import com.sfdc.pages.MyProfilePage;
import com.sfdc.pages.MySettingsPage;
import com.sfdc.test.BaseTest;

import test.pkg.IOUtilities;

@Listeners(SFDCListeners.class)
public class CleanupUtilities extends BaseTest {

	private static HomePage hp;
	private static LoginPage lp;
	private MyProfilePage mp;
	private MySettingsPage ms;

	public void driverSetup(ITestContext iTest) {

		this.context = setContext(iTest, driver);

	}

	@AfterMethod
	public void tearDown() {
		driver.close();

	}

	@Test
	public void restoreUserData() throws IOException {

		WebDriver driver = getBrowser("chrome", false);

		hp = new HomePage(driver);
		hp.launchApp(driver);

		lp = new LoginPage(driver);
		Assert.assertTrue(lp.loginToSFDC(), "Restore: SFDC Home page should be seen");
		test.info("Restore: SFDC Home page is seen");

		// First-Last Name Check and Restore
		String expectedProfileName = IOUtilities.readPropertiesFile("usertestdata", "prod.user.current.name");
		if (hp.getUserProfileName().equals(expectedProfileName)) {
			Assert.assertEquals(hp.getUserProfileName(), expectedProfileName);
			test.info("NO Restore: Profile name as expected");
		} else {
			Assert.assertNotEquals(hp.getUserProfileName(), expectedProfileName);
			test.info("Error: Profile name not as expected");

			Assert.assertTrue(hp.selectMyProfile(), "Restore: My Profile page should be seen");
			test.info("Restore: My Profile page is seen");

			mp = new MyProfilePage(driver);
			Assert.assertTrue(mp.clickProfileEditLink(), "Pop-up window with About and Contact tabs is seen");
			test.info("Profile edit link is selected");

			String restoreLastName = IOUtilities.readPropertiesFile("usertestdata", "prod.user.current.lastname");
			Assert.assertTrue(mp.editProfilelastName(restoreLastName),
					"Restore: Last name should be changed to previous selected value");
			test.info("Restore: Last name is changed to value :: "+ restoreLastName);

		}

		// Selected tab position Restore
		Assert.assertTrue(hp.selectMySettings(), "Restore: My Settings page should be seen");

		String chosenTab = IOUtilities.readPropertiesFile("usertestdata", "prod.add.tab");
		ms = new MySettingsPage(driver);
		Assert.assertTrue(ms.moveSelectedTab(chosenTab, "RightToLeft"),
//				Assert.assertTrue(ms.moveSelectedTab(chosenTab, "LeftToRight"),
				"Restore: My Settings: '" + chosenTab + "' Tab position must be restored");
		test.info("Restore: My Settings: '" + chosenTab + "' Tab position is restored to 'Available Tabs'");
	}

}
