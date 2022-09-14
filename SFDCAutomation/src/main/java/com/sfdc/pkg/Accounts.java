package com.sfdc.pkg;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sfdc.utilities.Utilities;
import com.sfdc.utilities.UtilityLogin;

public class Accounts extends UtilityLogin {

	String whichName = "";

	int min = 0;
    int max = 1000;
      
    //Generate random integer value from 50 to 100 
    int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
    

	
	@BeforeClass
	public void T00_launchNLogin() {

	}

	@BeforeMethod
	public void browseAccounts() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		UtilityLogin.T00_LoginToSFDC();

		System.out.println("Account Tab.... Browsing Started");
		String blockName = "accountBlock";
		String expectedPage = "Accounts";
		
		/*
		 *  	 Browse Home Tab: Accounts Tab
		 */
		Assert.assertTrue(Utilities.getTabHome(driver, blockName, expectedPage),
				"Error: Page mismatch: Expected: [Accounts]");

	}

//	@Parameters({"Splash N Play"}) 
	@Test(enabled = true)
	public void T10_CreateAccount() throws InterruptedException {

		
		String newAccountEntry = "Lottery Fund "+ random_int;
		String expectedName = "New Account";
		
		System.out.println("Account Entry: "+newAccountEntry);
		/*
		 *  	 Click New Button: New Account
		 */
		Assert.assertTrue(Utilities.clickNewButton(driver, expectedName),
				"Error New Account: New Button Failed ");
		/*
		 * 		 Enter <New Account name>
		 */
		WebElement accountID = driver.findElement(By.id("acc2"));
		accountID.sendKeys(newAccountEntry);
		
		/*
		 *  	 Click Save Button: Save Account
		 */
		Assert.assertTrue(Utilities.clickSaveButton(driver, expectedName),
				"Error New Account: [" + newAccountEntry +" ]"+" Save Button Failed ");
		
		
	}

	@Test(enabled = false)
	public void T11_CreateAccountView() throws InterruptedException {

		String viewName = "Pool Play 2023";

		// ------------------------------------------------------------
		// Verification of Accounts Page Activity: Account
		// ------------------------------------------------------------

		if (whichName.equals("Accounts")) {
			System.out.println("Browsing...  " + whichName + " Page");
			Assert.assertEquals(whichName, "Accounts", "Error: Page mismatch: [Accounts]");
			/*
			 * Step 3: Click on create new view link
			 */
//			
			driver.findElement(By.xpath("//span[@class='fFooter']/a[2]")).click();
		} else
			System.err.println("Page Expected: " + whichName);

		// ------------------------------------------------------------
		// Verification of Accounts: Create View Activity
		// ------------------------------------------------------------

		whichName = driver.findElement(By.className("pageDescription")).getText();

		if (whichName.equals("Create New View")) {
			System.out.println("Browsing...  " + whichName);
			Assert.assertEquals(whichName, "Create New View", "Error: Page mismatch: [Create New View]");
			/*
			 * Step 3: Provide <View name>, <View unique name>
			 */

			driver.findElement(By.cssSelector("#fname")).sendKeys(viewName);
			driver.findElement(By.id("devname")).click();
		} else
			System.err.println("Page Expected: " + whichName);

		System.out.println("Saving View...");
		/*
		 * Step 3: Click on save button
		 */
		String xpathString = "//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[1]";
		WebElement saveButton = Utilities.fluentWait(driver, xpathString, 30, 5);
		saveButton.click();

		System.out.println("View... SAVED");
		// ------------------------------------------------------------
		// Verification of Accounts Page Activity: New Account Created
		// ------------------------------------------------------------

		List<WebElement> listOptions = driver.findElements(By.xpath("//select[@name='fcf']/option"));
		boolean viewAddVerified = Utilities.isWebElementPresent(listOptions, viewName,
				"Error: Account Page: [Create New View]");

		if (!viewAddVerified)
			System.err.println("View creation error");
	}

	@Test(enabled = false)
	public void T12_EditAccountView() throws InterruptedException {

		String oldViewSelected = "Kids June 2022";
		String newViewName = "Kids June 2023";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// ------------------------------------------------------------
		// Verification of Accounts Page Activity: Account
		// ------------------------------------------------------------

		if (whichName.equals("Accounts")) {
			System.out.println("Browsing...  " + whichName + " Page");
			Assert.assertEquals(whichName, "Accounts", "Error: Page mismatch: [Accounts]");
			/*
			 * Step 3: Select the <view name> from the view drop down list on the account
			 * page.
			 */

			boolean viewAddVerified = false;

			List<WebElement> listOptions = driver.findElements(By.xpath("//select[@id='fcf']/option"));
			int index = Utilities.findWebElementIndex(listOptions, oldViewSelected,
					"Error: View mismatch: [Accounts View]");

			if (index != -1)
				viewAddVerified = true;

			if (!viewAddVerified)
				System.err.println("View creation error");
			else {

				String xpathString = "//select[@id='fcf']/option[" + index + "]";
				WebElement selectedView = Utilities.fluentWait(driver, xpathString, 30, 5);
				selectedView.click();

				WebElement goElement = driver.findElement(By.xpath("//input[@name='go']"));

				wait.until(ExpectedConditions.visibilityOf(goElement));
				goElement.click();
				/*
				 * Step 3: Click on the Edit view link
				 */

				driver.findElement(By.xpath("//div[@class='filterLinks']/a[1]")).click();
				driver.findElement(By.cssSelector("#fname")).sendKeys(newViewName);
				driver.findElement(By.id("devname")).click();

			}
		} else
			System.err.println("Page Expected: " + whichName);

		// ------------------------------------------------------------
		// Verification of Accounts: Edit View Activity
		// ------------------------------------------------------------

		System.out.println("Saving View...");
		/*
		 * Step 3: Click on save button
		 */
		WebElement saveElement = driver
				.findElement(By.xpath("//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[1]"));
		wait.until(ExpectedConditions.visibilityOf(saveElement));
		saveElement.click();

		System.out.println("View... SAVED");
		// ------------------------------------------------------------
		// Verification of Accounts Page Activity: Account View Edited
		// ------------------------------------------------------------

		List<WebElement> listOptions = driver.findElements(By.xpath("//select[@name='fcf']/option"));
		boolean viewEditVerified = Utilities.replaceWebElement(listOptions, oldViewSelected, newViewName,
				"Error: View mismatch: [Accounts View]");

		if (!viewEditVerified)
			System.err.println("View edit error");
	}

	@Test(enabled = false)
	public void T13_MergeAccounts() throws InterruptedException {

		// ------------------------------------------------------------
		// Verification of Accounts Page Activity: Account
		// ------------------------------------------------------------

		if (whichName.equals("Accounts")) {
			Assert.assertEquals(whichName, "Accounts", "Error: Page mismatch: [Accounts]");
			System.out.println("Browsing... " + whichName + " Page ");
			/*
			 * Step 3: Click on the New button to create new account
			 */

			driver.findElement(By.xpath("//td[@class='pbButton']/input[@value=' New ']")).click();
		} else
			System.err.println("Page Expected: Accounts");

		whichName = driver.findElement(By.xpath("//h2[@class='pageDescription']")).getText();

	}

	@Test(enabled = false)
	public void T14_CreateAccountReport() throws InterruptedException {

		// ------------------------------------------------------------
		// Verification of Accounts Page Activity: Account
		// ------------------------------------------------------------

		if (whichName.equals("Accounts")) {
			Assert.assertEquals(whichName, "Accounts", "Error: Page mismatch: [Accounts]");
			System.out.println("Browsing... " + whichName + " Page ");
			/*
			 * Step 3: Click on the New button to create new account
			 */

			driver.findElement(By.xpath("//td[@class='pbButton']/input[@value=' New ']")).click();
		} else
			System.err.println("Page Expected: Accounts");

		whichName = driver.findElement(By.xpath("//h2[@class='pageDescription']")).getText();

	}

	@AfterMethod
	public void Logout() {
		System.out.println("Accounts: After Method: Driver Close");
		driver.close();
	}

	@AfterClass
	public void T09_Logout() {
//		super.T09_LogoutSFDC();
		driver.quit();
	}

}
