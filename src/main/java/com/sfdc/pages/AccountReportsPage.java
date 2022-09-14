package com.sfdc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sfdc.utilities.WaitUtilities;

public class AccountReportsPage extends BaseSFDCPage {

	private static WebDriver driver;

	public AccountReportsPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "ext-gen148")
	public WebElement dateFieldArrow;

//	Does this id change???
	// div[@class='x-combo-list-inner']
	@FindBy(xpath = "//*[@id=\"ext-gen277\"]/div[3]")
	public WebElement createdDateFieldOption;

	@FindBy(xpath = "//input[@name='dateColumn']")
	public WebElement dateFieldCreatedDate;

	@FindBy(xpath = "//input[@name='dateColumn']//following-sibling::img")
	public WebElement dateFieldDropArrow;

	@FindBy(xpath = "//input[@name='startDate']")
	public WebElement startDate;

	@FindBy(xpath = "//input[@name='endDate']")
	public WebElement endDate;

	//// table[@id='saveReportBtn']//following::button[@id='ext-gen49']
	@FindBy(id = "ext-gen49")
	public WebElement saveButton;

	@FindBy(name = "reportName")
	public WebElement reportName;

	@FindBy(name = "reportDevName")
	public WebElement reportUniqueName;

	@FindBy(id = "dlgSaveReport")
	public WebElement saveReportButton;

	@FindBy(id = "dlgSaveAndRun")
	public WebElement saveAndRunReportButton;

	// check
	@FindBy(xpath = "//div[@class='content']/h1")
	public WebElement actualReportName;

	@FindBy(xpath = "//table[@class='x-grid3-row-table']//following::div[@class='x-grid3-cell-inner']/b")
	public WebElement grandTotalRecords;

	public boolean selectCreatedDateActivityReport(String startDateValue, String endDateValue) {

		boolean optionSelected = false;
		int total = 0;

		if (selectCreatedDate())
			if (clickSendStartDate(startDateValue))
				if (clickSendEndDate(endDateValue)) {
					total = getGrandTotalRecords();
					logger.info("#Records: " + total);
					if (total > 0)
						optionSelected = true;
				}

		return optionSelected;

	}

	private boolean isStartDateSeen() {

		WaitUtilities.waitUntil(driver, startDate, 30);
		if (startDate.isDisplayed()) {
			logger.info("Start Date: Visible");
			return true;
		} else {
			logger.info("Start Date: Not Visible");
			return false;
		}

	}

	public boolean clickSendStartDate(String startDateValue) {

		if (isStartDateSeen()) {
			WaitUtilities.waitUntil(driver, startDate, 30);
			startDate.click();
			logger.info("Start Date: Clicked");

			WaitUtilities.waitUntil(driver, startDate, 30);
			startDate.clear();
			startDate.sendKeys(startDateValue);
			logger.info("Start Date: Entered: " + startDateValue);

			return true;
		} else {
			logger.info("Start Date: Not Clicked");
			return false;
		}

	}

	private boolean isEndDateSeen() {

		WaitUtilities.waitUntil(driver, endDate, 30);
		if (endDate.isDisplayed()) {
			logger.info("End Date: Visible");
			return true;
		} else {
			logger.info("End Date: Not Visible");
			return false;
		}

	}

	public boolean clickSendEndDate(String endDateValue) {

		if (isEndDateSeen()) {
			WaitUtilities.waitUntil(driver, endDate, 30);
			endDate.click();
			logger.info("End Date: Clicked");

			WaitUtilities.waitUntil(driver, endDate, 30);
			endDate.clear();
			endDate.sendKeys(endDateValue);
			logger.info("End Date: Entered: " + endDateValue);

			return true;
		} else {
			logger.info("End Date: Not Clicked");
			return false;
		}

	}

	private boolean isDateFieldArrowSeen() {

		WaitUtilities.waitUntil(driver, dateFieldArrow, 30);
		if (dateFieldArrow.isDisplayed()) {
			logger.info("Date Field Arrow: Visible");
			return true;
		} else {
			logger.info("Date Field Arrow: Not Visible");
			return false;
		}

	}

	private boolean isCreatedDateSeen() {

		WaitUtilities.waitUntil(driver, dateFieldCreatedDate, 30);
		if (dateFieldCreatedDate.isDisplayed()) {
			logger.info("Created Date: Visible");
			return true;
		} else {
			logger.info("Created Date: Not Visible");
			return false;
		}

	}

	private boolean selectCreatedDateFromFieldOptions() {

		boolean selected = false;
		if (isDateFieldArrowSeen()) {
			WaitUtilities.waitUntil(driver, createdDateFieldOption, 30);
			if (createdDateFieldOption.getText().equals("Created Date")) {
				createdDateFieldOption.click();
				System.out.println(createdDateFieldOption.getText());
				logger.info("Created Date Field: Clicked");
				selected = true;
			}
		} else {
			logger.info("Created Date Field: Not Clicked");
			selected = false;
		}
		return selected;
	}

	public boolean selectCreatedDate() {

		boolean selected = false;

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (isDateFieldArrowSeen() && isCreatedDateSeen()) {
			WaitUtilities.waitUntil(driver, dateFieldArrow, 30);
			dateFieldArrow.click();

			WaitUtilities.waitUntil(driver, dateFieldCreatedDate, 30);
//			dateFieldCreatedDate.click();

			logger.info("Created Date: Clicked");
			if (selectCreatedDateFromFieldOptions())
				selected = true;
		} else {
			logger.info("Created Date: Not Clicked");
			selected = false;
		}
		return selected;
	}

	private boolean isSaveButtonSeen() {

		WaitUtilities.waitUntil(driver, saveButton, 30);
		if (saveButton.isDisplayed()) {
			logger.info("Save Button: Visible");
			return true;
		} else {
			logger.info("Save Button: Not Visible");
			return false;
		}

	}

	public boolean clickSaveButton() {

		if (isSaveButtonSeen()) {
			WaitUtilities.waitUntil(driver, saveButton, 30);
			saveButton.click();
			logger.info("Save Button: Clicked");
			return true;
		} else {
			logger.info("Save Button: Not Clicked");
			return false;
		}

	}

	private boolean isSaveReportButtonSeen() {

		WaitUtilities.waitUntil(driver, saveReportButton, 30);
		if (saveReportButton.isDisplayed()) {
			logger.info("Save Report Button: Visible");
			return true;
		} else {
			logger.info("Save Report Button: Not Visible");
			return false;
		}

	}

	public boolean clickSaveReportButton() {

		if (isSaveReportButtonSeen()) {
			WaitUtilities.waitUntil(driver, saveReportButton, 30);
			saveReportButton.click();
			logger.info("Save Report Button: Clicked");
			return true;
		} else {
			logger.info("Save Report Button: Not Clicked");
			return false;
		}

	}

	private boolean isGrandTotalRecordsSeen() {

		WaitUtilities.waitUntil(driver, grandTotalRecords, 30);
		if (grandTotalRecords.isDisplayed()) {
			logger.info("Grand Total Records: Visible");
			return true;
		} else {
			logger.info("Grand Total Records: Not Visible");
			return false;
		}

	}

	private int getGrandTotalRecords() {

		int count = 0;

		if (isGrandTotalRecordsSeen()) {
			WaitUtilities.waitUntil(driver, grandTotalRecords, 30);
			String totalString = grandTotalRecords.getText();
			logger.info("Grand Total Records: Read Success");

			String[] splitString = totalString.split(" ");
			String countText = splitString[2].replace("(", "");
			count = Integer.parseInt(countText);
			logger.info("Grand Total Records: Read: #" + count);

		} else
			logger.info("Grand Total Records: Read Error");

		return count;
	}

}
