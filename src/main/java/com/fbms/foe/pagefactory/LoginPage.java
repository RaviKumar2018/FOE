package com.fbms.foe.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.fbms.basepage.BasePage;
import com.fbms.foe.pagefactory.accountshome.AccountsHomePage;
import com.fbms.helper.UtilsHelper;

public class LoginPage extends BasePage {

	@FindBy(id = "userName")
	WebElement userName;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "btn-sign-in")
	WebElement signin;

	@FindBy(id = "lnkcontinue")
	WebElement lnkcontinue;

	@FindBy(className = "mat-menu-trigger")
	WebElement matMenuTrigger;

	@FindBy(className = "fas fa-address-book")
	WebElement faAddressBook;

	@FindBy(css = "div[tabindex='1'] .idpDescription.float:nth-of-type(1)")
	WebElement bySelection;

	@FindBy(xpath = "//*[@id=\"mat-menu-panel-0\"]/div/a[3]")
	WebElement matMenuPanel0;

	@FindBy(xpath = "//*[@id=\"mat-tab-content-0-0\"]/div/address/div/div[1]/div[2]/span")
	WebElement matTabContent;

	@FindBy(xpath = "/html/body/fbmsheader/div/h1/a")
	WebElement accountTitle;

	@FindBy(css = "div[class='alert alert-danger clsloginerror']")
	WebElement loginError;
	
	@FindBy(css = "a[data-widget='pushmenu']")
	WebElement pushMenu;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void setUserName(String strUserName) {
		userName.sendKeys(strUserName);
	}

	public void setPassword(String strPassword) {
		password.sendKeys(strPassword);
	}

	public void clickSignin() {
		signin.click();
	}

	public AccountsHomePage clickContinue() {
		return new AccountsHomePage(getDriver());
	}

	public void clickMatMenuTrigger() {
		matMenuTrigger.click();
	}

	public void clickAddressBook() {
		faAddressBook.click();
	}

	public void clickBySelection() {
		bySelection.click();
	}

	public boolean displayBySelection() {
		return bySelection.isDisplayed();
	}

	public void clickMatMenuPanel0() {
		matMenuPanel0.click();
	}

	public String getMatTabContent() {
		return matTabContent.getText();
	}

	public String getTitle() {
		return accountTitle.getText();
	}

	public String getLoginError() {
		return loginError.getText();
	}

	public AccountsHomePage login(String user, String password) {
		UtilsHelper utilsHelper = new UtilsHelper(getDriver());
		
		try {
			utilsHelper.explicitWait(bySelection);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clickBySelection();
		
		setUserName(user);
		setPassword(password);

		clickSignin();
		try {
			utilsHelper.explicitWait(pushMenu);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//lnkcontinue.click();
		
		return new AccountsHomePage(getDriver());
	}
}
