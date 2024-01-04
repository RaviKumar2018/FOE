package com.fbms.framework;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.fbms.utils.ReportsFactory;
import com.fbms.utils.TestDataFactory;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {
	public WebDriver driver;
	public static ExtentTest ReportLogger;
	ExtentTest logger;
	ExtentReports extent;

	public BaseTest() {
	}

	@BeforeMethod
	public void getDriver() throws IOException {
		driver = DriverFactory.getDriver();
	}

	//@AfterMethod
	public void stopDriver() {
		DriverFactory.tearDown();
		driver = null;
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		String methodName;

		methodName = result.getMethod().toString();
		for(int i = 0;i <= methodName.length();i++) {
			if(methodName.charAt(i) == '(') {
				methodName = methodName.substring(0, i+2);
				break;
			}
		}

		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotPath = ReportsFactory.getScreenshot(driver, result.getName());
			ReportLogger.log(LogStatus.FAIL, methodName + "\n" + result.getThrowable()
			+ ReportLogger.addScreenCapture(screenshotPath)
					);
		} 
		else if (result.getStatus() == ITestResult.SKIP) {
			ReportLogger.log(LogStatus.SKIP, "Test Case Skipped is " + methodName);
		} 
		else if (result.getStatus() == ITestResult.SUCCESS) {
			//.getDescription()
			ReportLogger.log(LogStatus.PASS, "Test Case passed is " + methodName);
		}
	}

	@BeforeSuite
	public void beginReportFactory() {
		ReportLogger = ReportsFactory.startReports();
	}

	@AfterSuite
	public void endReportTest() {
		ReportsFactory.endReports();
	}

	public void testData(int row, int column, int sheet) {
		TestDataFactory.ReadCellData(row, column, sheet);
	}

}