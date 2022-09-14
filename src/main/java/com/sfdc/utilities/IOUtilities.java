package com.sfdc.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class IOUtilities {

	public static String captureScreenShot(WebDriver driver) throws IOException {

		String dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String destinationPath = System.getProperty("user.dir") + "/src/main/resources/screenshots/" + dateFormat
				+ "_img.PNG";

		TakesScreenshot screenShot = (TakesScreenshot) driver;
		File sourceFile = screenShot.getScreenshotAs(OutputType.FILE);

		File destinationFile = new File(destinationPath);
		FileUtils.copyFile(sourceFile, destinationFile);

		System.out.println("Screenshot Captured: " + dateFormat + "_img.PNG");

		return destinationPath;
	}

	public static String readPropertiesFile(String filename, String key) {

		String filepath = System.getProperty("user.dir") + "/src/main/resources/testdata/" + filename + ".properties";
		Properties p = new Properties();
		FileReader f = null;
		try {
			f = new FileReader(filepath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			p.load(f);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String value = p.getProperty(key);
//		System.out.println(key +": "+value);
		return value;

	}

	public static void readCellInExcel(int row, int column) throws IOException {

		String filepath = "/Users/ghorpade/Desktop/Files/Tekarch/3 Topics/Selenium/TestDataSheet.xlsx";
		FileInputStream fis = new FileInputStream(filepath);

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet s2 = wb.createSheet("s2");
		XSSFSheet s1 = wb.getSheetAt(0);
		String cell = s1.getRow(row).getCell(column).toString();
		System.out.println(cell);
	}

	public static void main(String[] args) {
		try {
			System.out.println(IOUtilities.readPropertiesFile("loginData", "username"));
			IOUtilities.readCellInExcel(1, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String switchWindow(WebDriver driver) {

		Set<String> allWindowHandles = driver.getWindowHandles();
		String[] openWindows = allWindowHandles.toArray(new String[allWindowHandles.size()]);
		String currentWindow = driver.getWindowHandle();
		String nextWindow = openWindows[1];
		driver.switchTo().window(nextWindow);

		return currentWindow;
	}

	public static boolean switchWindow(WebDriver driver, String currentWindowHandle) {
		driver.switchTo().window(currentWindowHandle);
		return true;
	}

	public static boolean closeAlert(WebDriver driver) {
	
		Alert alert = driver.switchTo().alert();
		System.out.println("Alert Message: "+alert.getText());
		alert.accept();
		return true;
	}
	
	/**
	 *
	 * @param startDate
	 * @param futureDays
	 * @return string
	 */
	public static String addDaysToDate(String startDate, String futureDays, String DATE_FORMAT) {
		Calendar c = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);

		try {
			Date myDate = df.parse(startDate.trim());
			c.setTime(myDate);
			c.add(Calendar.DATE, Integer.parseInt(futureDays));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String futureDate = df.format(c.getTime());

		return futureDate;
	}

}
