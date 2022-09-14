package com.sfdc.listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.sfdc.test.BaseTest;

import test.pkg.IOUtilities;

public class SFDCListeners extends BaseTest implements ITestListener {

//	@Override
	public void onTestStart(ITestResult result) {

		System.out.println("test has started");
		test = extent.createTest(result.getName());

	}

//	@Override
	public void onTestSuccess(ITestResult result) {

		test.pass(result.getName() + " PASSED");

	}

//	@Override
	public void onTestFailure(ITestResult result) {

		/*
		 * IMPORTANT only for parallel-execution
		 */
//		BaseTest baseTest = new BaseTest();
//		WebDriver driver = baseTest.driver;
		/*
		 * REQUIRED - Normal run code
		 */
		WebDriver driver = BaseTest.driver;
		/*
		 * Not used - check later
		 */
//		WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver"); // here we are accessing the
		try {
			test.addScreenCaptureFromPath(IOUtilities.captureScreenShot(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}

		test.fail(result.getName() + " FAILED");
	}

//	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

//	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

//	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

//	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}
}
