package com.ui.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.constants.Env;
import com.utility.JSONUtility;
import com.utility.PropertiesUtil;

public class MyRetryAnalyzer implements IRetryAnalyzer {
	//private static final int MAX_NUM_OF_ATTEMPTS = Integer.parseInt(PropertiesUtil.readProperty(Env.QA, "MAX_NUM_OF_ATTEMPTS"));
	private static final int MAX_NUM_OF_ATTEMPTS = JSONUtility.readJSON(Env.QA).getMAX_NUM_OF_ATTEMPTS();
	private static int current_attempt = 1;

	@Override
	public boolean retry(ITestResult result) {
		if (current_attempt <= MAX_NUM_OF_ATTEMPTS) {
			current_attempt++;
			return true;
		}
		return false;
	}

}
