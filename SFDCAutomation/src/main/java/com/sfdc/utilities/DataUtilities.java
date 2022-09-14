package com.sfdc.utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataUtilities {

	public static String readPropertiesFile(String filename, String key) throws IOException {

		String filepath = System.getProperty("user.dir") + "/src/main/resources/testdata/" + filename + ".properties";
		Properties p = new Properties();
		FileReader f = new FileReader(filepath);
		p.load(f);

		String value = p.getProperty(key);
		System.out.println(key + ": " + value);
		return value;

	}

	
}
