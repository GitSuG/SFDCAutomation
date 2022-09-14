package com.sfdc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sfdc.utilities.WaitUtilities;

public class CustomizeMyTabsPage extends BaseSFDCPage{

	private WebDriver driver;
	GeneralButtonsLinks g;

	public CustomizeMyTabsPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		g = new GeneralButtonsLinks(driver);
	}

	@FindBy (xpath = "//input[@name = 'save']")
	public WebElement customeSaveButton;
	
	public boolean removeTabFromSelectedList(String option) {
		if (g.selectRemoveFromSelectedTabs(option))
			if (g.clickRemoveFromSelectedButton())
				return true;

		return false;

	}
	
	public boolean verifyTabInAvailableList(String option) {
		
		return g.verifyInAvailableTabsList(option);
	}
	
	public boolean clickCustomSaveButton() {
		
		WaitUtilities.waitUntil(driver, customeSaveButton, 30);
		if(customeSaveButton.isDisplayed()) {
			customeSaveButton.click();
			logger.info("Save button in Customize My Tabs page");
			return true;
		}
		return false;
	}

}
