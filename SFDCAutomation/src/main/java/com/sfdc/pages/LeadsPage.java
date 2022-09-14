package com.sfdc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsPage extends BaseSFDCPage {

	final String leads = "Leads";

	String[] stdLeadView = { "All Open Leads", "My Unread Leads", "Recently Viewed Leads", "Today's Leads" };
	private static WebDriver driver;

	public LeadsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		g = new GeneralButtonsLinks(driver);
//		newLead = new LeadsNewPage(driver);
	}

	HomePage hp;
	GeneralButtonsLinks g;
//	private LeadsNewPage newLead;

	@FindBy(xpath = "//*[@id=\"hotlist\"]/table/tbody/tr/td[1]/h3")
	public WebElement recentLeads;

	@FindBy(xpath = "//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div[1]/h3")
	public WebElement leadReports;

	@FindBy(xpath = "//*[@id=\"toolsContent\"]/tbody/tr/td[2]/div/div/h3")
	public WebElement leadSummary;

	@FindBy(xpath = "//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div[2]/h3")
	public WebElement leadTools;

	/**
	 * @return
	 */

	public boolean verifyLeadsPage() {
		
		  if(g.getPageTitleName().equals("Leads"))
			  return true;
		  else
			  return false;
	}

	/**
	 * 
	 */
	public boolean verifyStdLeadViews() {

		return g.verifyAllViewOption(stdLeadView);
	}

	/**
	 * 
	 */
	public boolean selectLeadsView(String leadsViewNameSelected) {

//		boolean isViewSelected = false;
//
//		if (g.viewDropDown.isDisplayed()) {
//			g.viewDropDown.click();
//			for (WebElement viewOption : g.viewOptions) {
//				if (viewNameSelected.equals(viewOption.getText())) {
//					viewOption.click();
//					isViewSelected = true;
//					System.out.println(viewOption.getText() + " selected");
//				}
//			}
//		} else
//			System.out.println("View Option not available");

		return g.selectViewOption(leadsViewNameSelected);
	}

	public boolean clickNewLeadButton() {

		return g.clickNewButton();
	}
	
	public boolean clickSaveLeadButton() {
		
		return g.clickSaveButton();
	}
	
	public boolean clickGoButtonInLeads() {

		return g.clickGoButton();
	}

}
