package com.sfdc.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sfdc.utilities.*;


public class AccountMergePage extends BaseSFDCPage {

	private static WebDriver driver;

	public AccountMergePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#srch")
	public WebElement accountSearch;

	@FindBy(name = "goNext")
	public WebElement goNextButton;

	@FindBy(xpath = "//input[@value=' Merge ']")
	public WebElement mergeButton;

	@FindBy(xpath = "//div[@class='pbWizardBody']/input[@value='Find Accounts']")
	public WebElement findAccountButton;

	@FindBy(xpath = "//div[@class='bRelatedList']//descendant::div[@class='pbBody']//descendant::td[1]")
	public List<WebElement> accountSearchList;

	@FindBy(xpath = "//div[@class='bRelatedList']//descendant::div[@class='pbBody']//descendant::input")
	public List<WebElement> accountSearchListCheckbox;

	// pop-up window OK
	// two buttons same name

	private boolean isSearchFieldSeen() {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WaitUtilities.waitUntil(driver, accountSearch, 30);
		if (accountSearch.isDisplayed()) {
			logger.info("Account Search Button: Visible");
			return true;
		} else {
			logger.info("Account Search Button: Not Visible");
			return false;
		}

	}

	public boolean clickSendSearchText(String searchText) {

		if (isSearchFieldSeen()) {
			WaitUtilities.waitUntil(driver, accountSearch, 30);
			accountSearch.click();
			logger.info("Account Search Button: Clicked");

			WaitUtilities.waitUntil(driver, accountSearch, 30);
			accountSearch.sendKeys(searchText);
			logger.info("Account Search Button: Search Text sent");
			return true;
		} else {
			logger.info("Account Search Button: Not Clicked");
			return false;
		}

	}

	private boolean isGoNextButtonSeen() {

		WaitUtilities.waitUntil(driver, goNextButton, 30);
		if (goNextButton.isDisplayed()) {
			logger.info("GoNext Button: Visible");
			return true;
		} else {
			logger.info("GoNext Button: Not Visible");
			return false;
		}

	}

	public boolean clickGoNextButton() {

		if (isGoNextButtonSeen()) {
			WaitUtilities.waitUntil(driver, goNextButton, 30);
			goNextButton.click();
			logger.info("GoNext Button: Clicked");
			return true;
		} else {
			logger.info("GoNext Button: Not Clicked");
			return false;
		}

	}

	private boolean isMergeButtonSeen() {

		WaitUtilities.waitUntil(driver, mergeButton, 30);
		if (mergeButton.isDisplayed()) {
			logger.info("Merge Button: Visible");
			return true;
		} else {
			logger.info("Merge Button: Not Visible");
			return false;
		}

	}

	public boolean clickMergeButton() {

		if (isMergeButtonSeen()) {
			WaitUtilities.waitUntil(driver, mergeButton, 30);
			mergeButton.click();
			logger.info("Merge Button: Clicked");
			return true;
		} else {
			logger.info("Merge Button: Not Clicked");
			return false;
		}

	}

	private boolean isFindAccountButtonSeen() {

		WaitUtilities.waitUntil(driver, findAccountButton, 30);
		if (findAccountButton.isDisplayed()) {
			logger.info("Find Account: Visible");
			return true;
		} else {
			logger.info("Find Account: Not Visible");
			return false;
		}

	}

	public boolean clickFindAccountButton() {

		if (isFindAccountButtonSeen()) {
			WaitUtilities.waitUntil(driver, findAccountButton, 30);
			findAccountButton.click();
			logger.info("Find Account Button: Clicked");
			return true;
		} else {
			logger.info("Find Account: Not Clicked");
			return false;
		}

	}

	public boolean selectAccountsToMerge(int count) {

		boolean accountsSelected = false;
		int i = 0;
		int numberOfAccounts = accountSearchList.size();

		if (numberOfAccounts >= count) {
			logger.info("More than "+count+" accounts are present");
			while (i < count) {
				accountSearchListCheckbox.get(i++).click();
				logger.info(i +": Account checked");
			}
			accountsSelected = true;
		}else {
			logger.info("Accounts present less than required");
		}

		return accountsSelected;

	}
	
	public boolean closeMergeAlert() {
		logger.info("Close pop-up");
		return IOUtilities.closeAlert(driver);
	}

}
