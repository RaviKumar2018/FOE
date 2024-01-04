package com.fbms.utils;

import java.util.Date;
import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import org.apache.commons.io.FileUtils;

public class ReportsFactory {
	static ExtentTest test;
	static ExtentReports report;
	ExtentReports extent;
	ExtentTest logger;
	WebDriver driver;

	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	public static ExtentTest startReports() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
		System.out.println(formatter.format(date).replaceAll(" ", ""));
		report = new ExtentReports(System.getProperty("user.dir") + "\\reports\\FBMSModernizationResults"
				+ formatter.format(date).replaceAll(" ", "") + ".html");
		test = report.startTest("FBMSModernization");
		return test;
	}

	public static void endReports() {
		report.endTest(test);
		report.flush();
	}
}