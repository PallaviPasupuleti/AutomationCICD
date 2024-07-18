package pallavi.com.SeleniumFramework.testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pallavi.com.SeleniumFramework.Resources.ExtentReportNG;

public class TestListener extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent=ExtentReportNG.ExtentReport();
	ThreadLocal<ExtentTest> extentthread=new ThreadLocal<ExtentTest>();//Threadsafe

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub//
		//ITestListener.super.onTestStart(result);
		
		test=extent.createTest(result.getMethod().getMethodName());
		extentthread.set(test);//creates unique thread ID and maps to test
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSuccess(result);
		extentthread.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailure(result);
		extentthread.get().fail(result.getThrowable());
		String filePath = null;
		try {
			
		   // driver info will be present in result. so we are pointing to TestClass i.e; in xml we have 
			// class present in test folder, from there it will fetch the real class and from there it will fetch the value
			// from the field driver
			driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			filePath=getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e1) {
			// TODO Auto-generated catch block 
			e1.printStackTrace();
		}
		
		extentthread.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onFinish(context);
		extent.flush();
	}
	

}
