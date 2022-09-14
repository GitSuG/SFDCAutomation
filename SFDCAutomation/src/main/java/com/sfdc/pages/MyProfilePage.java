package com.sfdc.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sfdc.utilities.WaitUtilities;

/**
 * @author ghorpade
 *
 */
/**
 * @author ghorpade
 *
 */
public class MyProfilePage extends BaseSFDCPage {

	public static WebDriver driver;

	public MyProfilePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		feed = new FeedsPage(driver);
	}

	FeedsPage feed;

	@FindBy(xpath = "//div[@class='editPen']/a[@class='contactInfoLaunch editLink']")
	public WebElement editProfileSymbol;

	@FindBy(id = "aboutTab")
	public WebElement aboutTab;

	@FindBy(id = "contactTab")
	public WebElement contactTab;

	@FindBy(xpath = "//input[@id ='lastName']")
	public WebElement lastName;

	@FindBy(xpath = "//input[@value='Save All']")
	public WebElement saveAllFrameButton;

	@FindBy(xpath = "//div[@class='photoUploadSection']/a[@id='uploadLink']")
	public WebElement addPhotoLink;

	@FindBy(id = "uploadPhotoContentId")
	public WebElement uploadPhotoFrame;

	@FindBy(xpath = "//input[@id='j_id0:uploadFileForm:uploadInputFile']")
	public WebElement choosePhotoButton;

	@FindBy(xpath = "//input[@id='j_id0:uploadFileForm:save']")
	public WebElement selectPhotoButton;

	@FindBy(xpath = "//input[@id='j_id0:j_id7:save']")
	public WebElement savePhotoButton;

	/**
	 * 
	 * @param driver
	 * @param editSectionSelected
	 * @return
	 */
	public boolean clickProfileEditLink() {

		boolean isEditable = false;

		WaitUtilities.waitUntil(driver, editProfileSymbol, 30);
		if (editProfileSymbol.isDisplayed()) {
			editProfileSymbol.click();
			driver.switchTo().frame("contactInfoContentId");
			isEditable = true;
			logger.info("Edit Link is active");
		} else {
			logger.info("Edit Link not active");
		}

		return isEditable;

	}

	/**
	 * @param message
	 */
	public boolean makeAPost(String message) {
		return feed.shareAPost(driver, message);
	}

	public String verifyNewPost() {
		return feed.getLastPost();
	}

	/**
	 * @param filePath
	 * @throws InterruptedException
	 */
	public boolean uplaodAFile(String filePath) throws InterruptedException {
		return feed.uploadAFile(driver, filePath);
	}

	/**
	 * @param photoPath
	 * @return
	 */
	public boolean uploadProfilePicture(String photoPath) {

		boolean photoUploaded = false;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		/*
		 * Hover the mouse on My Profile Photo image, Add photo link appears. Click on
		 * the link to upload a photo. Click on Choose file button and select the image
		 * to upload. Click save picture button.
		 */
		WaitUtilities.waitUntil(driver, addPhotoLink, 30);
		if (addPhotoLink.isDisplayed()) {

			logger.info("Updating Profile Picture...");
			WebDriverWait waitForPhoto = new WebDriverWait(driver, Duration.ofSeconds(30));
			waitForPhoto.until(ExpectedConditions.elementToBeClickable(addPhotoLink));

			Actions action = new Actions(driver);
			action.moveToElement(addPhotoLink).build().perform();

			addPhotoLink.click();

			WaitUtilities.waitUntil(driver, uploadPhotoFrame, 30);
			driver.switchTo().frame(uploadPhotoFrame);

			logger.info("Choosing Profile Picture...");
			waitForPhoto.until(ExpectedConditions.elementToBeClickable(choosePhotoButton));
			choosePhotoButton.sendKeys(photoPath);

			logger.info("Uploading Profile Picture...");
			WaitUtilities.waitUntil(driver, selectPhotoButton, 30);
			selectPhotoButton.click();
			
			logger.info("Saving Profile Picture...");
			WaitUtilities.waitUntil(driver, savePhotoButton, 30);
			savePhotoButton.click();
			

			logger.info("Profile picture upload complete");
			photoUploaded = true;
		}
		return photoUploaded;
	}

	/**
	 * @param lastNameText
	 * @return
	 */
	public boolean editProfilelastName(String lastNameText) {

		boolean editComplete = false;
		String editTabSelected = "About";

		boolean frameTabActive = selectProfileEditTab(editTabSelected);

		if (frameTabActive)
			if (isLastnameSeen()) {
				enterLastName(lastNameText);
				if (saveProfileEditSection())
					editComplete = true;
			}

		return editComplete;

	}

	private boolean selectProfileEditTab(String editSectionSelected) {

		boolean isoptionSelected = false;
		WebElement option;

		WaitUtilities.waitUntil(driver, aboutTab, 30);
		if (editSectionSelected.equals(aboutTab.getText())) {
			option = aboutTab;
			logger.info("About tab is selected");
		} else {
			WaitUtilities.waitUntil(driver, contactTab, 30);
			option = contactTab;
			logger.info("Contact tab is selected");
		}

		if (option.isDisplayed()) {
			option.click();
			isoptionSelected = true;
			logger.info("Edit Profile: " + option.getText() + " Clicked");
		} else {
			logger.info("Options available for Selection: About / Contact");
		}

		return isoptionSelected;

	}

	public boolean isLastnameSeen() {
		WaitUtilities.waitUntil(driver, lastName, 30);
		if (lastName.isDisplayed()) {
			logger.info("Last name field visible");
			return true;
		} else {
			logger.info("Last name filed not visible");
			return false;
		}
	}

	public void enterLastName(String lastNameText) {

		WaitUtilities.waitUntil(driver, lastName, 30);
		lastName.clear();
		lastName.sendKeys(lastNameText);
		logger.info("Update success: lastname field updated");
	}

	/**
	 * 
	 * @param driver
	 * @param lastName
	 * @return
	 */
	private boolean saveProfileEditSection() {

		boolean editSaved = false;

		WaitUtilities.waitUntil(driver, saveAllFrameButton, 30);
		if (saveAllFrameButton.isDisplayed()) {
			logger.info("Edit Profile: Section Changes Saved");
			saveAllFrameButton.click();
			editSaved = true;
		} else
			logger.info("Edit Profile: Section changes NOT saved");

		return editSaved;
	}

}
