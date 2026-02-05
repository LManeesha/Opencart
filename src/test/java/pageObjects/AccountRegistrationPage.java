package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) 
	{
		super(driver);
		
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txt_firstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txt_lastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_email;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txt_telephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txt_password;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txt_confirmpassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkbox_agree;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btn_continue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confirmationmsg;
	
	public void setFirstName(String fname)
	{
		txt_firstname.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txt_lastname.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txt_email.sendKeys(email);
	}
	
	public void setTelephone(String phno)
	{
		txt_telephone.sendKeys(phno);
	}
	
	public void setPassword(String pwd)
	{
		txt_password.sendKeys(pwd);
	}

	public void setConfirmPassword(String confirmpwd)
	{
		txt_confirmpassword.sendKeys(confirmpwd);
	}
	
	public void clickAgree()
	{
		chkbox_agree.click();
	}
	public void clickContinue()
	{
		btn_continue.click();
		
		//btn_continue.submit();
		
		//Actions act=new Actions(driver);
		//act.moveToElement(btn_continue).click().perform();
		
		//JavascriptExecutor js=(JavascriptExecutor) driver;
		//js.executeScript("arguments[0].click();", btn_continue);
		
		//btn_continue.sendKeys(Keys.RETURN);
		
		//WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(btn_continue)).click();
		}
	public String getConfirmationmsg() 
	{
		try
		{
			return (confirmationmsg.getText());
		}
		catch(Exception e)
		{
			return (e.getMessage());
		}
	}

}
