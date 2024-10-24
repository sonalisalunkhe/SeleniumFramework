package Base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utilities.ExtentReportsNG;

public class Listeners  extends TestBase implements ITestListener{
	ExtentTest test;
	ExtentReports extent=ExtentReportsNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		test=extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Pass");
	  }

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().log(Status.FAIL, "Test Pass");
		extentTest.get().fail(result.getThrowable());
		try {
			driver=(WebDriver)  result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String path = null;
		try {
			 path=getScreenshoots(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(path, result.getMethod().getMethodName());

	  }

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, "Test Pass");

	  }	
	@Override
	
	  
	public void onFinish(ITestContext context) {
		extent.flush();
	  }

}
