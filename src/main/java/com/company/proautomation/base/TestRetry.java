package com.company.proautomation.base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * @author vsanap
 * @created on 27-Sep-22
 * @project pro-automation
 */
public class TestRetry implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 4;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}
