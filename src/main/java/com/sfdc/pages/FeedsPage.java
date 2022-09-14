package com.sfdc.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sfdc.utilities.WaitUtilities;

public class FeedsPage extends BaseSFDCPage{

	public static WebDriver driver;
	
	public FeedsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//a[@title='Post']")
	public WebElement postFeed;

	@FindBy (xpath = "//a[@title='File']")
	public WebElement fileFeed;

	@FindBy (xpath = "//iframe[@class='cke_wysiwyg_frame cke_reset']")
	public WebElement postTextFrame;

	@FindBy (xpath = "/html/body")
	public WebElement textArea;

	@FindBy (xpath = "//span[@class='feeditemtext cxfeeditemtext']/p")
	public List<WebElement> latestPost;

	@FindBy (id = "publishersharebutton")
	public WebElement shareButton;
	
	@FindBy (css = "#chatterUploadFileAction")
	public WebElement uploadButton;
	
	@FindBy (css = "#chatterFile")
	public WebElement chooseFileButton;
	
	
	public String getLastPost() {
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(latestPost.get(0).isDisplayed()) {
			logger.info("Recent post is available");
			return latestPost.get(0).getText();
		}else {
			logger.info("New post not displayed");
			return null;
		}
	}

	public boolean shareAPost(WebDriver driver, String postMessage) {

		boolean isPosted = false;

		/*
		 * Click on Post link, Enter the <text> to post in the post text area Click on
		 * share button
		 */
		WaitUtilities.waitUntil(driver, postFeed, 30);
		if (postFeed.isDisplayed()) {
			WaitUtilities.waitUntil(driver, postFeed, 30);
			postFeed.click();
			logger.info("Making a Post");

			WaitUtilities.waitUntil(driver, postTextFrame, 30);
			driver.switchTo().frame(postTextFrame);
			logger.info("Switching to Post Frame");

			WaitUtilities.waitUntil(driver, textArea, 30);
			if (textArea.isDisplayed()) {

				textArea.sendKeys(postMessage);
				logger.info("Posted Message: " + postMessage);
				isPosted = true;

				// share button outside the frame hence need to switch back the frame
				driver.switchTo().defaultContent();
				WaitUtilities.waitUntil(driver, shareButton, 30);
				if (shareButton.isDisplayed()) {
					shareButton.click();
					logger.info("Post shared");
				}
			}
		}
		return isPosted;
	}
	
	public boolean uploadAFile(WebDriver driver, String filepath) throws InterruptedException {

		boolean fileUploaded = false;
		
		/*
		 * Click on the file link Click on "upload a file from computer" button.
		 * Click on choose file button Select a file to be uploaded Click open button.
		 * 
		 */
		WaitUtilities.waitUntil(driver, fileFeed, 30);
		if(fileFeed.isDisplayed()) {

			WaitUtilities.waitUntil(driver, fileFeed, 30);
			fileFeed.click();
			logger.info("Preparing to upload file");
			
			WaitUtilities.waitUntil(driver, uploadButton, 30);
			uploadButton.click();
			logger.info("UpLoad button clicked");
			
			WaitUtilities.waitUntil(driver, chooseFileButton, 30);
			chooseFileButton.sendKeys(filepath);
			logger.info("Choose file button clicked");
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			WaitUtilities.waitUntil(driver, shareButton, 100);
			shareButton.click();
			logger.info("File share button clicked: File share complete");
			
			fileUploaded = true;
			
		}
		return fileUploaded;

	}
	

	
}
