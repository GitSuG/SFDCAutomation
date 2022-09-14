package com.sfdc.pkg;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sfdc.utilities.Utilities;
import com.sfdc.utilities.UtilityLogin;

public class Contacts extends UtilityLogin{

	

	@BeforeMethod
	public void browseContacts() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)) ;
		
		UtilityLogin.T00_LoginToSFDC();

		System.out.println("Contacts Tab.... Browsing Started");
		String blockName = "contactBlock";
		String expectedPage = "Contacts";
		Assert.assertTrue(Utilities.getTabHome(driver, blockName, expectedPage), "Error: Page mismatch: Expected: [Contacts]");
		
	}


	@Test
	public void TC25_createNewContact() throws InterruptedException {
		
		String lastNameEntry = "Sinha";
		String companyNameEntry = "Citi Banks";
		String expectedName = "New Contact";
		
		/*
		 *  	 Click New Button: New Contact
		 */
		Assert.assertTrue(Utilities.clickNewButton(driver, expectedName),
				"Error New Contact: New Button Failed ");
		/*
		 * 		 Enter <New Contact: lastName>
		 * 		 Enter <New Contact: companyName>
		 */
		WebElement lastName = driver.findElement(By.id("name_lastcon2"));
		lastName.sendKeys(lastNameEntry);
		
		WebElement companyName = driver.findElement(By.id("con4"));
		companyName.sendKeys(companyNameEntry);
		
		/*
		 *  	 Click Save Button: Save Contact
		 */
		Assert.assertTrue(Utilities.clickSaveButton(driver, expectedName),
				"Error New Contact: [" + lastNameEntry +" ]"+" Save Button Failed ");
		
	}
	
	public void TC26_createNewContactView() {
		
	}
	
	public void TC27_verifyNewContactView() {
		
	}
	
	public void TC28_verifyMyContacts() {
		
	}

	public void TC29_verifySelectedContact() {
		
	}
	
	public void TC30_verifyErrorCreateContact() {
		
	}
	

	public void TC31_verifyCancelCreateContact() {
		
	}
	
	public void TC32_verifySaveNewContact() {
		
	}
	
}
