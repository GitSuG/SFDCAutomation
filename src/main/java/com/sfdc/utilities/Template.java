package com.sfdc.utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Template {

	/*
	 * 
	 */
	public static boolean verifyUserMenuItems(WebDriver driver) {
		String[] expectedUserMenuValues = { "My Profile", "My Settings", "Developer Console",
				"Switch to Lightning Experience", "Logout" };

		boolean isOptionVerified = true;

		List<WebElement> listOfUserMenuElements = driver.findElements(By.xpath("//div[@id='userNav-menuItems']/a"));

		for (int i = 0; i < listOfUserMenuElements.size(); i++) {
			String actualValue = listOfUserMenuElements.get(i).getText();
			if (actualValue.equals(expectedUserMenuValues[i])) {
				System.out.println(expectedUserMenuValues[i] + " is verified successfully");
			} else {
				System.out.println(expectedUserMenuValues[i] + " is not verified");
				isOptionVerified = false;
			}
		}

		return isOptionVerified;
	}

	/*
	 * 
	 */
	public static boolean selectOptionInUserMenu(WebDriver driver, String option) {

		boolean isOptionSelected = false;
		WebElement userMenuOption = driver.findElement(By.xpath("//[text()=" + option + "]"));
		if (userMenuOption.isDisplayed()) {
			userMenuOption.click();
			isOptionSelected = true;
		} else
			System.out.println("Option" + option + " is not selected");

		return isOptionSelected;
	}

	public static boolean createAPost(WebElement textbox, String message, WebElement button) {

		boolean isPostCreated = false;
		if (textbox.isDisplayed()) {
			textbox.click();
			System.out.println("Clicked on the textbox");
			textbox.sendKeys(message);
			System.out.println("Entered the text in the textbox");
			if (button.isDisplayed()) {
				button.click();
				System.out.println("Clicked on the button");
				isPostCreated = true;
			}
		}
		return isPostCreated;
	}


}
