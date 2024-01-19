package pageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage
{
	Alert alert;

	public DashboardPage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy (xpath="//h5[contains(text(),'Important')]")
	WebElement notificationBox;
	
	@FindBy (xpath="//button[@type='button'][@class='btn-close']")
	WebElement closeBtn;
	
	@FindBy (xpath ="//span[@class='hidden-xs hidden-sm hidden-md']")
	WebElement logoutLink;
	
	@FindBy (xpath="//h1[normalize-space()='Dashboard']")
	WebElement titleDashboard;
	public void acceptAlert() 
	{
		alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public void clickOnCloseBtn() 
	{
		if(notificationBox.isDisplayed())
		{
			closeBtn.click();
		}
		else 
		{
			
		}
	}
	public void clickOnLogoutLink() 
	{
		logoutLink.click();
	}
	public String getDashboardPageTitle() 
	{
		return driver.getTitle();
	}
	public boolean isDashoardPageExist() 
	{
		try 
		{
			return titleDashboard.isDisplayed();
		} catch (Exception e) 
		{
			return false;
		}
	}
}
