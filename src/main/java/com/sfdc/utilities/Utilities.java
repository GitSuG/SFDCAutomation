package com.sfdc.utilities;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * @author ghorpade
 *
 */
public class Utilities {

	/**
	 * Fluent Wait
	 * 
	 * @param driver
	 * @param xpathString
	 * @param timeOut
	 * @param pollFrequency
	 * @return
	 */
	public static WebElement fluentWait(WebDriver driver, final String xpathString, int timeOut, int pollFrequency) {

		Wait<WebDriver> waitFluent = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollFrequency)).ignoring(NoSuchElementException.class);

		WebElement element = waitFluent.until(new Function<WebDriver, WebElement>() {

			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(xpathString));
			}

		});
		return element;
	}

	/**
	 * 
	 * Check if the WebElement is present in a List of WebElements
	 * 
	 * @param listOptions
	 * @param searchString
	 * @param assertMessage
	 * @return
	 */
	public static boolean isWebElementPresent(List<WebElement> listOptions, String searchString, String assertMessage) {
		boolean viewAddVerified = false;

		for (int i = 1; i < listOptions.size(); i++) {

			String option = listOptions.get(i).getText();
			if (option.equals(searchString)) {
				System.out.println(option + " ...Successfully Verified");
				Assert.assertEquals(option, searchString, assertMessage);
				viewAddVerified = true;
				break;
			}
		}
		return viewAddVerified;
	}

	/**
	 * 
	 * Get the index of the WebElement in a List of WebElements
	 * 
	 * @param listOptions
	 * @param searchString
	 * @param assertMessage
	 * @return
	 */
	public static int findWebElementIndex(List<WebElement> listOptions, String searchString, String assertMessage) {

		int index = -1;
		boolean viewAddVerified = false;

		for (int i = 1; i < listOptions.size(); i++) {

			String option = listOptions.get(i).getText();
			if (option.equals(searchString)) {
				System.out.println(option + " ...Successfully Identified");
				Assert.assertEquals(option, searchString, assertMessage);
				index = i;
				viewAddVerified = true;
				break;
			}
		}

		if (viewAddVerified)
			return index;
		else
			return -1;
	}

	/**
	 * 
	 * 
	 * Replace the WebElement in a List of WebElements with another
	 * 
	 * @param listOptions
	 * @param oldString
	 * @param newString
	 * @param assertMessage
	 * @return
	 */
	public static boolean replaceWebElement(List<WebElement> listOptions, String oldString, String newString,
			String assertMessage) {

		boolean viewEditVerified = false;

		for (int i = 1; i < listOptions.size(); i++) {

			String option = listOptions.get(i).getText();
			if (option.equals(newString)) {
				System.out.println("Old String: " + oldString + "\nNew String: " + option);
				System.out.println(" Replace Successful ");
				Assert.assertEquals(option, newString, assertMessage);
				viewEditVerified = true;
				break;
			}
		}

		return viewEditVerified;
	}

	/**
	 * Open the Home Tab Click on the + (All Tabs) button Choose the Tab using
	 * blockName field by clicking on the WebElement Retrieve and Verify if the
	 * expected Home Page is displayed
	 * 
	 * @param driver
	 * @param blockName    - Name referring the expected Tab to be Opened
	 * @param expectedName - Page expected after clicking to open home page
	 * @return boolean = true --> Correct Home page successfully displayed = false
	 *         --> Home page not displayed
	 */
	public static boolean getTabHome(WebDriver driver, String blockName, String expectedPage) {
		boolean isHomeLoaded = false;
		/*
		 * Click on + link on the home page
		 */
		driver.findElement(By.id("AllTab_Tab")).click();

		driver.findElement(By.xpath("//a[@class='listRelatedObject " + blockName + " title']")).click();

		// ------------------------------------------------------------
		// Retrieve Page Name
		// ------------------------------------------------------------
		fluentWait(driver, "//h1[@class='pageType']", 30, 5);
		String actualPage = driver.findElement(By.xpath("//h1[@class='pageType']")).getText();

		// ------------------------------------------------------------
		// Verification of Home Page Loading
		// ------------------------------------------------------------

		if (actualPage.equals(expectedPage)) {
			System.out.println("Browsing... " + actualPage + " Page ");
			isHomeLoaded = true;
		} else
			System.err.println("Page Expected: " + expectedPage);

		return isHomeLoaded;
	}

	/**
	 * New button for New Record Entry Click on the NEW button when visible Verify
	 * if a new record entry page is displayed with expected values
	 * 
	 * @param driver
	 * @param expectedName - Page expected after clicking NEW button to create a
	 *                     record
	 * @return boolean = true --> New record page successfully displayed = false -->
	 *         Entry page not displayed
	 */
	public static boolean clickNewButton(WebDriver driver, String expectedName) {

		boolean isNewSuccess = false;
		/*
		 * Click on the New button to create new entry
		 */

		WebElement newButton = driver.findElement(By.xpath("//td[@class='pbButton']/input[@value=' New ']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(newButton));
		newButton.click();

		String actualName = driver.findElement(By.xpath("//h2[@class='pageDescription']")).getText();

		// ------------------------------------------------------------
		// Verification of Accounts Page Activity: New Account
		// ------------------------------------------------------------
		if (actualName.equals(expectedName)) {
			System.out.println("Editing... " + actualName + " Page ");
			isNewSuccess = true;
		} else
			System.err.println("Page Expected: " + expectedName);

		return isNewSuccess;
	}

	/**
	 * Save button for New Record Entry Click on the SAVE button when visible Verify
	 * if a new record is created and page is displayed with expected values
	 * 
	 * @param driver
	 * @param expectedName - Page expected after saving new record
	 * @return boolean = true --> Entry successfully saved = false --> Entry not
	 *         saved
	 */
	public static boolean clickSaveButton(WebDriver driver, String expectedName) {

		boolean isSaveSuccess = false;

		/*
		 * Step 3: Click on save button
		 */

		WebElement saveButton = driver.findElement(By.xpath("//td[@id='topButtonRow']/input[@value=' Save ']"));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(saveButton));
		saveButton.click();

		String actualName = driver.findElement(By.xpath("//h2[@class='topName']")).getText();

		if (actualName.equals(expectedName)) {
			isSaveSuccess = true;
			System.out.println(expectedName + " ...Save Successful");
		} else
			System.err.println(expectedName + " ...Save Unsuccessful");

		return isSaveSuccess;
	}

	/**
	 * Verify Options for User Navigation Menu Click on the User Navigation Menu and
	 * Display all the options present Verify if the options displayed are same as
	 * expected values
	 * 
	 * @param driver
	 * @return boolean = true --> All options match expected values 
	 * 				   = false --> If one(or more) option is not a match
	 */
	public static boolean verifyUserNavOptions(WebDriver driver) {

		boolean optionPresent = true;

		String[] expectedValues = { "My Profile", "My Settings", "Developer Console", "Switch to Lightning Experience",
				"Logout" };

		WebElement userNav = driver.findElement(By.id("userNav"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(userNav));
		userNav.click();

		for (int i = 1; i < expectedValues.length; i++) {
			String actualValue = driver.findElement(By.xpath("//div[@id='userNav-menuItems']/a[" + (i + 1) + "]"))
					.getText();
			if (actualValue.equals(expectedValues[i])) {
				System.out.println("'" + expectedValues[i] + "' --verified successfully");

			} else {
				System.err.println("'" + expectedValues[i] + "' --NOT verified successfully");
				optionPresent = false;
			}
		}

		return optionPresent;

	}

	/**
	 * 
	 * @param driver
	 * @param option
	 * @return
	 */
	public static boolean findOptionInUserNavMenu(WebDriver driver, String option) {

		boolean optionPresent = false;

		/*
		 * Step 2: Click option from user menu
		 */
		WebElement userNavMenu = driver.findElement(By.id("userNavMenu"));
		WebElement menuItemSelected = userNavMenu
				.findElement(By.xpath("//div[@class='mbrMenuItems']/a[@title='" + option + "']"));

		// ------------------------------------------------------------
		// Verification of Selected Activity
		// ------------------------------------------------------------
		if (menuItemSelected.isDisplayed()) {
			System.out.println("User Navigation Menu: " + menuItemSelected.getText() + " --Click Activity Completed");
			optionPresent = true;
			menuItemSelected.click();
		} else
			System.err.println(option + " -- Activity Error");

		return optionPresent;
	}

	/**
	 * 
	 * @param driver
	 * @param editSection
	 * @return
	 */
	public static boolean selectProfileEditFrame(WebDriver driver, String editSection) {

		boolean optionSelected = false;

		String option = "aboutTab";
		if (!editSection.toLowerCase().equals("about"))
			option = "contactTab";

		WebElement editLink = driver
				.findElement(By.xpath("//div[@class='editPen']/a[@class='contactInfoLaunch editLink']"));

		if (editLink.isDisplayed()) {
			editLink.click();
			driver.switchTo().frame("contactInfoContentId");
			WebElement optionElement = driver.findElement(By.id(option));
			if (optionElement.isDisplayed()) {
				optionElement.click();
				optionSelected = true;
				System.out.println("Edit Profile: " + option + " selected");
			} else {
				System.err.println("Options available for Selection: About / Contact");
			}
		} else {
			System.err.println("Edit Link not active");
		}

		return optionSelected;

	}

	/**
	 * 
	 * @param driver
	 * @param lastName
	 * @return
	 */
	public static boolean editSaveProfileAboutSection(WebDriver driver, String lastName) {

		boolean editComplete = false;
		WebElement lastnameElement = driver.findElement(By.xpath("//input[@id ='lastName']"));
		if (lastnameElement.isDisplayed()) {
			lastnameElement.clear();
			lastnameElement.sendKeys(lastName);
			WebElement saveButton = driver.findElement(By.xpath("//input[@value='Save All']"));
			if (saveButton.isDisplayed()) {
				System.out.println("Edit Profile: About Section Changes Saved");
				saveButton.click();
				editComplete = true;
			} else
				System.err.println("Edit Profile: About Section changes NOT saved");
		} else
			System.err.println(lastName + " Field not visible");

		return editComplete;
	}

	public static boolean shareAPost(WebDriver driver, String postMessage) {

		String feedItemType = "Post";
		boolean isPosted = false;

		/*
		 * Click on Post link, Enter the <text> to post in the post text area Click on
		 * share button
		 */
		WebElement feedItemSelected = driver
				.findElement(By.xpath("//a[@title='" + feedItemType + "']/span[@class='publisherattachtext ']"));
		if (feedItemSelected.isDisplayed()) {
			feedItemSelected.click();
			System.out.println("Making a Post");

			WebElement postTextFrame = driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']"));
			driver.switchTo().frame(postTextFrame);
			System.out.println("Switching to Post Frame");

			WebElement postTextArea = driver.findElement(By.xpath("/html/body"));
			if (postTextArea.isDisplayed()) {

				postTextArea.sendKeys(postMessage);
				System.out.println("Posted Message: " + postMessage);
				isPosted = true;

				// share button outside the frame hence need to switch back the frame
				driver.switchTo().defaultContent();
				WebElement shareButton = driver.findElement(By.id("publishersharebutton"));
				if (shareButton.isDisplayed()) {
					shareButton.click();
					System.out.println("Post shared");
				}
			}
		}
		return isPosted;
	}
	
	public static boolean uploadAFile(WebDriver driver, String filepath) throws InterruptedException {

		boolean fileUploaded = false;
		String feedItemType = "File";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		/*
		 * Step 6: Click on the file link Click on "upload a file from computer" button.
		 * Click on choose file button Select a file to be uploaded Click open button.
		 * 
		 */
		WebElement feedItemSelected = driver.findElement(By.xpath("//a[@title='" + feedItemType + "']/span[@class='publisherattachtext ']"));
		if(feedItemSelected.isDisplayed()) {

			System.out.println("Preparing to upload file");
			
			
			WebElement upLoadButton = driver.findElement(By.cssSelector("#chatterUploadFileAction"));
			wait.until(ExpectedConditions.elementToBeClickable(upLoadButton));
			Thread.sleep(5000);
			upLoadButton.click();
			System.out.println("UpLoad button clicked");
			
			WebElement chooseFileButton = driver.findElement(By.cssSelector("#chatterFile"));
			wait.until(ExpectedConditions.elementToBeClickable(chooseFileButton));
			Thread.sleep(5000);
			chooseFileButton.sendKeys(filepath);
			System.out.println("Choose file button clicked");
			
			WebElement shareButton = driver.findElement(By.cssSelector("#publishersharebutton"));
			wait.until(ExpectedConditions.elementToBeClickable(shareButton));
			Thread.sleep(5000);
			shareButton.click();
			System.out.println("File share button clicked: File share complete");
			
			fileUploaded = true;
			
		}
		return fileUploaded;

	}
	
	/*
	   driver.findElement(By.xpath("//a[@title='File']/span[@class='publisherattachtext ']")).click();
		driver.findElement(By.xpath("//a[@id='chatterUploadFileAction']")).click();
		driver.findElement(By.xpath("//input[@id='hiddenFileBtn']")).click();
		driver.findElement(By.xpath("//input[@id='chatterFile']")).sendKeys(filepath);
		driver.findElement(By.xpath("//input[@id='publishersharebutton']"));	
		driver.switchTo().frame(0);
	 */

	
	public static boolean uploadProfilePicture(WebDriver driver, String photoPath) {
		
		boolean photoUploaded = false;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		/*
		 * Step 7:	Hover the mouse on My Profile Photo image, Add photo link appears. 
		 * 			Click on the link to upload a photo. 
		 * 			Click on Choose file button and select the image to upload. 
		 * 			Crop the photo to fit the image.
		 */
		WebElement addPhoto = driver.findElement(By.id("uploadLink"));
		if(addPhoto.isDisplayed()) {
			
			System.out.println("Updating Profile Picture...");
			addPhoto.click();
			
			WebElement uploadPhotoFrame = driver.findElement(By.id("uploadPhotoContentId"));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(uploadPhotoFrame));
			driver.switchTo().frame(uploadPhotoFrame);
			
			System.out.println("Choosing Profile Picture...");
			WebElement chooseButton = driver.findElement(By.xpath("//input[@id='j_id0:uploadFileForm:uploadInputFile']"));
			wait.until(ExpectedConditions.elementToBeClickable(chooseButton));
			chooseButton.sendKeys(photoPath);
			
			System.out.println("Uploading Profile Picture...");
			WebElement saveButton = driver.findElement(By.xpath("//input[@id='j_id0:uploadFileForm:save']"));
			wait.until(ExpectedConditions.elementToBeClickable(saveButton));
			saveButton.click();
			
			System.out.println("Profile picture upload complete");
			photoUploaded = true;
		}
		return photoUploaded;
	}
}
