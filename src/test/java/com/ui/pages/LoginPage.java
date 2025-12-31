package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import com.utility.LoggerUtility;

import static com.utility.PropertiesUtil.*;

public final class LoginPage extends BrowserUtility {
	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	public LoginPage(Browser browserName, boolean isHeadless) {
		super(browserName, isHeadless);
		goToWebSite(JSONUtility.readJSON(QA).getUrl());
		maximizeWindow();
	}
	
	public LoginPage(WebDriver driver) {
		super(driver);
		goToWebSite(JSONUtility.readJSON(QA).getUrl());
		maximizeWindow();
	}

	private static final By EMAIL_TB_LOCATOR = By.id("email");
	private static final By PASSWORD_TB_LOCATOR = By.id("password");
	private static final By SUBMIT_BTN_LOCATOR = By.id("submit");
	private static final By SIGN_UP_BTN_LOCATOR=By.id("signup");
	private static final By ERROR_MSG_LOCATOR=By.id("error");

	//Verify login with valid credentials
	public ContactListPage login(String emailAddress, String password) {
		logger.info("Trying to Login in!!");
		enterText(EMAIL_TB_LOCATOR, emailAddress);
		enterText(PASSWORD_TB_LOCATOR, password);
		clickOn(SUBMIT_BTN_LOCATOR);
		ContactListPage contactListPage=new ContactListPage(getDriver());
		return contactListPage;
	}
	
	//Verify login with incorrect password
	public LoginPage loginWithInvalidCredentials() {
		enterText(EMAIL_TB_LOCATOR, "tedfdfsdf@gmail.com");
		enterText(PASSWORD_TB_LOCATOR, "sdfsdfsdf");
		clickOn(SUBMIT_BTN_LOCATOR);
		getMessageText(ERROR_MSG_LOCATOR);
		pageRefresh();
		LoginPage loginPage = new LoginPage(Browser.CHROME, true);
		return loginPage;
	}
	
	//Verify login with empty fields
	public LoginPage loginWithEmptyFields() {
		clickOn(SUBMIT_BTN_LOCATOR);
		getMessageText(ERROR_MSG_LOCATOR);
		pageRefresh();
		LoginPage loginPage = new LoginPage(Browser.CHROME, true);
		return loginPage;
	}
	
	//Verify login with invalid email format
	public LoginPage loginWithInvalidEmailFormat() {
		enterText(EMAIL_TB_LOCATOR, "tedfdfsdf.com");
		enterText(PASSWORD_TB_LOCATOR, "sdfsdfsdf");
		clickOn(SUBMIT_BTN_LOCATOR);
		getMessageText(ERROR_MSG_LOCATOR);
		pageRefresh();
		LoginPage loginPage = new LoginPage(Browser.CHROME, true);
		return loginPage;
	}
	
	//Verify password field masks input
	public LoginPage pwdFildValidation() {
		enterText(EMAIL_TB_LOCATOR, "tester001@gmail.com");
		enterText(PASSWORD_TB_LOCATOR, "tester001@gmail.com");
		String actuals=getAttributeValue(PASSWORD_TB_LOCATOR, "type");
		//assertEquals(actuals,"password","Password field is not Masked");
		LoginPage loginPage = new LoginPage(Browser.CHROME, true);
		return loginPage;
	}
	
	// Verify sign-up with valid inputs
	public AddUserPage signUp() {
		clickOn(SIGN_UP_BTN_LOCATOR);
		AddUserPage addUserPage=new AddUserPage(getDriver());
		return addUserPage;
	}
}
