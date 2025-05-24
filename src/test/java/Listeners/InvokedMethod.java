package Listeners;

import Utilities.LogsUtil;
import Utilities.Utility;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.IOException;

import static DriverFactory.driverFactory.getDriver;

public class InvokedMethod implements IInvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            try {
                LogsUtil.info("Test Case " + testResult.getName() + " failed");
                Utility.takeScreenshots(getDriver(), testResult.getName());
                //Utility.takefullScreenshot(getDriver(), new P02_HomePage(getDriver()).getNumberofProductsonCartIcon());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
