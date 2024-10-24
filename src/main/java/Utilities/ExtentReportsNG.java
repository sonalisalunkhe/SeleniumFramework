package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {
	public static ExtentReports getReportObject() {
		//ExtentReports, ExtentSparksReporter
		
		String path=System.getProperty("user.dir")+"//Reports//index.html";
		
		ExtentSparkReporter reporter=new  ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Result");
		reporter.config().setReportName("Web Automation");
		
		ExtentReports extent=new  ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "sonali");
		return extent;
	}

}
