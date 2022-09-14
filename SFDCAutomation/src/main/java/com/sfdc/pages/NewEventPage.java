package com.sfdc.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sfdc.utilities.*;


public class NewEventPage extends BaseSFDCPage {

	WebDriver driver;

	public NewEventPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".comboboxIcon")
	public WebElement subComboBox;

	@FindBy(xpath = "//div[@class='bPageTitle']/following::li/a")
	public List<WebElement> subjectOption;

	@FindBy(css = "#IsRecurrence")
	public WebElement recurrCheckbox;

	@FindBy(css = "#rectypeftw")
	public WebElement weeklyFrequncy;

	@FindBy(css = "#RecurrenceEndDateOnly")
	public WebElement recurrEndDate;

	@FindBy(xpath = "//div[@class='hourPicker']/div")
	public List<WebElement> endHourPick;

	@FindBy(xpath = "//input[@id='EndDateTime_time']")
	public WebElement endTime;

	@FindBy(xpath = "//input[@id='StartDateTime_time']")
	public WebElement startTime;

	public boolean clickRecurranceCheckbox() {

		WaitUtilities.waitUntil(driver, recurrCheckbox, 30);
		if (recurrCheckbox.isDisplayed()) {
			logger.info("New Event : Recurrance checkbox is visible");
			recurrCheckbox.click();
			logger.info("New Event : Recurrance checkbox is Clicked");
			return true;
		} else {
			logger.info("New Event : Recurrance checkbox is not visible");
			return false;
		}
	}
	
	public boolean clickWeeklyFrequency() {
		
		WaitUtilities.waitUntil(driver, weeklyFrequncy, 30);
		if (weeklyFrequncy.isDisplayed()) {
			logger.info("New Event : Weekly Frequency is visible");
			weeklyFrequncy.click();
			logger.info("New Event : Weekly Frequency is Clicked");
			return true;
		} else {
			logger.info("New Event : Weekly Frequency is not visible");
			return false;
		}
	}
	
	public boolean enterRecurranceEndDate(String endDate) {
		
		WaitUtilities.waitUntil(driver, recurrEndDate, 30);
		if (recurrEndDate.isDisplayed()) {
			logger.info("New Event : Recurrance EndDate is visible");
			recurrEndDate.sendKeys(endDate);;
			logger.info("New Event : Recurrance EndDate is Clicked");
			return true;
		} else {
			logger.info("New Event : Recurrance EndDate is not visible");
			return false;
		}
	}

	public String getEndTime() {

		WaitUtilities.waitUntil(driver, endTime, 30);
		if (endTime.isDisplayed()) {
			logger.info("New Event: End time is visible");
			String start = endTime.getText();
			logger.info("New Event: End time is read");
			return start;
		} else {
			logger.info("New Event: End time is not visible");
			return null;
		}

	}

	public String getStartTime() {

		WaitUtilities.waitUntil(driver, startTime, 30);
		if (startTime.isDisplayed()) {
			logger.info("New Event: Start time is visible");
			String start = startTime.getText();
			logger.info("New Event: Start time is read");
			return start;
		} else {
			logger.info("New Event: Start time is not visible");
			return null;
		}

	}

	public boolean clickComboBox() {

		WaitUtilities.waitUntil(driver, subComboBox, 30);
		if (subComboBox.isDisplayed()) {
			logger.info("New Event: ComboBox is visible");
			subComboBox.click();
			logger.info("New Event: ComboBox is Clicked");
			return true;
		} else {
			logger.info("New Event: ComboBox is not visible");
			return false;
		}

	}

	/**
	 * @param option value {'Call' / 'Email' / 'Meeting' / 'Send Letter/Quote' /
	 *               'Other'}
	 * @return
	 */
	public boolean selectcomboBoxSubject(String option) {

		logger.info("Switching to window handle: New");
		String prevEventWindow = IOUtilities.switchWindow(driver);
		boolean boxchosen = false;

		WaitUtilities.waitUntil(driver, subjectOption.get(0), 30);
		if (subjectOption.get(0).isDisplayed()) {
			logger.info("New Event: Subject: Options visible");
			for (int i = 0; i < subjectOption.size(); i++) {
				if (option.equals(subjectOption.get(i).getText())) {
					subjectOption.get(i).click();
					logger.info("New Event: Subject " + option + " selected");
					IOUtilities.switchWindow(driver, prevEventWindow);
					logger.info("Switching to window handle: " + prevEventWindow);
					boxchosen = true;
				}
			}
		} else {
			logger.info("New Event: Subject Options not visible");
			boxchosen = false;
		}

		return boxchosen;
	}

	/**
	 * @param hourOption format H:MM AM or H:MM PM (H - range 1 to 12) (MM - values
	 *                   00 or 30)
	 * @return
	 */
	public boolean selectEventEndHour(String hourOption) {

		boolean boxchosen = false;

		WaitUtilities.waitUntil(driver, endTime, 30);
		if (endTime.isDisplayed()) {
			logger.info("New Event: Subject: End Time visible");

//			System.out.println("Event End Hour selection... "+ endTime.getText());
			endTime.click();

			WaitUtilities.waitUntil(driver, endHourPick.get(0), 30);
//			System.out.println("Event End Hour selection... [0] "+ endHourPick.get(0));
			for (int i = 0; i < endHourPick.size(); i++) {
				if (hourOption.equals(endHourPick.get(i).getText())) {
					endHourPick.get(i).click();
					logger.info("New Event: Subject " + hourOption + " selected");
					boxchosen = true;
				}
			}
		} else {
			logger.info("New Event: Subject Options not visible");
			boxchosen = false;
		}

		return boxchosen;
	}

	public boolean saveNewEvent() {
		GeneralButtonsLinks g = new GeneralButtonsLinks(driver);
		logger.info("Saving event");
		return g.clickSaveButton();
	}
}
