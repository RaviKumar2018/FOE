/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.fbms.basepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	private WebDriver driver;

	public BasePage(WebDriver driver) {
		this.setDriver(driver);
		// This initElements method will create all WebElements.
		PageFactory.initElements(driver, this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	
}