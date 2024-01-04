package com.fbms.framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.codeborne.selenide.Configuration;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	private static WebDriver driver;
	public static String projectLocation = System.getProperty("user.dir");
	public static Properties prop = new Properties();

	public static WebDriver getDriver() throws IOException {
		// FIXME: can this be accessed parallelly?
		if (driver == null) {
			setup();
		}
		return driver;
	}

	/*public static void setup() {
		// Uses chrome driver by default
		String browser = System.getenv("BROWSER");
		browser = "headless";
		if (browser.toLowerCase().equals(FIREFOX)) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.toLowerCase().equals(IE)) {
			// WebDriverManager.iedriver().setup();
			WebDriverManager.iedriver().architecture(Architecture.X32).arch32().setup();
			// Configuration.browser = "ie";
			// driver = new InternetExplorerDriver();
			// System.setProperty("webdriver.ie.driver",
			// "C:\\fordutils\\chromedriver\\IEDriverServer_1.exe");
			driver = new InternetExplorerDriver();
		} else if (browser.toLowerCase().equals(CHROME)) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.toLowerCase().equals(EDGE)) {
			System.setProperty("webdriver.ie.driver", "C:\\fordutils\\chromedriver\\msedgedriver_1.exe");
			driver = new InternetExplorerDriver();
			// WebDriverManager.edgedriver().setup();
			// driver = new MSedgeDriver();
		} else {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("disable-gpu");
			driver = new ChromeDriver(options);
			options.setBinary(
					"/var/jenkins_home/tools/com.cloudbees.jenkins.plugins.customtools.CustomTool/chrome_77/usr/bin");
		}
	}*/

	public static void setup() throws IOException {
		// Uses chrome driver by default
		FileInputStream fis = new FileInputStream("src//test//resources//config.properties");
		prop.load(fis);

		String browser = System.getenv("BROWSER");
		//browser=prop.getProperty("IE");
		browser=prop.getProperty("CHROME");
		if (browser.toLowerCase().equals(prop.getProperty("FIREFOX"))) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} 
		else if (browser.toLowerCase().equals(prop.getProperty("IE"))) {
			//WebDriverManager.iedriver().setup();
			//WebDriverManager.iedriver().architecture(Architecture.X32).arch32().setup();
			Configuration.browser = prop.getProperty("IE");
			//driver = new InternetExplorerDriver();
			System.setProperty(
					"webdriver.ie.driver",
					projectLocation +
					"\\drivers\\edgeDriver\\IEDriverServer.exe"
					); 
			driver=new InternetExplorerDriver();
		}
		else {
			//WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver();
			System.setProperty(
					"webdriver.chrome.driver",
					projectLocation +
					"\\drivers\\chromeDriver\\chromedriver.exe"
					);

			String downloadFilepath = System.getProperty("user.dir") + "\\downloads\\";
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);

			driver = new ChromeDriver(options);
		}
	}

	public static void tearDown() {
		driver.close();
		driver.quit();
		driver = null;
	}

	public static void timeOuts() {
		driver.get(prop.getProperty("QA_URL")); //QA_URL DEV_URL
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// TODO: Move this to BaseTest.
	public static void waits(int timelimit) {
		try {
			Thread.sleep(timelimit);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}