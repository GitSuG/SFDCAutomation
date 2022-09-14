package com.sfdc.pkg;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sfdc.utilities.Utilities;
import com.sfdc.utilities.UtilityLogin;

public class Opportunity  extends UtilityLogin {

	int min = 0;
    int max = 1000;
      
    //Generate random integer value from 50 to 100 
    int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
 
    @BeforeMethod
	public void browseOpportunity() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		UtilityLogin.T00_LoginToSFDC();

		System.out.println("Opportunity Tab.... Browsing Started");
		String blockName = "opportunityBlock";
		String expectedPage = "Opportunities";
		Assert.assertTrue(Utilities.getTabHome(driver, blockName, expectedPage),
				"Error: Page mismatch: Expected: [Opportunities]");

	}

	public void TC15_verifyOpportunities() {

	}

	
	public static void writeOpportunityEntry(String newOpportunityEntry, String accountNameEntry, String opportunityCloseDate,
			  String opportunityLeadSrcEntry, String opportunityStageEntry, 
			  String opportunityProbabilityEntry, String opportunityPriCampainSrcEntry) {
//		String newOpportunityEntry = "Opportunity 101";
//		String accountNameEntry = "";
//		String opportunityCloseDate = "";
//		String opportunityLeadSrcEntry = "";
//		String opportunityStageEntry = "";
//		String opportunityProbabilityEntry = "";
//		String opportunityPriCampainSrcEntry = "";
		/*
		 * 		 Enter 	<New Opportunity name>
		 * 				<Account Name>
		 * 				<Close Date>
		 * 				<Stage>
		 * 				<Probability>
		 * 				<Lead Source> 
		 * 				<Primary Campaign Source> 
		 * 
		 * [Examples: DM Campaign to Top Customers - Nov 12-23, 2001
		 *			  GC Product Webinar - Jan 7, 2002
		 *			  International Electrical Engineers Association Trade Show - Mar 4-5, 2002]
		 *
		 * [General: Website calls to action/ Speaking engagements / Conferences / Webinars / Purchased lists]
		 */
		WebElement opportunityID = driver.findElement(By.id("opp3"));
		opportunityID.sendKeys(newOpportunityEntry);
		
		WebElement accountName = driver.findElement(By.xpath("//input[@id='opp4']"));
		accountName.sendKeys(accountNameEntry);
		
		WebElement opprtunityCloseDate =driver.findElement(By.xpath("//input[@id='opp9']"));
		opprtunityCloseDate.sendKeys(opportunityCloseDate);
		
		WebElement opportunityStage = driver.findElement(By.xpath("//select[@id='opp11']"));
		opportunityStage.sendKeys(opportunityStageEntry);
		
		WebElement opportunityProbability = driver.findElement(By.xpath("//input[@id='opp12']"));
		opportunityProbability.clear();
		opportunityProbability.sendKeys(opportunityProbabilityEntry);
		
		WebElement opportunityLeadSrc = driver.findElement(By.xpath("//select[@id='opp6']"));
		opportunityLeadSrc.sendKeys(opportunityLeadSrcEntry);
		
		WebElement opportunityPriCampainSrc = driver.findElement(By.xpath("//input[@id='opp17']"));
		opportunityPriCampainSrc.sendKeys(opportunityPriCampainSrcEntry);
		
		
	}
	
 	@Test
	public void TC16_checkOpportunity() throws InterruptedException {
		
		String expectedName = "New Opportunity";
		/*
		 *  	 Click New Button: New Account
		 */
		Assert.assertTrue(Utilities.clickNewButton(driver, expectedName),
				"Error New Opportunity: New Button Failed ");
		
		/*
		 * 		 Enter 	<New Opportunity name>
		 * 				<Account Name>
		 * 				<Close Date>
		 * 				<Stage>
		 * 				<Probability>
		 * 				<Lead Source> 
		 * 				<Primary Campaign Source>
		 */
		

		String newOpportunityEntry = "Opportunity "+ random_int;
		System.out.println("Opportunity Entry: "+newOpportunityEntry);
		writeOpportunityEntry(newOpportunityEntry, 
							"Bear Welfare 2022", 
							"11/5/2022" ,
							"Partner Referral", 
							"Needs Analysis", 
							"70", 
							"GC Product Webinar - Jan 7, 2002");

		/*
		writeOpportunityEntry(newOpportunityEntry,
							  accountNameEntry,
							  opportunityCloseDate,
							  opportunityLeadSrcEntry,
							  opportunityStageEntry,
							  opportunityProbabilityEntry,
							  opportunityPriCampainSrcEntry);
		*/
		/*
		 *  	 Click Save Button: Save Opportunity
		 */
		Assert.assertTrue(Utilities.clickSaveButton(driver, expectedName),
				"Error New Opportunity: [" + newOpportunityEntry +" ]"+" Save Button Failed ");
		
	}

	public void TC17_checkOpportunityPipelineReport() {

	}

	public void TC18_checkStuckOpportunityReport() {

	}

	public void TC19_checkOpportunityQuaterlySummaryReport() {

	}

}
