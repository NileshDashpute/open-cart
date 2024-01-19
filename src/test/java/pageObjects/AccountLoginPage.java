package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountLoginPage extends BasePage
{

	public AccountLoginPage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy (xpath="//input[@id='input-email']")
	WebElement email;
	
	@FindBy (xpath="//input[@id='input-password']")
	WebElement passWord;
	
	@FindBy (xpath="//input[@value='Login']")
	WebElement loginBtn;
	
	public void enterEmail(String eMail) 
	{
		email.sendKeys(eMail);
	}
	
	public void enterPassWord(String pass_Word) 
	{
		passWord.sendKeys(pass_Word);
	}
	
	public void clickOnLoginBtn() 
	{
		loginBtn.click();
	}
	
	public String getAdminPageTitle() 
	{
		return driver.getTitle();
	}
	
	public MyAccountPage enterToMyAccount(String eMail, String passWord) 
	{
		enterEmail(eMail);
		enterPassWord(passWord);
		clickOnLoginBtn();
		return new MyAccountPage(driver);
	}

}
