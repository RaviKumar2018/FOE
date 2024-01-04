package com.fbms.helper;
//package com.fbms.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.fbms.basepage.BasePage;
import com.fbms.foe.pagefactory.accountshome.ContactInfoPage;

public class UtilsHelper extends BasePage {

	@FindBy(css = "p-table tbody tr")
	WebElement rowExist;

	@FindBy(css = "mat-dialog-container button:nth-of-type(1)")
	WebElement clickOk;

	@FindBy(css = "button:nth-of-type(2)")
	WebElement clickNo;

	@FindBy(css = "#file")
	WebElement chooseFile;

	@FindBy(css = "a[mattooltip='Upload Data'] > i")
	WebElement upload;

	@FindBy(css = "a[mattooltip='Excel Download'] > i")
	WebElement download;

	@FindBy(css = "a[class$='ui-dialog-titlebar-icon ui-dialog-titlebar-close ui-corner-all ng-star-inserted']")
	WebElement closeDialog;

	@FindBy(css = ".mat-simple-snackbar.ng-star-inserted span")
	public
	WebElement popup;

	public UtilsHelper(WebDriver driver) {
		super(driver);
	}

	/*
	 * Wait Methods
	 */

	// Method to wait explicitly until the element appears

	public void explicitWait(WebElement element) throws InterruptedException {
		String targetElement = webElementToString(element);
		WebDriverWait wait = new WebDriverWait(getDriver(), 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(targetElement)));
	}

	// Method to wait explicitly until the text appears

	public void explicitWaitOnText(WebElement element, String text) throws InterruptedException {
		//String targetElement = webElementToString(element);
		WebDriverWait wait = new WebDriverWait(getDriver(),15);
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	/*
	 * Date and time Methods
	 */

	// Get current date

	public String currentDate() {
		return java.time.LocalDate.now().toString();
	}

	/* Specify the date format and use
	Example
	1) "yyyy/MM/dd HH:mm:ss"
	2) "yyyy/MM/dd"
	3) "MM/dd/yyyy"
	4) "d:LLL:yyyy"
	5) "dd/MM/yyyy"
	6) "d/MM/yyyy"
	7) "dd-LLL-yy"
	 */

	public String dateFormatter(String format) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);  
		LocalDateTime now = LocalDateTime.now();  
		//System.out.println(dtf.format(now)); 
		return String.valueOf(dtf.format(now));
	}

	public String dateFormatter2(String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, Locale.ENGLISH);
		LocalDate date = LocalDate.parse(format, formatter);
		System.out.println(date);
		return date.toString();
	}

	/*
	 * This method generate's date as per below format 
	 * Format: Thu Mar 26 08:22:02 IST 2015
	 */

	public String getDate() {
		java.util.Date date = new java.util.Date();
		return String.valueOf(date);
	}

	/*
	 * Generates time in 12 hours format 
	 * format example: 8:11:16 AM
	 */

	public String getTime() {
		Date calDate = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss aa");  
		String time = dateFormat.format(calDate);
		return time;
	}

	// Generate random string

	public String randomString(int size) {
		Random r = new Random();
		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String output = "";
		for (int i = 0; i < size; i++) {
			output += alphabet.charAt(r.nextInt(alphabet.length()));
		}
		System.out.println("output = " + output);
		return output;
	}

	/*
	 * Generate random character Methods
	 */

	// Generate random Alpha numeric string

	public String randomAlphaNumeric(int size) {
		Random r = new Random();
		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ126354987";
		String output = "";
		for (int i = 0; i < size; i++) {
			output += alphabet.charAt(r.nextInt(alphabet.length()));
		}
		//System.out.println("output = " + output);
		return output;
	}

	// Generate random integer

	public int randomInt(int size) {
		Random r = new Random();
		String alphabet = "123456789";
		String output = "";
		for (int i = 0; i < size; i++) {
			output += alphabet.charAt(r.nextInt(alphabet.length()));
		}
		System.out.println("output = " + output);
		return Integer.parseInt(output);
	}

	// Generate random Float values(Decimals)

	public String randomFloat(int beforeDecimal, int afterDecimal) {
		int size;
		boolean flag = true;
		Random r = new Random();
		String alphabet = "123456789";
		String output = "";
		size = beforeDecimal;
		for (int i = 1; i <= size; i++) {
			output += alphabet.charAt(r.nextInt(alphabet.length()));
			if(beforeDecimal == i && flag == true) {
				output += ".";
				size = afterDecimal;
				flag = false;
				i = 0;
			}
		}

		System.out.println("output = " + output);
		return output;
	}

	// Clear the data with Keys

	public void clearData(WebElement element) {
		element.sendKeys(Keys.CONTROL + "a" + Keys.CONTROL + Keys.BACK_SPACE);
		element.sendKeys(Keys.BACK_SPACE);
	}

	// Click backspace

	public void backspace(WebElement element) {
		element.sendKeys(Keys.BACK_SPACE);
	}

	/*
	 * Navigation Methods
	 */

	// Refresh Page

	public void refreshPage() {
		getDriver().navigate().refresh();
	}

	// Navigate to the previous page

	public void previousPage() {
		getDriver().navigate().back();
	}

	// Forward page

	public void forwardPage() {
		getDriver().navigate().forward();
	}

	/*
	 * Window Operations
	 */

	// Scroll down in the screen

	public void scrollUp() {
		JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		jse.executeScript("window.scrollBy(0,-250)");
	}

	// Scroll up in the screen

	public void scrollDown() {
		JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		jse.executeScript("window.scrollBy(0,250)");
	}

	/* Use below in case element is passed as String
	 * getDriver().findElement(By.cssSelector(element)))
	 */

	// Scroll to element

	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) getDriver()).executeScript(
				"arguments[0].scrollIntoView(true);",
				element);
	}

	// Scroll within element. Example dropdown

	public void scrollWithinElement(WebElement element) {
		Actions action = new Actions(getDriver());
		action.moveToElement(element);
		action.perform();
	}

	// Switch to frame based on index

	public void switchFrameUponID(int index) {
		getDriver().switchTo().frame(index);
	}

	// Switch to frame based on element

	public void switchFrameUpon(WebElement element) {
		getDriver().switchTo().frame(element);
	}

	// Switch to Parent frame

	public void switchToParentFrame() {
		getDriver().switchTo().parentFrame();
	}

	// Switch to default content

	public void switchToDefaultContent() {
		getDriver().switchTo().defaultContent();
	}

	// Input text in alert window

	public void inputTextInAlert(String text) {
		getDriver().switchTo().alert().sendKeys(text);
	}

	// Accept alert window

	public void acceptAlert() {
		getDriver().switchTo().alert().accept();;
	}

	// Dismiss alert window

	public void dismissAlert() {
		getDriver().switchTo().alert().dismiss();;
	}

	// Get alert window text

	public String getAlertText() {
		return getDriver().switchTo().alert().getText();
	}

	// Get window handle

	public String getWindowHandle() {
		return getDriver().getWindowHandle();
	}

	// Get window handles

	public Set<String> getWindowHandles() {
		return getDriver().getWindowHandles();
	}

	// Switch to window

	public void switchWindow(String windowHandle) {
		getDriver().switchTo().window(windowHandle);
	}

	/* 
	 * Web element methods 
	 */

	// Get value with DOM attribute

	public String getAttribute(String attribute, WebElement element) {
		return element.getAttribute(attribute);
	}

	// Verify the element text

	public boolean validateElementText(String text, WebElement element) {
		try {
			explicitWait(element);
			element.isDisplayed();
			Assert.assertEquals(element.getText().trim(), text);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Verify the element text

	public boolean validateElementString(String text, WebElement element) {
		try {
			explicitWait(element);
			element.isDisplayed();
			Assert.assertEquals(element.getText().trim(), text);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Verify the element exist

	public boolean isElementExist(WebElement element) {
		try {
			explicitWait(element);
			element.isDisplayed();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Verify the element is enabled

	public boolean isElementEnabled(WebElement element) {
		try {
			explicitWait(element);
			element.isEnabled();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Verify the element is selected

	public boolean isElementSelected(WebElement element) {
		try {
			explicitWait(element);
			element.isSelected();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Verify if element is editable

	public boolean isCommentEditable(WebElement element, String text) {
		try{       
			explicitWait(element);
			element.sendKeys(text);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	// Method to convert Web Element to String Note: Applicable only for CSS selector

	public String webElementToString(WebElement element) throws InterruptedException {
		String webElement = element.toString();
		String targetElement = "";
		int size = webElement.length();

		for(int i = size-1;i > 0;i--) {
			if(
					webElement.charAt(i) == ':' 
					&&
					webElement.substring(i-12, i+1).equals("css selector:"))
			{
				targetElement = webElement.substring(i+1, webElement.length()-1).trim();
				break;
			} 
			else if(
					webElement.charAt(i) == ':' 
					&&
					webElement.substring(i-11, i+1).equals("cssSelector:"))
			{
				targetElement = webElement.substring(i+2, webElement.length()-1).trim();
				break;
			}
		}
		return targetElement;
	}

	// Drop down selection based on visible Text

	public void dropdownSelection(String dropdownID, String visibleText) {
		Select s = new Select(getDriver().findElement(By.cssSelector(dropdownID)));
		s.selectByVisibleText(visibleText);
	}

	// Get the default value in drop down

	public String getDefaultValue(String element) {
		Select s = new Select(getDriver().findElement(By.cssSelector(element)));
		WebElement option = s.getFirstSelectedOption();
		String selectedItem = option.getText();
		return selectedItem;
	}

	// Select element based on index

	public void dropdownByIndex(String dropdownID, int index) {
		Select s = new Select(getDriver().findElement(By.cssSelector(dropdownID)));
		s.selectByIndex(index);
	}

	// Select element based on JavaScript(click is made in DOM level)

	public void clickUponDOM(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		jse.executeScript("arguments[0].click();", element);
	}

	// Get table row count

	public int getRowCount() throws InterruptedException {
		Thread.sleep(2000);
		explicitWait(rowExist);
		List<WebElement> rows = getDriver().findElements(By.cssSelector("p-table tbody tr"));
		return rows.size();
	}

	// Click element based on CSS selector
	public void findElementByCSS(String selector) {
		getDriver().findElement(By.cssSelector(selector)).click();
	}

	// Send values into element based on CSS selector
	public void sendValuesForCSS(String selector, String value) {
		getDriver().findElement(By.cssSelector(selector)).sendKeys(value);
	}

	// Select element based on Index
	public void findElementByIndex(String selector, int index) {
		getDriver().findElements(By.cssSelector(selector)).get(index).click();
	}

	// Double click on element
	public void doubleClick(WebElement element) {
		Actions action = new Actions(getDriver());

		//Performing the double click action on the target element.
		action.doubleClick(element).perform();
	}

	// Dialog box OK button 
	public void clickOK() throws InterruptedException {
		Thread.sleep(1000);
		explicitWait(clickOk);
		clickOk.click();
	}

	// Dialog box NO button
	public void clickNo() throws InterruptedException {
		explicitWait(clickNo);
		clickNo.click();
	}

	// Delete a file
	public boolean deleteFile(String fileName) {
		File file = new File(System.getProperty("user.dir") + "/downloads/" + fileName);

		if(file.delete())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	// Verify File Exist
	public boolean fileExist(String fileName) throws InterruptedException {
		File file = new File(System.getProperty("user.dir") + "/downloads/" + fileName);

		Thread.sleep(5000);
		if(file.exists())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	// Choose File while uploading

	public void chooseFile(String fileName) {
		chooseFile.sendKeys(System.getProperty("user.dir") + "\\downloads\\" + fileName);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Click Upload button
	public void upload() {
		upload.click();
	}

	// Click Download button
	public void download(String fileName) throws InterruptedException {
		download.click();
		fileExist(fileName);
	}

	// Close upload dialog
	public void closeDialog() throws InterruptedException {
		explicitWait(closeDialog);
		closeDialog.click();
	}

	// Get excel row count

	public int getExcelRowCount(int vSheet, String fileName) throws InterruptedException {
		Workbook wb = null;
		File src = new File(System.getProperty("user.dir") + "\\downloads\\" + fileName);

		try {
			//reading data from a file in the form of bytes

			FileInputStream fis = new FileInputStream(src);

			//constructs an XSSFWorkbook object, by buffering the whole stream into the memory  

			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Sheet sheet = wb.getSheetAt(vSheet); // getting the XSSFSheet object at given index

		int rowTotal = sheet.getLastRowNum();

		if ((rowTotal > 0) || (sheet.getPhysicalNumberOfRows() > 0)) {
			rowTotal++;
		}

		return rowTotal;
	}

	// Flash message validation
	public String popUpTextWait(String text) throws InterruptedException {
		try {
			explicitWaitOnText(popup, text);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return popup.getText();
	}

	public String getPopupValue(ContactInfoPage contactInfoPage) {
		try {
			explicitWait(contactInfoPage.popup);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return contactInfoPage.popup.getText();
	}

}
