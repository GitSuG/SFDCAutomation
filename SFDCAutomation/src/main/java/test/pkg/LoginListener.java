package test.pkg;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class LoginListener extends ReportsLogin implements ITestListener {

	public void onTestStart(ITestResult result) {

		System.out.println("Listener: Test started: " + result.getName());
		System.out.println("Listener: Test started: " + result.getTestName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Listener: PASS: Status = " + result.getStatus());
		ReportsLogin.test.log(Status.PASS, result.getName());
	}

	public void onTestFailure(ITestResult result) {
		try {
			System.out.println("Listener: FAIL: Status = " + result.getStatus());
			ReportsLogin.test.log(Status.FAIL, result.getName());
			ReportsLogin.test.addScreenCaptureFromPath(IOUtilities.captureScreenShot(ReportsLogin.driver));
		} catch (IOException e) {
			e.getMessage();
		}
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}

}
