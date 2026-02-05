package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.TestBase;
import utilities.DataProviders;

public class TC003_LoginDDT extends TestBase
{
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven")
	public void verify_loginDDT(String username,String password,String result) 
	{
		try {
		logger.info("****Starting TC003_LoginDDT*****");
		
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.enterEmail(username);
		lp.enterPassword(password);
		lp.clickLogin();
		
		MyAccountPage myacc=new MyAccountPage(driver);
		boolean status=myacc.verifyAccountStatus();
		
		if(result.equalsIgnoreCase("Valid"))
		{
			if(status==true)
			{
				myacc.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(result.equalsIgnoreCase("Invalid"))
		{
			if(status==true)
			{
				myacc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("****Completed TC003_LoginDDT*****");
	}
	

}
