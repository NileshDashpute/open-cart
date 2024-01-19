package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterPage extends BasePage
{

	public AccountRegisterPage(WebDriver driver) 
	{
		super(driver);
	}

	@FindBy (xpath = "//input[@id='input-firstname']")
	WebElement firstname;

	@FindBy (xpath = "//input[@id='input-lastname']")
	WebElement lastname;

	@FindBy (xpath = "//input[@id='input-email']")
	WebElement email;

	@FindBy (xpath = "//input[@id='input-telephone']")
	WebElement telePhone;

	@FindBy (xpath = "//input[@id='input-password']")
	WebElement password;

	@FindBy (xpath = "//input[@id='input-confirm']")
	WebElement confirmPassword;

	@FindBy (xpath = "//input[@name='agree']")
	WebElement chkPolicy;

	@FindBy (xpath = "//input[@value='Continue']")
	WebElement continueBtn;

	@FindBy (xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confirmationMsg;

	public void setFirstName(String fname) 
	{
		firstname.sendKeys(fname);
	}

	public void setLastName(String lname) 
	{
		lastname.sendKeys(lname);
	}

	public void setEmail(String eMail) 
	{
		email.sendKeys(eMail);
	}

	public void setTelePhone(String tPhone) 
	{
		telePhone.sendKeys(tPhone);
	}

	public void setPassword(String pass) 
	{
		password.sendKeys(pass);
	}

	public void setConfirmPassword(String pass) 
	{
		confirmPassword.sendKeys(pass);
	}

	public void clickOnPolicy() 
	{
		//chkPolicy.click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", chkPolicy);
	}

	public void clickOnContinueBtn() 
	{
		continueBtn.click();
		
		//continueBtn.submit();
		
		//continueBtn.sendKeys(Keys.RETURN);

		//JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("arguments[1].click();", continueBtn);

		//Actions act = new Actions(driver);
		//act.moveToElement(continueBtn).click().perform();
		
		//elementWait(continueBtn);

	}

	public String getConfirmationMsg() 
	{
		try 
		{
			return confirmationMsg.getText();
		} 
		catch (Exception e) 
		{
			return e.getMessage();
		}
	}
}
