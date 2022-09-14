package com.sfdc.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sfdc.utilities.WaitUtilities;

public class GeneralButtonsLinks extends BaseSFDCPage {

	static WebDriver driver;
	Logger logger = LogManager.getLogger(GeneralButtonsLinks.class.getName());

	public GeneralButtonsLinks(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(xpath = "//td[@class='pbButton']/input[@value=' New ']")
	public WebElement newButton;

	@FindBy(xpath = "//td[@id='topButtonRow']/input[@value=' Save ']")
	public WebElement saveButton;

	@FindBy(xpath = "//td[@id='topButtonRow']/input[@value=' Edit ']")
	public WebElement editButton;

	@FindBy(xpath = "//td[@id='topButtonRow']/input[@value='Cancel']")
	public WebElement cancelButton;

	@FindBy(xpath = "//td[@id='topButtonRow']/input[@value='Save & New']")
	public WebElement saveNewButton;

	@FindBy(xpath = "//input[@value=' Go! ']")
	public WebElement goButton;

	@FindBy(xpath = "//*[@id='editPage']/div[3]/table/tbody/tr/td[2]/input[@value=' Save ']")
	public WebElement saveViewButton;

	@FindBy(xpath = "//*[@id='editPage']/div[3]/table/tbody/tr/td[2]/input[@value='Cancel']")
	public WebElement cancelViewButton;

//	--------------------------------------------------

	@FindBy(xpath = "//div[@id='createNewMenu']/a")
	public List<WebElement> createNewMenu;

	@FindBy(xpath = "//span[@class='fFooter']/a[1]")
	public WebElement editView;

	@FindBy(xpath = "//span[@class='fFooter']/a[2]")
	public WebElement createNewView;

	@FindBy(xpath = "//select[@name='fcf']")
	public WebElement viewDropDown;

	@FindBy(xpath = "//select[@name='fcf']/option")
	public List<WebElement> viewOptions;

	@FindBy(xpath = "//select[@id='hotlist_mode']/option")
	public List<WebElement> filterOverviewOptions;

	@FindBy(xpath = "//h2[@class='pageDescription']")
	public WebElement pageHeader;

	@FindBy(xpath = "//h2[@class='pageDescription']/preceding-sibling::h1")
	public WebElement pageTitle;

	@FindBy(xpath = "//div[@class='zen-mbs text']//following::div/a[@id='duel_select_0_left']")
	public WebElement moveLeftButton;

	@FindBy(xpath = "//div[@class='zen-mbs text']/a[@id='duel_select_0_right']")
	public WebElement moveRightButton;

	@FindBy(xpath = "//select[@id='duel_select_1']/option")
	public List<WebElement> selectedTabsList;
	
	@FindBy(xpath = "//select[@id='duel_select_0']/option")
	public List<WebElement> availableTabsList;

	
	public boolean selectRemoveFromSelectedTabs(String option) {
	
		boolean tabSelected = false;
		WaitUtilities.waitUntil(driver, selectedTabsList.get(0), 30);
		if(selectedTabsList.get(0).isDisplayed()) {
			logger.info("Selected Tab List is visible");
			for(int i = 0; i < selectedTabsList.size(); i++) {
				if(option.equals(selectedTabsList.get(i).getText())) {
					selectedTabsList.get(i).click();
					tabSelected = true;
					logger.info(option+" chosen from Selected List");
				}
			}
		}else {
			logger.info("Selected Tab List is not visible");
		}
		return tabSelected;
	}
	
	
	public boolean selectAddFromAvailableTabs(String option) {
		
		boolean tabSelected = false;
		WaitUtilities.waitUntil(driver, availableTabsList.get(0), 30);
		if(availableTabsList.get(0).isDisplayed()) {
			logger.info("Available Tab List is visible");
			for(int i = 0; i < availableTabsList.size(); i++) {
				if(option.equals(availableTabsList.get(i).getText())) {
					availableTabsList.get(i).click();
					tabSelected = true;
					logger.info(option+" chosen from Available List");
				}
			}
		}else {
			logger.info("Available Tab List is not visible");
		}
		return tabSelected;
	}
	
	public boolean verifyInAvailableTabsList(String option) {
		
		boolean tabSelected = false;
		WaitUtilities.waitUntil(driver, availableTabsList.get(0), 30);
		if(availableTabsList.get(0).isDisplayed()) {
			logger.info("Available Tab List is visible");
			for(int i = 0; i < availableTabsList.size(); i++) {
				WaitUtilities.waitUntil(driver, availableTabsList.get(i), 30);
				if(option.equals(availableTabsList.get(i).getText())) {
					tabSelected = true;
					logger.info(option+" chosen from Available List");
				}
			}
		}else {
			logger.info("Available Tab List is not visible");
		}
		return tabSelected;
	}
	
	
	public boolean clickAddToSelectedButton() {

		WaitUtilities.waitUntil(driver, moveRightButton, 30);
		if (moveRightButton.isDisplayed()) {
			moveRightButton.click();
			logger.info("Customize Settings: Add selection button clicked");
			return true;
		} else {
			logger.info("customize Settings: Add selection button not clicked");
			return false;
		}

	}

	public boolean clickRemoveFromSelectedButton() {

		WaitUtilities.waitUntil(driver, moveLeftButton, 30);
		if (moveLeftButton.isDisplayed()) {
			moveLeftButton.click();
			logger.info("Customize Settings: Remove selection button clicked");
			return true;
		} else {
			logger.info("customize Settings: Remove selection button not clicked");
			return false;
		}

	}

	private boolean isNewButtonSeen() {

		WaitUtilities.waitUntil(driver, newButton, 30);
		if (newButton.isDisplayed()) {
			logger.info("New View Button: Visible");
			return true;
		} else {
			logger.info("New View Button: Not Visible");
			return false;
		}

	}

	public boolean clickNewButton() {

		if (isNewButtonSeen()) {
			WaitUtilities.waitUntil(driver, newButton, 30);
			newButton.click();
			logger.info("New View Button: Clicked");
			return true;
		} else {
			logger.info("New View Button: Not Clicked");
			return false;
		}

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

	private boolean isEditButtonSeen() {

		WaitUtilities.waitUntil(driver, editButton, 30);
		if (editButton.isDisplayed()) {
			logger.info("Edit Button: Visible");
			return true;
		} else {
			logger.info("Edit Button: Not Visible");
			return false;
		}

	}

	public boolean clickEditButton() {

		if (isEditButtonSeen()) {
			WaitUtilities.waitUntil(driver, editButton, 30);
			editButton.click();
			logger.info("Edit Button: Clicked");
			return true;
		} else {
			logger.info("Edit Button: Not Clicked");
			return false;
		}

	}

	private boolean isCancelButtonSeen() {

		WaitUtilities.waitUntil(driver, cancelButton, 30);
		if (cancelButton.isDisplayed()) {
			logger.info("Cancel Button: Visible");
			return true;
		} else {
			logger.info("Cancel Button: Not Visible");
			return false;
		}

	}

	public boolean clickCancelButton() {

		if (isCancelButtonSeen()) {
			WaitUtilities.waitUntil(driver, cancelButton, 30);
			cancelButton.click();
			logger.info("Cancel Button: Clicked");
			return true;
		} else {
			logger.info("Cancel Button: Not Clicked");
			return false;
		}

	}

	private boolean isSaveNewButtonSeen() {

		WaitUtilities.waitUntil(driver, saveNewButton, 30);
		if (saveNewButton.isDisplayed()) {
			logger.info("Save New Button: Visible");
			return true;
		} else {
			logger.info("Save New Button: Not Visible");
			return false;
		}

	}

	public boolean clickSaveNewButton() {

		if (isSaveNewButtonSeen()) {
			WaitUtilities.waitUntil(driver, saveNewButton, 30);
			saveNewButton.click();
			logger.info("Save New Button: Clicked");
			return true;
		} else {
			logger.info("Save New Button: Not Clicked");
			return false;
		}

	}

	private boolean isGoButtonSeen() {

		WaitUtilities.waitUntil(driver, goButton, 30);
		if (goButton.isDisplayed()) {
			logger.info("Go Button: Visible");
			return true;
		} else {
			logger.info("Go Button: Not Visible");
			return false;
		}

	}

	public boolean clickGoButton() {

		if (isGoButtonSeen()) {
			WaitUtilities.waitUntil(driver, goButton, 30);
			goButton.click();
			logger.info("GoButton: Clicked");
			return true;
		} else {
			logger.info("Go Button: Not Clicked");
			return false;
		}

	}

	private boolean isSaveViewButtonSeen() {

		WaitUtilities.waitUntil(driver, saveViewButton, 30);
		if (saveViewButton.isDisplayed()) {
			logger.info("Save View Button: Visible");
			return true;
		} else {
			logger.info("Save View Button: Not Visible");
			return false;
		}

	}

	public boolean clickSaveViewButton() {

		if (isSaveViewButtonSeen()) {
			WaitUtilities.waitUntil(driver, saveViewButton, 30);
			saveViewButton.click();
			logger.info("Save View Button: Clicked");
			return true;
		} else {
			logger.info("Save View Button: Not Clicked");
			return false;
		}

	}

	private boolean isCancelViewButtonSeen() {

		WaitUtilities.waitUntil(driver, cancelViewButton, 30);
		if (cancelViewButton.isDisplayed()) {
			logger.info("Cancel View Button: Visible");
			return true;
		} else {
			logger.info("Cancel View Button: Not Visible");
			return false;
		}

	}

	public boolean clickCancelViewButton() {

		if (isCancelViewButtonSeen()) {
			WaitUtilities.waitUntil(driver, cancelViewButton, 30);
			cancelViewButton.click();
			logger.info("Cancel View Button: Clicked");
			return true;
		} else {
			logger.info("Cancel View Button: Not Clicked");
			return false;
		}

	}

//	--------------------------------------------------

	private boolean isEditViewLinkSeen() {

		WaitUtilities.waitUntil(driver, editView, 30);
		if (editView.isDisplayed()) {
			logger.info("Edit View Link: Visible");
			return true;
		} else {
			logger.info("Edit View Link: Not Visible");
			return false;
		}
	}

	public boolean clickEditViewLink() {

		if (isEditViewLinkSeen()) {
			WaitUtilities.waitUntil(driver, editView, 30);
			editView.click();
			logger.info("Edit View Link: Clicked");
			return true;
		} else {
			logger.info("Edit View Link: Not Clicked");
			return false;
		}
	}

	private boolean isCreateNewViewLinkSeen() {

		WaitUtilities.waitUntil(driver, createNewView, 30);
		if (createNewView.isDisplayed()) {
			logger.info("Create New View Link: Visible");
			return true;
		} else {
			logger.info("Create New View Link: Not Visible");
			return false;
		}
	}

	public boolean clickCreateNewViewLink() {

		if (isCreateNewViewLinkSeen()) {
			WaitUtilities.waitUntil(driver, createNewView, 30);
			createNewView.click();
			logger.info("Create New View Link: Clicked");
			return true;
		} else {
			logger.info("Create New View Link: Not Clicked");
			return false;
		}
	}

	private boolean isViewDropDownLinkSeen() {

		WaitUtilities.waitUntil(driver, viewDropDown, 30);
		if (viewDropDown.isDisplayed()) {
			logger.info("View Drop Down Link: Visible");
			return true;
		} else {
			logger.info("View Drop Down Link: Not Visible");
			return false;
		}
	}

	private boolean clickViewDropDownLink() {

		if (isViewDropDownLinkSeen()) {
			WaitUtilities.waitUntil(driver, viewDropDown, 30);
			viewDropDown.click();
			logger.info("View Drop Down Link: Clicked");
			return true;
		} else {
			logger.info("View Drop Down Link: Not Clicked");
			return false;
		}
	}

	public boolean selectViewOption(String viewNameOption) {

		boolean isViewOptionFound = false;

		if (clickViewDropDownLink()) {
			for (int i = 0; i < viewOptions.size(); i++) {

				WaitUtilities.waitUntil(driver, viewOptions.get(i), i);
				String currentOption = viewOptions.get(i).getText();

				if (viewNameOption.equals(currentOption)) {
					logger.info("Fetch Success: view option: " + currentOption);
					viewOptions.get(i).click();
					isViewOptionFound = true;
				}
			}
		}

		return isViewOptionFound;
	}

	public boolean verifyAllViewOption(String[] allViewNameOptions) {

		boolean allViewOptionsVerified = false;
		int count = allViewNameOptions.length;

		if (clickViewDropDownLink()) {
			int j = 0, countVerified = 0;
			while (j < count) {

				String expectedViewName = allViewNameOptions[j++];

				for (int i = 0; i < viewOptions.size(); i++) {

					WaitUtilities.waitUntil(driver, viewOptions.get(i), i);
					String currentOption = viewOptions.get(i).getText();

					if (expectedViewName.equals(currentOption)) {
						logger.info(expectedViewName + " : verified");
						countVerified++;
					}
				}
			}
			if (countVerified == count) {
				logger.info("All standard views : verified");
				allViewOptionsVerified = true;
			} else {
				logger.info("All standard views : not present");
				allViewOptionsVerified = false;
			}
		}

		return allViewOptionsVerified;
	}

	private boolean isFilterOverviewDropDownLinkSeen() {

		WaitUtilities.waitUntil(driver, filterOverviewOptions.get(0), 30);
		if (filterOverviewOptions.get(0).isDisplayed()) {
			logger.info("Filter Overview Drop Down Link: Visible");
			return true;
		} else {
			logger.info("Filter Overview Drop Down Link: Not Visible");
			return false;
		}
	}

	private boolean clickFilterOverviewDropDownLink() {

		if (isFilterOverviewDropDownLinkSeen()) {
			WaitUtilities.waitUntil(driver, filterOverviewOptions.get(0), 30);
			filterOverviewOptions.get(0).click();
			logger.info("Filter Overview Drop Down Link: Clicked");
			return true;
		} else {
			logger.info("Filter Overview Drop Down Link: Not Clicked");
			return false;
		}
	}

	public boolean selectFilterOverviewOption(String viewNameOption) {

		boolean isFilterOptionFound = false;

		if (clickFilterOverviewDropDownLink()) {
			for (int i = 0; i < filterOverviewOptions.size(); i++) {

				WaitUtilities.waitUntil(driver, filterOverviewOptions.get(i), i);
				String currentOption = filterOverviewOptions.get(i).getText();

				if (viewNameOption.equals(currentOption)) {
					logger.info("Fetch Success: Filter Overview option: " + currentOption);
					filterOverviewOptions.get(i).click();
					isFilterOptionFound = true;
				}
			}
		}

		return isFilterOptionFound;
	}

	private boolean isCreateNewMenuDropDownLinkSeen() {

		WaitUtilities.waitUntil(driver, createNewMenu.get(0), 30);
		if (createNewMenu.get(0).isDisplayed()) {
			logger.info("Create New Menu Drop Down Link: Visible");
			return true;
		} else {
			logger.info("Create New Menu Drop Down Link: Not Visible");
			return false;
		}
	}

	private boolean clickCreateNewMenuDropDownLink() {

		if (isCreateNewMenuDropDownLinkSeen()) {
			WaitUtilities.waitUntil(driver, createNewMenu.get(0), 30);
			createNewMenu.get(0).click();
			logger.info("Create New Menu Drop Down Link: Clicked");
			return true;
		} else {
			logger.info("Create New Menu Drop Down Link: Not Clicked");
			return false;
		}
	}

	public boolean selectCreateNewMenuOption(String viewNameOption) {

		boolean isFilterOptionFound = false;

		if (clickCreateNewMenuDropDownLink()) {
			for (int i = 0; i < createNewMenu.size(); i++) {

				WaitUtilities.waitUntil(driver, createNewMenu.get(i), i);
				String currentOption = createNewMenu.get(i).getText();

				if (viewNameOption.equals(currentOption)) {
					logger.info("Fetch Success: Create New Menu option: " + currentOption);
					createNewMenu.get(i).click();
					isFilterOptionFound = true;
				}
			}
		}

		return isFilterOptionFound;
	}

	private boolean isPageHeaderLinkSeen() {

		WaitUtilities.waitUntil(driver, pageHeader, 30);
		if (pageHeader.isDisplayed()) {
			logger.info("Page Header Link: Visible");
			return true;
		} else {
			logger.info("Page Header Link: Not Visible");
			return false;
		}
	}

	public String getPageHeaderName() {

		if (isPageHeaderLinkSeen()) {
			WaitUtilities.waitUntil(driver, pageHeader, 30);
			String pageName = pageHeader.getText();
			logger.info("Page Header : read: " + pageName);
			return pageName;
		} else {
			logger.info("Page Header : Null");
			return null;
		}
	}

	private boolean isPageTitleLinkSeen() {

		WaitUtilities.waitUntil(driver, pageTitle, 30);
		if (pageTitle.isDisplayed()) {
			logger.info("Page Title Link: Visible");
			return true;
		} else {
			logger.info("Page Title Link: Not Visible");
			return false;
		}
	}

	public String getPageTitleName() {

		if (isPageTitleLinkSeen()) {
			WaitUtilities.waitUntil(driver, pageTitle, 30);
			String pageTitleName = pageTitle.getText();
			logger.info("Page Title : read: " + pageTitleName);
			return pageTitleName;
		} else {
			logger.info("Page Title : Null");
			return null;
		}
	}

}
