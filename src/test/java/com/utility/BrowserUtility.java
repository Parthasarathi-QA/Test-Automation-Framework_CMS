package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.constants.Browser;

public abstract class BrowserUtility {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	private SoftAssert Assert;
	private WebDriverWait wait;
	Logger logger = LoggerUtility.getLogger(this.getClass());

	public WebDriver getDriver() {
		return driver.get();

	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);
	}

	public BrowserUtility(String browserName) {
		logger.info("Launching Browser for  " + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {

			driver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("edge")) {

			driver.set(new EdgeDriver());
		} else if (browserName.equalsIgnoreCase("firefox")) {
			logger.info("Launching Browser for  " + browserName);
			driver.set(new FirefoxDriver());
		} else {

			logger.error("Invalid Browser Name.......Please select Chrome/Edge/Firefox alone");
			System.err.println("Invalid Browser Name.......Please select Chrome/Edge/Firefox alone");
		}
	}

	public BrowserUtility(Browser browserName) {
		logger.info("Launching Browser for  " + browserName);
		if (browserName == Browser.CHROME) {
			driver.set(new ChromeDriver());
		} else if (browserName == Browser.EDGE) {
			driver.set(new EdgeDriver());
		} else if (browserName == Browser.FIRFOX) {
			driver.set(new FirefoxDriver());
		} else {
			logger.error("Invalid Browser Name.......Please select Chrome/Edge/Firefox alone");
			System.err.println("Invalid Browser Name.......Please select Chrome/Edge/Firefox alone");
		}
	}
	
	public BrowserUtility(Browser browserName,boolean isHeadless) {
		logger.info("Launching Browser for " + browserName);
		if (browserName == Browser.CHROME) {
			if(isHeadless) {
			ChromeOptions options=new ChromeOptions();
			options.addArguments("--headless=new");
			options.addArguments("--window-size=1920,1080");
			options.addArguments("--disable-gpu");
			driver.set(new ChromeDriver(options));
			}
			else {
				driver.set(new ChromeDriver());
			}
		} else if (browserName == Browser.EDGE) {
			if(isHeadless) {
				EdgeOptions options=new EdgeOptions();
				options.addArguments("--headless=new");
				options.addArguments("--disable-gpu");
				driver.set(new EdgeDriver(options));
				}
				else {
					driver.set(new EdgeDriver());
				}
		} else if (browserName == Browser.FIRFOX) {
			if(isHeadless) {
				FirefoxOptions options=new FirefoxOptions();
				options.addArguments("--headless=new");
				options.addArguments("--disable-gpu");
				driver.set(new FirefoxDriver(options));
				}
				else {
					driver.set(new FirefoxDriver());
				}
		} else {
			logger.error("Invalid Browser Name.......Please select Chrome/Edge/Firefox alone");
			System.err.println("Invalid Browser Name.......Please select Chrome/Edge/Firefox alone");
		}
	}

	public void goToWebSite(String url) {
		logger.info("Visiting the website " + url);
		driver.get().get(url);

	}

	public void maximizeWindow() {
		logger.info("Maximizing Browser Window");
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
		logger.info("Finding the element with locator " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element Found and now performing Click");
		element.click();
	}

	public void enterText(By locator, String text) {
		logger.info("Finding the element with locator " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element Found and now enter text " + text);
		element.sendKeys(text);
	}

	public String getMessageText(By locator) {
		logger.info("Finding the element with locator " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element Found and now returning visible text" + element.getText());
		return element.getText();
	}

	public String getAttributeValue(By locator, String attribute) {
		logger.info("Finding the element with locator " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element Found and now returning it's Attribuete value" + element.getAttribute(attribute));
		return element.getAttribute(attribute);
	}

	/**
	 * public void assertComparingStrings(String expected, String actuals, String
	 * message) { Assert.assertEquals(actuals, expected, message); }
	 */

	public void quitBrowser() {
		logger.info("Quiting the Browser");
		 if (driver.get() != null) {
		        logger.info("Quiting the Browser");
		        driver.get().quit();
		        driver.remove(); // ⭐ REQUIRED
		    }
	}

	public void pageRefresh() {
		logger.info("Page refresh");
		driver.get().navigate().refresh();
	}

	public String getTitleOfPage() {
		logger.info("Getting the title of the page");
		waitForPageToLoad("contactList");
		logger.info("Element Found and now returning Title of page" + driver.get().getTitle());
		return driver.get().getTitle();
	}

	public void waitForPageToLoad(String partialURLText) {
		wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30));
		wait.until(ExpectedConditions.urlContains(partialURLText));
	}

	public String takeScreenshot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		File ScreenshotData = screenshot.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = format.format(date);
		String path = System.getProperty("user.dir") + "//screenshots//" + name + Thread.currentThread().getId() +" - " + timeStamp + ".png";
		File ScreenshotFile = new File(path);
		try {
			FileUtils.copyFile(ScreenshotData, ScreenshotFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
