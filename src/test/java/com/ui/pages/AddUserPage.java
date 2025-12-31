package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import com.utility.BrowserUtility;

public final class AddUserPage extends BrowserUtility {

	public AddUserPage(WebDriver driver) {
		super(driver);
	}

	private static final By FIRST_NAME_TB_LOCATOR = By.id("firstName");
	private static final By LAST_NAME_TB_LOCATOR = By.id("lastName");
	private static final By EMAIL_TB_LOCATOR = By.id("email");
	private static final By PASSWORD_TB_LOCATOR = By.id("password");
	private static final By SUBMIT_BTN_LOCATOR = By.id("submit");
	private static final By CANCEL_BTN_LOCATOR = By.id("cancel");
	private static final By ERROR_MSG_LOCATOR = By.id("error");

	// Verify sign-up with valid inputs
	public ContactListPage addUserSubmit() {
		enterText(FIRST_NAME_TB_LOCATOR, "Hihoh");
		enterText(LAST_NAME_TB_LOCATOR, "James");
		enterText(EMAIL_TB_LOCATOR, "dihoh80532@lawior.com");
		enterText(PASSWORD_TB_LOCATOR, "Password@01");
		clickOn(SUBMIT_BTN_LOCATOR);
		ContactListPage contactListPage = new ContactListPage(getDriver());
		return contactListPage;
	}

	// Verify registration with already registered email
	public LoginPage addUserExistingEmail() {
		enterText(FIRST_NAME_TB_LOCATOR, "Hihoh");
		enterText(LAST_NAME_TB_LOCATOR, "James");
		enterText(EMAIL_TB_LOCATOR, "hihoh80532@lawior.com");
		enterText(PASSWORD_TB_LOCATOR, "Password@01");
		clickOn(SUBMIT_BTN_LOCATOR);
		getMessageText(ERROR_MSG_LOCATOR);
		clickOn(CANCEL_BTN_LOCATOR);
		LoginPage loginPage = new LoginPage(Browser.CHROME, true);
		return loginPage;
	}

	// Verify email format validation during sign-up
	public LoginPage addUserWithInvalidEmailFormat() {
		enterText(FIRST_NAME_TB_LOCATOR, "Hihoh");
		enterText(LAST_NAME_TB_LOCATOR, "James");
		enterText(EMAIL_TB_LOCATOR, "jihoh80532@lawior");
		enterText(PASSWORD_TB_LOCATOR, "Password@01");
		clickOn(SUBMIT_BTN_LOCATOR);
		getMessageText(ERROR_MSG_LOCATOR);
		clickOn(CANCEL_BTN_LOCATOR);
		LoginPage loginPage = new LoginPage(Browser.CHROME, true);
		return loginPage;
	}

	// Verify registration with blank fields
	public LoginPage addUserWithBlankEmailAndPassword() {
		enterText(FIRST_NAME_TB_LOCATOR, "Hihoh");
		enterText(LAST_NAME_TB_LOCATOR, "James");
		clickOn(SUBMIT_BTN_LOCATOR);
		getMessageText(ERROR_MSG_LOCATOR);
		clickOn(CANCEL_BTN_LOCATOR);
		LoginPage loginPage = new LoginPage(Browser.CHROME, true);
		return loginPage;
	}

	// Verify User Canceling the User Registration
	public LoginPage addUserCancel() {
		clickOn(CANCEL_BTN_LOCATOR);
		LoginPage loginPage = new LoginPage(Browser.CHROME, true);
		return loginPage;
	}

}
