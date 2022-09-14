package test.pkg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class LoginUtils {

	@SuppressWarnings("finally")
	public static boolean checkLoginErrors(WebDriver driver, ExtentTest test) {
		String[] expectedErrors = {
				"Please check your username and password. If you still can't log in, contact your Salesforce administrator.",
				"Please enter your password.",
				"We’ve sent you an email with a link to finish resetting your password." };

		WebElement error;
		boolean isLoginError = false;
		int errorIndex = -1;
		String errMsg = "";

		try {

			error = driver.findElement(By.cssSelector("#error"));
			String actualError = error.getText();

			if (!actualError.isEmpty()) {
				for (int i = 0; i < expectedErrors.length; i++) {
					if (actualError.equals(expectedErrors[i])) {
						isLoginError = true;
						errorIndex = i;
						errMsg = expectedErrors[i];
					}
				}

				if (isLoginError) {
					Assert.assertEquals(actualError, expectedErrors[errorIndex], "Password Missing Test");
					test.log(Status.WARNING, "Warning: '"+errMsg+"'");
				}

			} else
				isLoginError = false;

		} catch (Exception e) {
			e.getMessage();
			isLoginError = false;
		} finally {
			return isLoginError;
		}

	}
}
