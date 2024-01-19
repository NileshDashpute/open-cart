package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage extends BasePage
{
	public AdminPage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy (xpath="//input[@name='username']")
	WebElement userName;
	
	@FindBy (xpath="//input[@name='password']")
	WebElement passWord;
	
	@FindBy (xpath="//button[@type='submit']")
	WebElement loginBtn;
	
	public void enterUserName(String user_Name) 
	{
		userName.sendKeys(user_Name);
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
	
	public DashboardPage enterToDashBoard(String userName, String passWord) 
	{
		enterUserName(userName);
		enterPassWord(passWord);
		clickOnLoginBtn();
		return new DashboardPage(driver);
	}
	public void clearUsernameAndPassword() 
	{
		userName.clear();
		passWord.clear();
	}
}
