package com.sfdc.pages;

import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sfdc.utilities.WaitUtilities;

public class AccountsPage extends BaseSFDCPage {

	/*
	 * dropDownSelect goButton newButton editView createNewView
	 */
	final String accounts = "Accounts";
	public static WebDriver driver;

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		newAccount = new AccountNewPage(driver);
		newAccountView = new AccountViewPage(driver);
		mergeAccount = new AccountMergePage(driver);
		accountReports = new AccountReportsPage(driver);
		g = new GeneralButtonsLinks(driver);
	}

	boolean mergeAccountsSelected = false;

	GeneralButtonsLinks g;
	AccountNewPage newAccount;
	AccountViewPage newAccountView;
	AccountMergePage mergeAccount;
	AccountReportsPage accountReports;

	@FindBy(xpath = "//div[@class='toolsContentLeft']/div/div/ul/li[2]/a")
	public WebElement accountActivityReportLink;

	@FindBy(xpath = "//div[@class='toolsContentRight']/div/div/ul/li[4]/span/a")
	public WebElement mergeAccountsToolLink;

	@FindBy(xpath = "//div[@class='bRelatedList']//descendant::div[@class='pbBody']//descendant::a")
	public List<WebElement> accountList;

	public boolean createNewAccount(String accountName) {

		boolean accountCreated = false;

		if (g.clickNewButton())
			if (newAccount.enterAccountName(accountName))
				if (g.clickSaveButton()) {
					logger.info("Create New Account: successful");
					accountCreated = true;
				} else {
					logger.info("Create New Account: not successful");
					accountCreated = false;
				}

		return accountCreated;

	}

	public boolean verifyAccount(String expectedAccountName) {

		boolean isViewPresent = false;

		String actualAccountName = newAccount.getAccountNameTitle();
		
		if (expectedAccountName.equals(actualAccountName)) {
			logger.info("Account Name: '" + actualAccountName + "': verified successufully");
			isViewPresent = true;
		} else {
			logger.info("Account Name: '" + actualAccountName + "': not present");
			isViewPresent = false;
		}

		return isViewPresent;

	}
	
	public boolean selectAccountViewOption(String expectedAccountViewName) {
		
		return g.selectViewOption(expectedAccountViewName);
	}
	
	
	
	public boolean selectAccountOption(String expectedAccountName) {

		boolean isAccountFound = false;

		logger.info("Expected : Account : " + expectedAccountName);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < accountList.size(); i++) {

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String currentOption = accountList.get(i).getText();

			logger.info("Next Compare: Account : " + currentOption);
			if (expectedAccountName.equals(currentOption)) {
				logger.info("Fetch Success: Account : " + currentOption);
				accountList.get(i).click();
				isAccountFound = true;
				break;
			}
		}

		return isAccountFound;
	}

	public boolean createNewView(String newViewName, String newViewUniqueName) {

		boolean isnewViewCreated = false;

		if (g.clickCreateNewViewLink())
			if (newAccountView.enterAccountViewName(newViewName))
				if (newAccountView.enterAccountViewUniqueName(newViewUniqueName))
					if (g.clickSaveViewButton()) {
						logger.info("Create New Account View: successful");
						isnewViewCreated = true;
					} else {
						logger.info("Create New Account View: not successful");
						isnewViewCreated = false;
					}

		return isnewViewCreated;

	}

	public boolean verifyAccountView(String expectedViewName) {

		boolean isViewPresent = false;

		if (g.selectViewOption(expectedViewName)) {
			logger.info("Account View: '" + expectedViewName + "': verified successufully");
			isViewPresent = true;
		} else {
			logger.info("Account View: '" + expectedViewName + "': not present");
			isViewPresent = false;
		}

		return isViewPresent;

	}

	public boolean editAccountView(String editAccountViewName, String editAccountViewUniqueName,
			String expectedFilterField, String expectedOperator, String value) {

		boolean isViewEdited = false;
		AccountsPage ap = new AccountsPage(driver);

		if (ap.editAccountViewName(editAccountViewName, editAccountViewUniqueName))
			if (newAccountView.addFilter(expectedFilterField, expectedOperator, value))
				if (g.clickSaveViewButton())
					isViewEdited = true;

		return isViewEdited;

	}

	public boolean editAccountViewName(String editAccountViewName, String editAccountViewUniqueName) {

		boolean isViewNameEdited = false;

		if (g.clickEditViewLink())
			if (newAccountView.enterAccountViewName(editAccountViewName))
				if (newAccountView.enterAccountViewUniqueName(editAccountViewUniqueName))
					isViewNameEdited = true;
		
		logger.info("Account View Name: Edit status: "+isViewNameEdited);
		return isViewNameEdited;
	}

	public boolean selectAccountsToMerge(String searchText, int count) {

		boolean accountsSelected = false;

		if (clickMergeAccountsLink())
			if (mergeAccount.clickSendSearchText(searchText))
				if (mergeAccount.clickFindAccountButton())
					if (mergeAccount.selectAccountsToMerge(count))
						if (mergeAccount.clickGoNextButton()) {
							accountsSelected = true;
							mergeAccountsSelected = true;
						}

		return accountsSelected;

	}

	public boolean mergeSelectedAccounts() {

		boolean mergeSuccess = false;

		if (mergeAccountsSelected)
			if (mergeAccount.clickMergeButton())
				mergeSuccess = true;

		if(mergeSuccess)
			return mergeAccount.closeMergeAlert();
		else
			return false;

	}

	private boolean isMergeAccountsLinkSeen() {

		WaitUtilities.waitUntil(driver, mergeAccountsToolLink, 30);
		if (mergeAccountsToolLink.isDisplayed()) {
			logger.info("Merge Accounts Tool Link : Visible");
			return true;
		} else {
			logger.info("Merge Accounts Tool Link : Not Visible");
			return false;
		}

	}

	private boolean clickMergeAccountsLink() {

		if (isMergeAccountsLinkSeen()) {
			WaitUtilities.waitUntil(driver, mergeAccountsToolLink, 30);
			mergeAccountsToolLink.click();
			logger.info("Merge Accounts Tool Link : Clicked");
			return true;
		} else {
			logger.info("Merge Accounts Tool Link : Not Clicked");
			return false;
		}

	}

	public boolean createAccountActivityReport() {

		if (clickAccountActivityReportLink())
			return true;
		else
			return false;
	}

	public boolean selectCreatedDateActivityReport(String startDateValue, String endDateValue) {

		if (accountReports.selectCreatedDateActivityReport(startDateValue, endDateValue))
			return true;
		else
			return false;
	}

	private boolean isAccountActivityReportLinkSeen() {

		WaitUtilities.waitUntil(driver, accountActivityReportLink, 30);
		if (accountActivityReportLink.isDisplayed()) {
			logger.info("Account Activity Report Link : Visible");
			return true;
		} else {
			logger.info("Account Activity Report Link : Not Visible");
			return false;
		}

	}

	private boolean clickAccountActivityReportLink() {

		if (isAccountActivityReportLinkSeen()) {
			WaitUtilities.waitUntil(driver, accountActivityReportLink, 30);
			accountActivityReportLink.click();
			logger.info("Account Activity Report Link : Clicked");
			return true;
		} else {
			logger.info("Account Activity Report Link : Not Clicked");
			return false;
		}

	}

}
