package Listeners;

import Utilities.LogsUtil;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Itest implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        LogsUtil.info("Test Case " + result.getName() + " started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogsUtil.info("Test Case " + result.getName() + " passed");
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        LogsUtil.info("Test Case " + result.getName() + " skipped");
    }
}
