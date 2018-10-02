package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

	public static Properties prop;

	public  TestBase() {
	}

	public static void init() {

		prop = new Properties();

		try {
			FileInputStream path = new FileInputStream(
					"G:/Selenium/SeleniumPractise/RestAPIRestAssured/src/main/java/Config.properties");
			prop.load(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
