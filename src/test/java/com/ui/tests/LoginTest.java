package com.ui.tests;

import static com.constants.Browser.CHROME;
import static org.testng.Assert.*;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.LoginPage;
import com.ui.pojo.User;
import com.utility.LoggerUtility;


@Listeners(com.ui.listeners.TestListener.class)
public class LoginTest extends TestBase{
	

	

	@Test(description = "Verify the valid user is able to login to the application", groups = { "e2e", "sanity",
			"smoke" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider")
	public void loginTest(User user) {

		assertEquals(loginPage.login(user.getEmailAddress(), user.getPassword()).getTitleOfPage(), "My Contacts",
				"Login was not successful and Contact List Page is not Loaded");

	}

	@Test(description = "Verify the valid user is able to login to the application", groups = { "e2e", "sanity",
			"smoke" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestCSVDataProvider")
	public void loginCVTest(User user) {

		assertEquals(loginPage.login(user.getEmailAddress(), user.getPassword()).getTitleOfPage(), "My Contacts",
				"Login was not successful and Contact List Page is not Loaded");
	}

	@Test(description = "Verify the valid user is able to login to the application", groups = { "e2e", "sanity",
			"smoke" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestExcelDataProvider", retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
	public void loginExcelTest(User user) {
		
		
		assertEquals(loginPage.login(user.getEmailAddress(), user.getPassword()).getTitleOfPage(), "My Contacts1",
				"Login was not successful and Contact List Page is not Loaded");
		
	}

}