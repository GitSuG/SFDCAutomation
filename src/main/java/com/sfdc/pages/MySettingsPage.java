package com.sfdc.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sfdc.utilities.WaitUtilities;

public class MySettingsPage extends BaseSFDCPage {

	public static WebDriver driver;

	public MySettingsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	Personal Information - Section
	@FindBy(xpath = "//div[@id='PersonalInfo']/a")
	public WebElement personalInfoSection;

	// Login History - Sub-section
	@FindBy(xpath = "//div[@class='setupLeaf']/a[@id='LoginHistory_font']")
	public WebElement loginHistory;

	@FindBy(xpath = "//div[@class='pShowMore']/a")
	public WebElement downloadHistoryLink;

//	Display and Layout - Section	
	@FindBy(xpath = "//div[@id='DisplayAndLayout']/a")
	public WebElement diaplayNLayoutSection;

	// Customize Tabs - Sub-section
	@FindBy(xpath = "//div[@class='setupLeaf']/a[@id='CustomizeTabs_font']")
	public WebElement customizeTabs;

	@FindBy(xpath = "//select[@id='duel_select_0']/option[@value='report']")
	public WebElement selectReportsOptionOnLeft;

	@FindBy(xpath = "//select[@id='duel_select_1']/option[@value='report']")
	public WebElement selectReportsOptionOnRight;

	@FindBy(xpath = "//select[@id='duel_select_1']/option")
	public List<WebElement> selectedTabs;

	@FindBy(xpath = "//div[@class='zen-mbs text']/a[@id='duel_select_0_right']")
	public WebElement moveRightButton;

	@FindBy(xpath = "//div[@class='text']/a[@id='duel_select_0_left']")
	public WebElement moveLeftButton;

	@FindBy(name = "save")
	public WebElement saveTabButton;

	@FindBy(xpath = "//select[@id='duel_select_0']/option")
	public List<WebElement> availableTabOption;

	@FindBy(xpath = "//select[@id='duel_select_1']/option")
	public List<WebElement> selectedTabOption;

//	Email - Section
	@FindBy(xpath = "//div[@id='EmailSetup']/a[@class='header setupFolder']")
	public WebElement emailSetupSection;

	// Email Settings - sub-section
	@FindBy(xpath = "//a[@id='EmailSettings_font']")
	public WebElement emailSettings;

	@FindBy(css = "#sender_name")
	public WebElement senderName;

	@FindBy(css = "#sender_email")
	public WebElement senderEmail;

	@FindBy(css = "#auto_bcc1")
	public WebElement selectBccRadio;

	@FindBy(xpath = "//input[@name='save']")
	public WebElement saveEmailSettingsButton;

//	Calendars and Reminders Section
	@FindBy(xpath = "//div[@id='CalendarAndReminders']/a")
	public WebElement calendarReminderSection;

	@FindBy(xpath = "//a[@id='Reminders_font']")
	public WebElement activityReminder;

	@FindBy(id = "testbtn")
	public WebElement testReminderButton;

	@FindBy(xpath = "//div[@id='summary0']/div")
	public WebElement sampleReminder;

	/**
	 * 
	 */
	public boolean downloadLoginHistory() {

		boolean isDownloadComplete = false;
		if (personalInfoSection.isDisplayed()) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			WaitUtilities.waitUntil(driver, personalInfoSection, 30);
			personalInfoSection.click();
			logger.info("My Settings: Personal Information link clicked");

			WaitUtilities.waitUntil(driver, loginHistory, 30);
			if (loginHistory.isDisplayed()) {
				loginHistory.click();
				logger.info("My Settings: Login History clicked");

				WaitUtilities.waitUntil(driver, downloadHistoryLink, 30);
				if (downloadHistoryLink.isDisplayed()) {
					downloadHistoryLink.click();
					logger.info("My Settings: Download History link clicked");
					isDownloadComplete = true;
				}
			}
		}
		return isDownloadComplete;
	}

	/**
	 * @param selectedTab
	 */
	public boolean customizeMyTabs(String selectedTab) {

		logger.info("My Settings: Adding " + selectedTab + " to customize account");
		boolean isTabCustomized = false;
		if (selectDiaplayNLayoutSection())
			if (selectCustomizeTabs())
				if (selectReportsTab(selectedTab) == true) {
					if (clickedMoveToRightButton())
						if (clickedSaveButton())
							isTabCustomized = true;
				} else {
					isTabCustomized = true;
				}

		return isTabCustomized;
	}

	private boolean selectDiaplayNLayoutSection() {

		WaitUtilities.waitUntil(driver, diaplayNLayoutSection, 30);
		if (diaplayNLayoutSection.isDisplayed()) {
			diaplayNLayoutSection.click();
			logger.info("Customize Settings: Diaplay N Layout Section selected");
			return true;
		} else {
			logger.info("customize Settings: Diaplay N Layout Section not selected");
			return false;
		}
	}

	private boolean selectCustomizeTabs() {

		WaitUtilities.waitUntil(driver, customizeTabs, 30);
		if (customizeTabs.isDisplayed()) {
			customizeTabs.click();
			logger.info("Customize Settings: Customize Tab selected");
			return true;
		} else {
			logger.info("customize Settings: Customize Tab not selected");
			return false;
		}
	}

	private boolean selectReportsTab(String selectedTab) {

		/*
		 * Ask if this will work or not
		 */

		boolean reportStatus = false;
		WaitUtilities.waitUntil(driver, selectReportsOptionOnLeft, 30);
		if (selectedTab.equals("Reports") && selectReportsOptionOnLeft.isDisplayed()) {
			selectReportsOptionOnLeft.click();
			logger.info("Customize Settings: Reports Tab selected");
			reportStatus = true;
		} else {

			WaitUtilities.waitUntil(driver, selectReportsOptionOnRight, 30);
			if (selectedTab.equals("Reports") && selectReportsOptionOnRight.isDisplayed()) {
				logger.info("Customize Settings: Reports Tab already present in Settings");
				reportStatus = false;
			}
		}
		return reportStatus;

	}

	public boolean moveSelectedTab(String tabOption, String fromPositionTo) {

		boolean toRight = false;
		boolean toLeft = false;

		boolean status = false;

		if (fromPositionTo.equals("LeftToRight"))
			toRight = true;
		else if (fromPositionTo.equals("RightToLeft"))
			toLeft = true;

		if (toRight) {
			logger.info("Moving: '" + tabOption + "' Tab from left to right");
			if (selectDiaplayNLayoutSection())
				if (selectCustomizeTabs()) {
					moveToRight(tabOption);
					status = true;
				}
		} else {
			logger.info("Moving: '" + tabOption + "' Tab already on left :: NA");
		}

		if (toLeft) {
			logger.info("Moving: '" + tabOption + "' Tab from right to left");
			if (selectDiaplayNLayoutSection())
				if (selectCustomizeTabs()) {
					moveToLeft(tabOption);
					status = true;
				}
		} else {
			logger.info("Moving: '" + tabOption + "' Tab already on right :: NA");
		}

		return status;
	}

	private void moveToLeft(String tabOption) {
		WaitUtilities.waitUntil(driver, availableTabOption.get(0), 30);
		if (selectedTabOption.get(0).isDisplayed()) {

			for (WebElement w : selectedTabOption) {
				if (tabOption.equals(w.getText())) {
					logger.info("Tab chosen: " + tabOption + " is present in Selected Tabs");
					w.click();
					clickedMoveToLeftButton();
					clickedSaveButton();
				}
			}
		}

	}

	private void moveToRight(String tabOption) {
		WaitUtilities.waitUntil(driver, availableTabOption.get(0), 30);
		if (availableTabOption.get(0).isDisplayed()) {

			for (WebElement w : availableTabOption) {
				System.out.println("-------"+w.getText());
				if (tabOption.equals(w.getText())) {
					logger.info("Tab chosen: " + tabOption + " is present in Available Tabs");
					w.click();
					clickedMoveToRightButton();
					clickedSaveButton();
				}
			}
		}

	}

	private boolean clickedMoveToLeftButton() {

		WaitUtilities.waitUntil(driver, moveLeftButton, 30);
		if (moveLeftButton.isDisplayed()) {
			moveLeftButton.click();
			logger.info("Customize Settings: MoveLeftButton button clicked");
			return true;
		} else {
			logger.info("customize Settings: MoveLeftButton button not clicked");
			return false;
		}

	}

	private boolean clickedMoveToRightButton() {

		WaitUtilities.waitUntil(driver, moveRightButton, 30);
		if (moveRightButton.isDisplayed()) {
			moveRightButton.click();
			logger.info("Customize Settings: MoveToRight button clicked");
			return true;
		} else {
			logger.info("customize Settings: MoveToRight button not clicked");
			return false;
		}

	}

	private boolean clickedSaveButton() {

		WaitUtilities.waitUntil(driver, saveTabButton, 30);
		if (saveTabButton.isDisplayed()) {
			saveTabButton.click();
			logger.info("Customize Settings: Save button clicked");
			return true;
		} else {
			logger.info("customize Settings: Save button not clicked");
			return false;
		}

	}

	/**
	 * @param sender
	 * @param senderEmailAddress
	 */
	public boolean customizeEmailSettings(String sender, String senderEmailAddress) {

		boolean isEmailSettingSaved = false;

		WaitUtilities.waitUntil(driver, emailSetupSection, 30);
		if (emailSetupSection.isDisplayed()) {
			emailSetupSection.click();
			logger.info("My Settings: Email setup clicked");

			WaitUtilities.waitUntil(driver, emailSettings, 30);
			if (emailSettings.isDisplayed()) {
				emailSettings.click();
				logger.info("My Settings: Email settings clicked");

				WaitUtilities.waitUntil(driver, senderName, 30);
				if (senderName.isDisplayed()) {
					senderName.sendKeys(sender);
					logger.info("My Settings: Enter Sender name");

					WaitUtilities.waitUntil(driver, senderEmail, 30);
					if (senderEmail.isDisplayed()) {
						senderEmail.sendKeys(senderEmailAddress);
						logger.info("My Settings: Enter Sender Address");

						WaitUtilities.waitUntil(driver, selectBccRadio, 30);
						if (selectBccRadio.isDisplayed()) {
							selectBccRadio.click();
							logger.info("My Settings: Bcc radio selected");

							WaitUtilities.waitUntil(driver, saveEmailSettingsButton, 30);
							if (saveEmailSettingsButton.isDisplayed()) {
								saveEmailSettingsButton.click();
								logger.info("My Settings: Email settings saved");
								isEmailSettingSaved = true;
							}
						}
					}
				}
			}
		}
		return isEmailSettingSaved;
	}

	/**
	 * Test the working of the
	 */
	public boolean testActivityCalendarReminders() {

		// pop-up window test verification pending
		boolean testCalenderReminder = false;
		WaitUtilities.waitUntil(driver, calendarReminderSection, 30);
		if (calendarReminderSection.isDisplayed()) {
			calendarReminderSection.click();

			WaitUtilities.waitUntil(driver, activityReminder, 30);
			if (activityReminder.isDisplayed()) {
				activityReminder.click();

				WaitUtilities.waitUntil(driver, testReminderButton, 30);
				if (testReminderButton.isDisplayed()) {
					testReminderButton.click();
					testCalenderReminder = true;
				}
			}
		}

		return testCalenderReminder;

	}

	/**
	 * Verification if chosen Tab (Reports) is Added to Selected Tabs
	 * 
	 * @param choiceTab - Reports
	 * @return boolean = true - Chosen tab has been added = false - Tab not found
	 *         among Selected Tabs
	 */
	public boolean verifySelectedTabs(String choiceTab) {

		boolean isPresent = false;

		WaitUtilities.waitUntil(driver, selectedTabs.get(0), 30);
		logger.info("Tab chosen: " + choiceTab);
		for (WebElement w : selectedTabs) {
			if (choiceTab.equals(w.getText())) {
				logger.info("Tab chosen: " + choiceTab + " is present");
				isPresent = true;
			}
		}

		return isPresent;
	}

}
