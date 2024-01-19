package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports report;
	public ExtentTest test;

	String reportName;
	String timeStamp;

	public void reportInit() 
	{
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());   //Time stamp for report name
		reportName = "Test-Report-"+timeStamp+".html"; // reportName with timeStamp
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+reportName); //reprtLocation

		//report configurations.
		sparkReporter.config().setDocumentTitle("OpenCart Report");
		sparkReporter.config().setReportName("OpenCart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);

		//attaching Report.
		report = new ExtentReports();
		report.attachReporter(sparkReporter);

		//Providing Info in Report
		report.setSystemInfo("Application", "OpenCart");
		report.setSystemInfo("Module", "Admin");
		report.setSystemInfo("SubModule", "Customers");
		report.setSystemInfo("UserName", System.getProperty("user.name"));
		report.setSystemInfo("Environment", "QA");
	}

	public void onStart(ITestContext testContext) 
	{
		reportInit();

		String os = testContext.getCurrentXmlTest().getParameter("os");
		report.setSystemInfo("Operating System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		report.setSystemInfo("Browser", browser);

		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) 
		{
			report.setSystemInfo("Groups", includedGroups.toString());
		}
		
		List<String> excludedGroups = testContext.getCurrentXmlTest().getExcludedGroups();
		if (!excludedGroups.isEmpty()) 
		{
			report.setSystemInfo("Groups", excludedGroups.toString());
		}
	}

	public void onTestSuccess(ITestResult result) 
	{
		test = report.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());//to Display Groups in Report
		test.log(Status.PASS, result.getName()+" got executed succesfully.");
	}

	public void onTestFailure(ITestResult result) 
	{
		test = report.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.FAIL, result.getName()+" got failed.");
		test.log(Status.INFO, result.getThrowable().getMessage());

		String imgPath = new BaseClass().captureScreen(result.getName());
		test.addScreenCaptureFromPath(imgPath); 

	}

	public void onTestSkipped(ITestResult result) 
	{
		test = report.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.SKIP, result.getName()+" got skipped.");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext context) 
	{
		report.flush();
	}
}

