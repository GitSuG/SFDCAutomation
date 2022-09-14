package com.sfdc.pages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sfdc.utilities.*;

/**
 * @author ghorpade
 *
 */
public class HomePage extends BaseSFDCPage {

	private final String[] expectedItemValue = { "My Profile", "My Settings", "Developer Console",
			"Switch to Lightning Experience", "Logout" };
	String blockName;

	public static WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

//	public MyProfilePage mp;
//	public MySettingsPage ms;
//	public AccountsPage ap;
//	public ContactsPage cp;
//	public LeadsPage lp;
//	public OpportunityPage op;

	@FindBy(id = "userNavLabel")
	public WebElement userProfileName;

	@FindBy(xpath = "//h1[@class='currentStatusUserName']/a")
	public WebElement firstLastNameLink;

	@FindBy(css = "#tailBreadcrumbNode")
	public WebElement userProfilePageName;

	@FindBy(id = "userNav")
	public WebElement userNavMenu;

	@FindBy(xpath = "//div[@id='userNav-menuItems']/a")
	public List<WebElement> userMenuOptions;

	@FindBy(xpath = "//div[@id='userNav-menuItems']/a[1]")
	public WebElement myProfile;

	@FindBy(xpath = "//div[@id='userNav-menuItems']/a[2]")
	public WebElement mySettings;

	@FindBy(xpath = "//div[@id='userNav-menuItems']/a[3]")
	public WebElement devConsole;

	@FindBy(xpath = "//div[@id='userNav-menuItems']/a[4]")
	public WebElement lightningExperience;

	@FindBy(xpath = "//div[@id='userNav-menuItems']/a[5]")
	public WebElement logout;

	@FindBy(name = "customize")
	public WebElement customizeTabs;

	@FindBy(id = "AllTab_Tab")
	public WebElement allTabPlus;

	@FindBy(xpath = "//nav/ul/li")
	public List<WebElement> tabNavigationOptions;

	@FindBy(xpath = "//ul[@id='tabBar']/li[1]/a")
	public WebElement homeTab;

	@FindBy(xpath = "//table[@class='detailList tabs']/tbody/tr/td/a")
	public WebElement tabOptions;

	@FindBy(xpath = "//span[@class='pageDescription']/a")
	public WebElement eventCalendar;

	public boolean clickEventCalendar() {

		WaitUtilities.waitUntil(driver, eventCalendar, 30);
		if (eventCalendar.isDisplayed()) {
			logger.info("Calendar link is visble");
			eventCalendar.click();
			logger.info("Calendar link: Clicked");
			return true;
		} else {
			logger.info("Calendar link is not visble");
			return false;
		}

	}

	public String getUserProfileName() {
		System.out.println("1:" + userProfileName.getText());
		logger.info("Fetch Success: User profile name");
		WaitUtilities.waitUntil(driver, userProfileName, 30);
		return userProfileName.getText();
	}

	public boolean isUserMenuSeen() {
		WaitUtilities.waitUntil(driver, userNavMenu, 30);
		if (userNavMenu.isDisplayed()) {
			logger.info("User menu displayed");
			clickUserMenu();
			return true;
		} else {
			logger.info("User menu not displayed");
			return false;
		}
	}

	public boolean clickCustomizeTabs() {

		WaitUtilities.waitUntil(driver, customizeTabs, 30);
		if (customizeTabs.isDisplayed()) {
			logger.info("Customize Tabs displayed");
			customizeTabs.click();
			logger.info("Customize Tabs clicked");
			return true;
		} else {
			logger.info("Customize Tabs not displayed");
			return false;
		}
	}

	public void clickUserMenu() {
		WaitUtilities.waitUntil(driver, userNavMenu, 30);
		userNavMenu.click();
		logger.info("User Navigation Menu clicked");
	}

	public boolean isFirstLastNameLinkSeen() {
		WaitUtilities.waitUntil(driver, firstLastNameLink, 30);
		if (firstLastNameLink.isDisplayed()) {
			logger.info("User menu displayed");
			firstLastNameLink.click();
			return true;
		} else {
			logger.info("User menu not displayed");
			return false;
		}
	}

	public void clickFirstLastNameLink() {
		WaitUtilities.waitUntil(driver, firstLastNameLink, 30);
		firstLastNameLink.click();
		logger.info("Firstname Lastname Link clicked");
	}

	public String getUserProfilePageName() {
		System.out.println("2:" + userProfilePageName.getText());
		WaitUtilities.waitUntil(driver, userProfilePageName, 30);
		return userProfilePageName.getText();
	}

	public String getPageName() {
		System.out.println("3:" + firstLastNameLink.getText());
		WaitUtilities.waitUntil(driver, firstLastNameLink, 30);
		return firstLastNameLink.getText();
	}

	public void clickMyProfile() {
		WaitUtilities.waitUntil(driver, myProfile, 30);
		myProfile.click();
		logger.info("My Profile clicked");
	}

	public void clickMySettings() {
		WaitUtilities.waitUntil(driver, mySettings, 30);
		mySettings.click();
		logger.info("My Settings clicked");
	}

	public void clickDevConsole() {
		WaitUtilities.waitUntil(driver, devConsole, 30);
		devConsole.click();
		logger.info("Dev console clicked");
	}

	public boolean closeDevConsole() {

		String currentHandle = IOUtilities.switchWindow(driver);
		driver.close();
		return IOUtilities.switchWindow(driver, currentHandle);
	}

	public void clickLightningExperience() {
		WaitUtilities.waitUntil(driver, lightningExperience, 30);
		lightningExperience.click();
		logger.info("Lightning Experience clicked");
	}

	public void clickLogout() {
		WaitUtilities.waitUntil(driver, logout, 30);
		logout.click();
		logger.info("Logout clicked");
	}

	public boolean clickAllTabs() {
		WaitUtilities.waitUntil(driver, allTabPlus, 30);
		if (allTabPlus.isDisplayed()) {
			allTabPlus.click();
			logger.info("All tabs clicked");
			return true;
		} else {
			logger.info("All tabs clicked");
			return false;
		}

	}

	public void clickHomeTab() {
		WaitUtilities.waitUntil(driver, homeTab, 30);
		homeTab.click();
		logger.info("Home tab clicked");
	}

	public boolean logout() {
		String option = "Logout";
		boolean isLogoutSuccess = false;

		isLogoutSuccess = selectMenuOption(option);
		if (isLogoutSuccess)
			logger.info("Logout: Success");
		else
			logger.info("Logout: Failure");

		return isLogoutSuccess;
	}

	public boolean selectDevConsole() {
		return selectMenuOption("Developer Console");

	}

	public boolean selectMenuOption(String option) {
		boolean isSelected = false;

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		if (isUserMenuSeen()) {
			WaitUtilities.waitUntil(driver, userMenuOptions.get(0), 30);
			for (int i = 0; i < userMenuOptions.size(); i++) {

				WaitUtilities.waitUntil(driver, userMenuOptions.get(i), 30);
				String selectedOption = userMenuOptions.get(i).getText();
				logger.info("Compare: " + option + ":: Next: " + selectedOption);

				if (option.equals(selectedOption)) {
					WaitUtilities.waitUntil(driver, userMenuOptions.get(i), 30);
					userMenuOptions.get(i).click();
					logger.info("User Menu Option: '" + selectedOption + "' Selected");
					isSelected = true;
					break;
				}
			}
		}

		return isSelected;
	}

	/**
	 * @param option
	 * @return
	 */
	public boolean verifyUserMenuOptionSelected(String option) {
		boolean isSelected = false;
		for (int i = 0; i < userMenuOptions.size(); i++)
			if (option.equals(userMenuOptions.get(i + 1).getText())) {
				isSelected = true;
				logger.info(option + "is selected");
				break;
			}
		return isSelected;
	}

	/**
	 * @return
	 */
	public boolean verifyUserMenuOptionsAll() {

		boolean isSelected = false;
		for (int i = 0; i < userMenuOptions.size(); i++) {
			String actualOptionValue = userMenuOptions.get(i).getText();
			logger.info("Next: " + actualOptionValue);
			if (expectedItemValue[i].equals(actualOptionValue)) {
				logger.info(expectedItemValue[i] + " : verified");
				isSelected = true;
			} else {
				logger.info(expectedItemValue[i] + " : not verified");
				isSelected = false;
			}
		}
		return isSelected;
	}

	public boolean selectMyProfile() {

//		HomePage hp = new HomePage(driver);
		return selectMenuOption("My Profile");
	}

	public boolean selectMySettings() {

//		HomePage hp = new HomePage(driver);
		return selectMenuOption("My Settings");
	}

	/**
	 * @return
	 */
	public boolean clickAccounts() {
		return selectNavigationTab("Accounts");
	}

	public boolean clickHome() {
		return selectNavigationTab("Home");
	}

	/**
	 * @return
	 */
	public boolean clickContacts() {
		return selectNavigationTab("Contacts");
	}

	/**
	 * @return
	 */
	public boolean clickLeads() {
		return selectNavigationTab("Leads");
	}

	/**
	 * @return
	 */
	public boolean clickOpportunities() {
		return selectNavigationTab("Opportunities");
	}

	/**
	 * @return
	 */
	public boolean clickEventFromCreateMenu() {
		return selectNavigationTab("Event");
	}

	public boolean selectTaskFromCreateMenu() {
		return selectFromMainMenu("Task");
	}

	public boolean selectAccountFromCreateMenu() {
		return selectFromMainMenu("Account");
	}

	public boolean selectContactFromCreateMenu() {
		return selectFromMainMenu("Contact");
	}

	public boolean selectOpportunityFromCreateMenu() {
		return selectFromMainMenu("Opportunity");
	}

	public boolean selectLeadFromCreateMenu() {
		return selectFromMainMenu("Lead");
	}

	public boolean selectFromMainMenu(String option) {
		GeneralButtonsLinks g = new GeneralButtonsLinks(driver);
		return g.selectCreateNewMenuOption(option);
	}

	/**
	 * @param tabName
	 * @return
	 */
	public boolean selectNavigationTab(String tabName) {
		boolean tabSelected = false;

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (WebElement w : tabNavigationOptions) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (tabName.equals(w.getText())) {
				w.click();
				tabSelected = true;
				break;
			}
		}

		if (tabSelected)
			logger.info(tabName + " tab selected");
		else {
			logger.info("Tab not found on Home Page");
		}
		return tabSelected;
	}

	/**
	 * @param tabName
	 * @return
	 */
	public boolean verifyNavigationTab(String tabName) {
		boolean tabSelected = false;

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (WebElement w : tabNavigationOptions) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (tabName.equals(w.getText())) {
//				w.click();
				tabSelected = true;
				break;
			}
		}

		if (tabSelected)
			logger.info(tabName + " tab verified");
		else {
			logger.info("Tab not found on Home Page");
		}
		return tabSelected;
	}

}
