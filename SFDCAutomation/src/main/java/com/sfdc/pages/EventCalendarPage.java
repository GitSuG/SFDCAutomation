package com.sfdc.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sfdc.utilities.WaitUtilities;

public class EventCalendarPage extends BaseSFDCPage {

	WebDriver driver;

	public EventCalendarPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	static int eventIndex = 0;
	int startTime = 0;

	String eventHourID = "p:f:j_id25:j_id61:" + eventIndex + ":j_id64";

	// Use subjectIndex + 1 to access half hour slot;
	static int subjectIndex;
	String eventHalfHourID;

	@FindBy(xpath = "//td[@class='fixedTable']/descendant::a")
	public List<WebElement> eventHour;

	@FindBy(xpath = "//td[@class='dayBlock']/child::div/following::a/span")
	public List<WebElement> eventSubject;

	@FindBy(xpath = "//span[@class='dwmIcons']/a[@title='Month View']")
	public WebElement monthViewButton;
	
	/**
	 * @param option - String with time in format "H:00 AM" (H valid range - 6 to
	 *               11) "H:00 PM" (H valid range - 1 to 12)
	 * @return
	 */
	public boolean selectEventHour(String option) {

		startTime = Integer.parseInt(option.substring(0, 1));

		boolean selectStatus = false;
		WaitUtilities.waitUntil(driver, eventHour.get(0), 30);
		if (eventHour.get(0).isDisplayed()) {
			logger.info("Event Hour is visible");
			for (int i = 0; i < eventHour.size(); i++) {
				if (option.equals(eventHour.get(i).getText())) {
					logger.info("Event Hour match found");
					eventIndex = i;
					eventHour.get(i).click();
					logger.info("Event Hour: Selected :: Index = "+ eventIndex);
					selectStatus = true;
				}
			}
			logger.info("Event Hour match not found");
		} else {
			logger.info("Event Hour not visible");
			selectStatus = false;
		}
		return selectStatus;

	}

	public String getEventDetails() {

		System.out.println("Event Starts at: " + startTime);
		subjectIndex = eventIndex * 2;
		eventHalfHourID = "p:f:j_id25:j_id69:" + subjectIndex + ":hh";
		WebElement eventTime = driver.findElement(By.id(eventHalfHourID));
		boolean eventFound = false;

		WaitUtilities.waitUntil(driver, eventTime, 30);
		if (eventTime.isDisplayed()) {
			if (eventTime.getText().isEmpty()) {
				logger.info("Event Details: Check half hour slot");
				eventIndex++;
				subjectIndex = eventIndex * 2;
				eventHalfHourID = "p:f:j_id25:j_id69:" + subjectIndex + ":hh";
				eventTime = driver.findElement(By.id(eventHalfHourID));
				if (eventTime.isDisplayed())
					logger.info("Event Details: slot found");
					eventFound = true;
			}else {
				logger.info("Event Details: slot found"+eventHalfHourID);
				eventFound = true;
			}
		}else {
			eventFound = false;
			logger.info("Event Details: slot not found"+eventHalfHourID);
		}
		
		if(eventFound)
			return eventTime.getText();
		else
			return null;
		
	}
	
	
	public boolean clickMonthView() {
		
		WaitUtilities.waitUntil(driver, monthViewButton, 30);
		if(monthViewButton.isDisplayed()) {
			logger.info("Event Calendar: Month View Button is visible");
			monthViewButton.click();
			logger.info("Event Calendar: Month View Button is Clicked");
			return true;
		}else {
			logger.info("Event Calendar: Month View Button is not visible");
			return false;
		}
		
	}

}
