package testCases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.TestBase;
@Listeners(utilities.ExtentReportManager.class)
public class TC002_LoginTest extends TestBase {
	
	@Test(groups={"Sanity","Master"})
	public void logintest()
	{
		logger.info("****Starting TC002_LoginTest*****");
		
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.enterEmail(p.getProperty("username"));
		lp.enterPassword(p.getProperty("password"));
		lp.clickLogin();
		
		MyAccountPage myacc=new MyAccountPage(driver);
		boolean status=myacc.verifyAccountStatus();
		Assert.assertEquals(status, true,"Test Failed" );
		
		logger.info("****Completed TC002_LoginTest*****");
	}

}
