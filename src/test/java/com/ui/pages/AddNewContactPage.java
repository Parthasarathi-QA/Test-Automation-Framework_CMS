package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.utility.BrowserUtility;

public class AddNewContactPage extends BrowserUtility{

	public AddNewContactPage(WebDriver driver) {
		super(driver);
	}

	private static final By HEADER_TXT_LOCATOR = By.tagName("h1");
	private static final By FIRST_NAME_TB_LOCATOR = By.id("firstName");
	private static final By LAST_NAME_TB_LOCATOR = By.id("lastName");
	private static final By BIRTH_DATE_TB_LOCATOR = By.id("birthdate");
	private static final By EMAIL_TB_LOCATOR = By.id("email");
	private static final By PHONE_TB_LOCATOR = By.id("phone");
	private static final By STREET1_TB_LOCATOR = By.id("street1");
	private static final By STREET2_TB_LOCATOR = By.id("street2");
	private static final By CITY_TB_LOCATOR = By.id("city");
	private static final By STATE_OR_PROVINCE_TB_LOCATOR = By.id("stateProvince");
	private static final By POSTAL_CODE_TB_LOCATOR = By.id("postalCode");
	private static final By COUNTRY_TB_LOCATOR = By.id("country");
	private static final By SUBMIT_BTN_LOCATOR = By.id("submit");
	private static final By CANCEL_BTN_LOCATOR = By.id("cancel");
	private static final By LOGOUT_BTN_LOCATOR = By.id("logout");
	private static final By ERROR_MSG_LOCATOR = By.id("lastName");

	public ContactListPage addNewContactDetails() {
		enterText(FIRST_NAME_TB_LOCATOR, "David");
		enterText(LAST_NAME_TB_LOCATOR, "Richard");
		enterText(BIRTH_DATE_TB_LOCATOR, "1998-03-03");
		enterText(EMAIL_TB_LOCATOR, "david.richard@gmail.com");
		enterText(PHONE_TB_LOCATOR, "9589876549");
		enterText(STREET1_TB_LOCATOR, "TEST01");
		enterText(STREET2_TB_LOCATOR, "TEST01");
		enterText(CITY_TB_LOCATOR, "ALABAMA");
		enterText(STATE_OR_PROVINCE_TB_LOCATOR, "ALABAMA");
		enterText(POSTAL_CODE_TB_LOCATOR, "15465");
		enterText(COUNTRY_TB_LOCATOR, "America");
		clickOn(SUBMIT_BTN_LOCATOR);
		ContactListPage contactListPage=new ContactListPage(getDriver());
		return contactListPage;
	}
}
