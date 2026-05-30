package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.Testbase;

public class MyListener implements ITestListener {

    ExtentReports extent = ExtentManager.getInstance();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {

        System.out.println("INSIDE onTestStart");

        test = extent.createTest(result.getName());
        ExtentTestManager.setTest(test);

        System.out.println("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
        }

        Integer row = TestResultManager.getRow();

        if (row != null) {
            ExcelUtil.setCellData("Login", row, 3, "PASS");
        }

        System.out.println("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
        } else {
            System.out.println("ExtentTest is NULL");
        }

        Integer row = TestResultManager.getRow();

        if (row != null) {
            ExcelUtil.setCellData("Login", row, 3, "FAIL");
        }

        String path = null;

        if (Testbase.getDriver() != null) {

            path = ScreenshotUtil.captureScreenshot(
                    Testbase.getDriver(),
                    result.getName());

        } else {

            System.out.println("Driver is NULL. Screenshot skipped.");
        }

        System.out.println("Test Failed: " + result.getName());

        try {

            if (path != null && ExtentTestManager.getTest() != null) {
                ExtentTestManager.getTest()
                        .addScreenCaptureFromPath(path);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
        }

        Integer row = TestResultManager.getRow();

        if (row != null) {
            ExcelUtil.setCellData("Login", row, 3, "FAIL");
        }

        String path = null;

        if (Testbase.getDriver() != null) {

            path = ScreenshotUtil.captureScreenshot(
                    Testbase.getDriver(),
                    result.getName());

        } else {

            System.out.println("Driver is NULL. Screenshot skipped.");
        }

        System.out.println("Test Skipped: " + result.getName());

        try {

            if (path != null && ExtentTestManager.getTest() != null) {
                ExtentTestManager.getTest()
                        .addScreenCaptureFromPath(path);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

        System.out.println(
                "Test failed with certain success percentage: "
                        + result.getName());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

        if (Testbase.getDriver() != null) {

            ScreenshotUtil.captureScreenshot(
                    Testbase.getDriver(),
                    result.getName());

        } else {

            System.out.println( "Driver is NULL. Screenshot skipped.");
        }

        System.out.println(
                "Test Failed with Timeout: "
                        + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {

        ITestListener.super.onStart(context);

        System.out.println("onStart method started");
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();

        System.out.println("onFinish method finished");
    }
}