package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
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

import testBase.TestBase;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	
	public void onStart(ITestContext context) 
	  {
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report-"+timestamp+".html";
		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports\\"+repName);
		
		sparkReporter.config().setDocumentTitle("Opencart Automation Report");
		sparkReporter.config().setReportName("Opencart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("Username", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QAT");
		
		String os=context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser=context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		List<String> includedGroups=context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	  }
	

	public void onTestSuccess(ITestResult result) {
		
	  test=extent.createTest(result.getTestClass().getName());
	  test.assignCategory(result.getMethod().getGroups());
	  test.log(Status.PASS, "Test is Passed : "+result.getName());
	
	    
	  }
	public void onTestFailure(ITestResult result) 
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test is Failed : "+result.getName());
		test.log(Status.INFO, "Error is : "+result.getThrowable().getMessage());

		try {
			String impPath = new TestBase().captureScreen(result.getName());
			test.addScreenCaptureFromPath(impPath);
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	  }
	
	public void onTestSkipped(ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test is Skipped : "+result.getName());
		test.log(Status.INFO, "Error is : "+result.getThrowable().getMessage());
	  }
	
	public void onFinish(ITestContext context) {
		
		extent.flush();
		
		String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
		File ExtentReport=new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(ExtentReport.toURI());
			
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
	  }
	
	

}
