package com.sfdc.pages;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.sfdc.utilities.DataUtilities;

public class BaseSFDCPage {

	static Logger logger = LogManager.getLogger(BaseSFDCPage.class.getName());

	public void launchApp(WebDriver driver)  {
		
		try {
			driver.get(DataUtilities.readPropertiesFile("logintestdata", "prod.salesforce"));
			logger.info("Launched app: From BaseSFDCPage");
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
}
