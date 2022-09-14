package test.pkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class IOUtilities {

	
	public static String captureScreenShot(WebDriver driver) throws IOException {
		
		String dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String destinationPath = System.getProperty("user.dir")+"/src/main/resources/screenshots/"+dateFormat+"_img.PNG";
		
		TakesScreenshot screenShot = (TakesScreenshot)driver;
		File sourceFile = screenShot.getScreenshotAs(OutputType.FILE);
				
		File destinationFile = new File(destinationPath);
		FileUtils.copyFile(sourceFile, destinationFile);
		
		System.out.println("Screenshot Captured: "+ dateFormat+"_img.PNG");
		
		return destinationPath;
	}
	
	public static String readPropertiesFile(String filename, String key) throws IOException {
		
		String filepath = System.getProperty("user.dir")+"/src/main/resources/testdata/"+filename+".properties";
		Properties p = new Properties();
		FileReader f = new FileReader(filepath);
		p.load(f);

		String value = p.getProperty(key);
		System.out.println(key +": "+value);
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
}
