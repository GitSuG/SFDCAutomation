package com.sfdc.pkg;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sfdc.utilities.Utilities;
import com.sfdc.utilities.UtilityLogin;

public class Leads extends UtilityLogin{


	@BeforeMethod
	public void browseLeads() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)) ;
		
		UtilityLogin.T00_LoginToSFDC();

		System.out.println("Leads Tab.... Browsing Started");
		String blockName = "leadBlock";
		String expectedPage = "Leads";
		Assert.assertTrue(Utilities.getTabHome(driver, blockName, expectedPage), "Error: Page mismatch: Expected: [Leads]");		

	}
	

	public void TC20_testLeads() {
		
	}
	
	public void TC21_viewLeads() {
		
	}
	
	public void TC22_goToSelectedLeadsView() {
		
	}
	
	public void TC23_todaysLeads() {
		
	}
	
	@Test
	public void TC24_createNewLead() throws InterruptedException {
		
		String lastNameEntry = "Gatsby";
		String companyNameEntry = "Burmingham Paints";
		String expectedName = "New Lead";
//		String leadStatusEntry = "Working - Contacted";
		
		/*
		 *  	 Click New Button: New Lead
		 */
		Assert.assertTrue(Utilities.clickNewButton(driver, expectedName),
				"Error New Lead: New Button Failed ");
		/*
		 * 		 Enter <New Lead lastName>
		 * 		 Enter <New Lead companyName>
		 */
		WebElement lastName = driver.findElement(By.id("name_lastlea2"));
		lastName.sendKeys(lastNameEntry);
		
		WebElement companyName = driver.findElement(By.id("lea3"));
		companyName.sendKeys(companyNameEntry);

		/*
		 *  Mandatory field missed in testcase - Default value used
		 */
//		WebElement leadStatus = driver.findElement(By.id("lea13"));
//		leadStatus.sendKeys(leadStatusEntry);
		
		/*
		 *  	 Click Save Button: Save Lead
		 */
		Assert.assertTrue(Utilities.clickSaveButton(driver, expectedName),
				"Error New Lead: [" + lastNameEntry +" ]"+" Save Button Failed ");
		
		
	}
	
	
	

}
