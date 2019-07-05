package com.bpci.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter{
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext context) {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/myReport.html");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("BPCI_API_Testing_Report");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Project Name", "BPCI_API");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "Sunil");
		
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "TEST CASE PASSED IS " + result.getName());
		
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName());
		test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable());
		
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "TEST CASE SKIPPED IS " + result.getName());
		
	}

	public void onFinish(ITestContext context) {
		extent.flush();
		
	}

}
