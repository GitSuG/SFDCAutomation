package com.sfdc.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sfdc.utilities.WaitUtilities;

public class AccountViewPage extends BaseSFDCPage{


	private static WebDriver driver;

	public AccountViewPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='fname']")
	public WebElement viewName;
	
	@FindBy(xpath = "//input[@id='devname']")
	public WebElement viewUniqueName;

	@FindBy(xpath = "//select[@id='fcol1']/option")
	public List<WebElement> fieldCol1;
	
	@FindBy(xpath = "//select[@id='fop1']/option")
	public List<WebElement> fieldOp1;	

	@FindBy(xpath = "//input[@id='fval1']")
	public WebElement fieldVal1;	

	@FindBy(id = "colselector_select_0")
	public WebElement availableFields;
	
	@FindBy(xpath = "//div[@class='zen-mbs text']/a[@id='colselector_select_0_right']")
	public WebElement moveRightButton;
	
	
	private boolean isAccountViewNameFieldSeen() {
		
		WaitUtilities.waitUntil(driver, viewName, 30);
		if(viewName.isDisplayed()) {
			logger.info("Account View Name Field: Visible");
			return true;
		}else{
			logger.info("Account View Name Field: Not Visible");
			return false;
		}
	}
	
	public boolean enterAccountViewName(String newViewNameText) {
		
		if(isAccountViewNameFieldSeen()) {
			WaitUtilities.waitUntil(driver, viewName, 30);
			viewName.sendKeys(newViewNameText);
			logger.info("Account View Name Entry: Successful: "+newViewNameText);
			return true;
		}else{
			logger.info("Account View Name Entry: Not successful");
			return false;
		}
	}
	
	private boolean isAccountViewUniqueNameFieldSeen() {
		
		WaitUtilities.waitUntil(driver, viewUniqueName, 30);
		if(viewUniqueName.isDisplayed()) {
			logger.info("Account View UniqueName Field: Visible");
			return true;
		}else{
			logger.info("Account View UniqueName Field: Not Visible");
			return false;
		}
	}
	
	public boolean enterAccountViewUniqueName(String newViewUniqueNameText) {
		
		if(isAccountViewUniqueNameFieldSeen()) {
			WaitUtilities.waitUntil(driver, viewUniqueName, 30);
			viewUniqueName.clear();
			viewUniqueName.sendKeys(newViewUniqueNameText);
			logger.info("Account View UniqueName Entry: Successful: "+newViewUniqueNameText);
			return true;
		}else{
			logger.info("Account View UniqueName Entry: Not successful");
			return false;
		}
	}
	
	public boolean selectFilterOperatorOption(String expectedOperator) {

		boolean isOperatorFound = false;

		for (int i = 0; i < fieldOp1.size(); i++) {

			WaitUtilities.waitUntil(driver, fieldOp1.get(i), i);
			String currentOption = fieldOp1.get(i).getText();

			if (expectedOperator.equals(currentOption)) {
				logger.info("Fetch Success: Filter Operator : " + currentOption);
				fieldOp1.get(i).click();
				isOperatorFound = true;
			}
		}

		return isOperatorFound;
	}
	
	public boolean selectFilterFieldOption(String expectedFilterField) {

		boolean isFieldFound = false;

		for (int i = 0; i < fieldCol1.size(); i++) {

			WaitUtilities.waitUntil(driver, fieldCol1.get(i), i);
			String currentOption = fieldCol1.get(i).getText();

			if (expectedFilterField.equals(currentOption)) {
				logger.info("Fetch Success: Filter Field : " + currentOption);
				fieldCol1.get(i).click();
				isFieldFound = true;
			}
		}

		return isFieldFound;
	}
	

	private boolean isFilterValueSeen() {

		WaitUtilities.waitUntil(driver, fieldVal1, 30);
		if (fieldVal1.isDisplayed()) {
			logger.info("Filter Value field: Visible");
			return true;
		} else {
			logger.info("Filter Value field: Not Visible");
			return false;
		}

	}

	public boolean enterFilterValue(String value) {

		if (isFilterValueSeen()) {
			WaitUtilities.waitUntil(driver, fieldVal1, 30);
			fieldVal1.sendKeys(value);
			logger.info("Filter Value field: Edited");
			return true;
		} else {
			logger.info("Filter Value field: Not Edited");
			return false;
		}

	}
	
	public boolean addFilter(String expectedFilterField, 
							String expectedOperator ,
							String value) {
		
		boolean filterAdded = false;
		
		if(selectFilterFieldOption(expectedFilterField)) {
			if(selectFilterOperatorOption(expectedOperator)) {
				if(enterFilterValue(value))
					filterAdded = true;
			}
		}
		
		return filterAdded;
		
	}


	
}
