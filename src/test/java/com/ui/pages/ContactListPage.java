package com.ui.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.constants.Browser;
import com.utility.BrowserUtility;

public final class ContactListPage extends BrowserUtility {

	public ContactListPage(WebDriver driver) {
		super(driver);
	}

	private static final By HEADER_TXT_LOCATOR = By.tagName("h1");
	private static final By ADD_NEW_CONTACT_BTN_LOCATOR = By.id("add-contact");
	private static final By LOGOUT_BTN_LOCATOR = By.id("logout");
	private static final By RECORD_NAME_LOCATOR = By.xpath("//table/tr/td[2]");
	private static final By TABLE_RECORDS_LOCATOR = By.xpath("//table/tr");

	public LoginPage userLogOut() {
		clickOn(LOGOUT_BTN_LOCATOR);
		LoginPage loginPage = new LoginPage(Browser.CHROME, true);
		return loginPage;
	}
	
	public AddNewContactPage addNewContact() {
		clickOn(ADD_NEW_CONTACT_BTN_LOCATOR);
		AddNewContactPage addNewContactPage=new AddNewContactPage(getDriver());
		return addNewContactPage;
	}
	
	

}
