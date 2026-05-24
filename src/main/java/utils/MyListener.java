package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.Testbase;

public class MyListener implements ITestListener {

	ExtentReports extent= ExtentManager.getInstance();
	ExtentTest test;
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("🔥 INSIDE onTestStart");
		test= extent.createTest(result.getName());
		ExtentTestManager.setTest(test);
		System.out.println("Test Started: "+result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
		 Integer row = TestResultManager.getRow();
		    if (row != null) {
		        ExcelUtil.setCellData("Login", row, 3, "PASS"); 
		    }
		
		System.out.println("Test Passed: "+result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		 if (ExtentTestManager.getTest() != null) {
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
		    } else {
		        System.out.println("⚠ ExtentTest is NULL");
		    }
		
		 Integer row = TestResultManager.getRow();

		 if (row != null) {
		     ExcelUtil.setCellData("Login", row, 3, "FAIL");
		 }
		 
		 // Take screenshot
		String path= ScreenshotUtil.captureScreenshot(Testbase.getDriver(), result.getName());
		
		System.out.println("Test Failed: "+result.getName());
		
		 // Attach screenshot to report
		try {
			ExtentTestManager.getTest().addScreenCaptureFromPath(path);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
		
		Integer row = TestResultManager.getRow();

		if (row != null) {
		    ExcelUtil.setCellData("Login", row, 3, "FAIL");
		}
		
		 // Take screenshot
		String path= ScreenshotUtil.captureScreenshot(Testbase.getDriver(), result.getName());
		
		System.out.println("Test Skipped: "+result.getName());
		
		 // Attach screenshot to report
		try {
			ExtentTestManager.getTest().addScreenCaptureFromPath(path);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test failed with certain success percentage: "+result.getName());
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ScreenshotUtil.captureScreenshot(Testbase.getDriver(), result.getName());
		System.out.println("Test Failed with Timout: "+result.getName());
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
		System.out.println("onStart method started");
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
		System.out.println("onStart method finished");
	}

}
