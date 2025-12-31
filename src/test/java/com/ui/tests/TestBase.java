package com.ui.tests;

import static com.constants.Browser.CHROME;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.ui.pages.LoginPage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;

public class TestBase {
	
	protected LoginPage loginPage;
	protected BrowserUtility browserUtility;
	Logger logger = LoggerUtility.getLogger(this.getClass());
	private boolean isLambdaTest=false;
	//private boolean isHeadLess=true;
	
	@Parameters({"browser","isLambdaTest","isHeadLess"})
	@BeforeMethod(description = "Load the Login page of the Website")
	public void setUp(@Optional("chrome") String browser,
			@Optional("false") boolean isLambdaTest,
			@Optional("true") boolean isHeadLess,
			ITestResult result) {
		
		this.isLambdaTest=isLambdaTest;
		WebDriver lamdaDriver;
		if(isLambdaTest) {
			lamdaDriver=LambdaTestUtility.initializeLambdaTestSession(browser, result.getMethod().getMethodName());
			loginPage = new LoginPage(lamdaDriver);
		}else {
			logger.info("Load the Login page of the Website");
			loginPage = new LoginPage(Browser.valueOf(browser.toUpperCase()),isHeadLess);
		}
		
	}
	
	public BrowserUtility getInstance() {
		return loginPage;
	}
	
	@AfterMethod(alwaysRun = true)
    public void tearDown() {
		
       if(isLambdaTest) {
    	   LambdaTestUtility.quitSession();
       }
       else {
    	   loginPage.quitBrowser();
       }
    }

}
