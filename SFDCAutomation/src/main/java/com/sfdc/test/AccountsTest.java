package com.sfdc.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.sfdc.listeners.SFDCListeners;
import com.sfdc.pages.AccountsPage;
import com.sfdc.pages.HomePage;
import com.sfdc.pages.LoginPage;
import com.sfdc.utilities.IOUtilities;

/*
 * IMPORTANT: View name can give error - edit before running case TC12
 */
/**
 * @author ghorpade
 *
 */
@Listeners(SFDCListeners.class)
public class AccountsTest extends BaseTest {

	private static WebDriver driver;
	private static LoginPage lp;
	private HomePage hp;
	private AccountsPage ap;
	Logger logger = LogManager.getLogger(AccountsTest.class.getName());

	private boolean launchAndLoginSFDC() {
		driver = getBrowser("chrome", false);
		boolean isSuccess = false;
		lp = new LoginPage(driver);
		
		lp.launchApp(driver);
		isSuccess = lp.loginToSFDC();
		return isSuccess;
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.close();
		
	}


	@Test
	public void TC10_CreateAccount() {

		logger.info("TC10_CreateAccount");
		
		AccountsTest at = new AccountsTest();
		
		Assert.assertTrue(at.launchAndLoginSFDC(), "SFDC Home Page should be seen");
		test.info("Login Successful: Home Page is seen");

		// Verify user name is needed?

		hp = new HomePage(driver);
		Assert.assertTrue(hp.clickAccounts(), "Accounts Home Page should be seen");
		test.info("Accounts Home Page is seen");

		ap = new AccountsPage(driver);
		String accountName = IOUtilities.readPropertiesFile("accountstestdata", "prod.accounts.new.accountName");
		Assert.assertTrue(ap.createNewAccount(accountName), "New account must be created");
		test.info("New Account created");

		// Verify the newly created account
		String expectedAccountName = accountName;
		Assert.assertTrue(ap.verifyAccount(expectedAccountName), "Account name must be present in list");
		test.info("Account is present in list");

	}

	@Test
	public void TC11_CreateNewAccountView() {


		logger.info("TC11_CreateNewAccountView");
		
		AccountsTest at = new AccountsTest();
		Assert.assertTrue(at.launchAndLoginSFDC(), "SFDC Home Page should be seen");
		test.info("Login Successful: Home Page is seen");

		// Verify user name is needed?

		hp = new HomePage(driver);
		Assert.assertTrue(hp.clickAccounts(), "Accounts Home Page should be seen");
		test.info("Accounts Home Page is seen");

		
		AccountsPage ap = new AccountsPage(driver);
		String accountViewName = IOUtilities.readPropertiesFile("accountstestdata",
				"prod.accounts.new.accountViewName");
		String accountViewUniqueName = IOUtilities.readPropertiesFile("accountstestdata",
				"prod.accounts.new.accountViewUniqueName");
		Assert.assertTrue(ap.createNewView(accountViewName, accountViewUniqueName), "New account must be created");
		test.info("New Account created");

		// Verify the newly created account view
		String expectedViewName = IOUtilities.readPropertiesFile("accountstestdata",
				"prod.accounts.new.accountViewName");
		Assert.assertTrue(ap.verifyAccountView(expectedViewName), "Account view name should be present");
		test.info("Account View Name present in list");

	}

	
	
	/**
	 *  Check the data file for the account name before 
	 *  running the test.
	 */
	@Test
	public void TC12_EditAccountView() {

		logger.info("TC12_EditAccountView");
		
		AccountsTest at = new AccountsTest();
		Assert.assertTrue(at.launchAndLoginSFDC(), "SFDC Home Page should be seen");
		test.info("Login Successful: Home Page is seen");

		// Verify user name is needed?

		
		//
		hp = new HomePage(driver);
		Assert.assertTrue(hp.clickAccounts(), "Accounts Home Page should be seen");
		test.info("Accounts Home Page is seen");

		// Select Account to edit
		ap = new AccountsPage(driver);
		String accountViewName = IOUtilities.readPropertiesFile("accountstestdata",
				"prod.accounts.new.accountViewName");
		Assert.assertTrue(ap.selectAccountViewOption(accountViewName), "Selected Account must be visible");
		test.info("Selected Account is seen");

		String editAccountViewName = IOUtilities.readPropertiesFile("accountstestdata",
				"prod.accounts.edit.accountViewName");
		String editAccountViewUniqueName = IOUtilities.readPropertiesFile("accountstestdata",
				"prod.accounts.edit.accountViewUniqueName");
		String expectedFilterField = IOUtilities.readPropertiesFile("accountstestdata",
				"prod.accounts.edit.filterField");
		String expectedOperator = IOUtilities.readPropertiesFile("accountstestdata",
				"prod.accounts.edit.filterOperator");
		String filterValue = IOUtilities.readPropertiesFile("accountstestdata", "prod.accounts.edit.filterValue");
		
		Assert.assertTrue(ap.editAccountView(editAccountViewName, editAccountViewUniqueName, expectedFilterField,
				expectedOperator, filterValue), "Account view name should be edited");
		test.info("Account view name is edited");

		// Verify the newly created account view
		String expectedViewName = IOUtilities.readPropertiesFile("accountstestdata",
				"prod.accounts.edit.accountViewName");
		Assert.assertTrue(ap.verifyAccountView(expectedViewName), "Account view name edited should be present");
		test.info("Account View Name edited present in list");

	}

	@Test
	public void TC13_MergeAccounts() {

		logger.info("TC13_MergeAccounts");
		
		AccountsTest at = new AccountsTest();
		Assert.assertTrue(at.launchAndLoginSFDC(), "SFDC Home Page should be seen");
		test.info("Login Successful: Home Page is seen");

		// Verify user name is needed?

		hp = new HomePage(driver);
		Assert.assertTrue(hp.clickAccounts(), "Accounts Home Page should be seen");
		test.info("Accounts Home Page is seen");

		ap = new AccountsPage(driver);
		String accountSearchText = IOUtilities.readPropertiesFile("accountstestdata",
				"prod.accounts.edit.accountSearchText");
		String noOfAccounts = IOUtilities.readPropertiesFile("accountstestdata", "prod.accounts.edit.noOfAccounts");
		int accountCount = Integer.parseInt(noOfAccounts);
		
		System.out.println("#Accounts="+accountCount);
		
		Assert.assertTrue(ap.selectAccountsToMerge(accountSearchText, accountCount),
				"Accounts to be merged should be seen");
		test.info("Accounts to be merged seen");

		Assert.assertTrue(ap.mergeSelectedAccounts(), "Selected Accounts should be merged");
		test.info("Selected Accounts are merged");
		
		
	}

	@Test
	public void TC14_CreateAccountActivityReport() {

		logger.info("TC14_CreateAccountActivityReport");
		
		AccountsTest at = new AccountsTest();
		Assert.assertTrue(at.launchAndLoginSFDC(), "SFDC Home Page should be seen");
		test.info("Login Successful: Home Page is seen");

		// Verify user name is needed?

		hp = new HomePage(driver);
		Assert.assertTrue(hp.clickAccounts(), "Accounts Home Page should be seen");
		test.info("Accounts Home Page is seen");

		ap = new AccountsPage(driver);
		Assert.assertTrue(ap.createAccountActivityReport(), "Accounts Unsaved Reports Page should be seen");
		test.info("Accounts Unsaved Reports Page is seen");

		String startDateValue = IOUtilities.readPropertiesFile("accountstestdata", "prod.accounts.edit.startDateValue");
		String endDateValue = IOUtilities.readPropertiesFile("accountstestdata", "prod.accounts.edit.endDateValue");
		Assert.assertTrue(ap.selectCreatedDateActivityReport(startDateValue, endDateValue),
				"Unsaved report with records for selected range should beseen");
		test.info("Unsaved report with records for selected range seen");

//		Saving Report is yet to be completed
	}

}
