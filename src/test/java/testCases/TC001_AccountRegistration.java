package testCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.TestBase;

public class TC001_AccountRegistration extends TestBase {
	
	    
		@Test(groups={"Regression","Master"})
		public void Account_Registration()
		{
			try
			{
			logger.info("***Starting TC001_AccountRegistration***");
			HomePage hp=new HomePage(driver);
			logger.info("***Clicked on MyAccount...");
			hp.clickMyAccount();
			logger.info("***Clicked on Register...");
			hp.clickRegister();
			
			AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
			logger.info("***Entering Customer Details...");
			regpage.setFirstName(randomString());
			regpage.setLastName(randomString());
			regpage.setEmail(randomString()+"@gmail.com");
			regpage.setTelephone(randomNumber());
			
			String password=randomAlphaNumeric();
			
			regpage.setPassword(password);
			regpage.setConfirmPassword(password);
			regpage.clickAgree();
			regpage.clickContinue();
			
			logger.info("***Validating Confirmation message..");
			String cnfmsg=regpage.getConfirmationmsg();
			if(cnfmsg.equals("Your Account Has Been Created!"))
			{
				logger.info("***Account has been created***");
			}
			else
			{
				logger.error("Test Failed");
				logger.debug("debug logs");
				Assert.assertTrue(false);
				
			}
			}
			catch(Exception e)
			{
				Assert.fail();
			}
			logger.info("*** Completed TC001_AccountRegistration ***");
		}
}
