package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StoreHomePage extends BasePage 
{
	public StoreHomePage(WebDriver driver) 
	{
		super(driver);
	}

	public String getStoreHomePageTitle() 
	{
		return driver.getTitle();
	}

	@FindBy (xpath = "//span[normalize-space()='My Account']")
	WebElement myAccountDropDown;

	@FindBy (xpath = "//a[text()='Register']")
	WebElement registerLink;

	@FindBy (xpath = "//a[text()='Login']")
	WebElement loginLink;
	
	@FindBy (xpath = "//input[@placeholder='Search']")
	WebElement searchBox;
	
	@FindBy (xpath = "//i[@class='fa fa-search']")
	WebElement searchBtn;

	public void clickOnMyAccountDropdown() 
	{
		//myAccountDropDown.click();
		elementWait(myAccountDropDown).click();
	}

	public void clickOnRegisterLink() 
	{
		//registerLink.click();
		elementWait(registerLink).click();
	}

	public void clickOnLoginLink() 
	{
		loginLink.click();
	}
	
	public void enterProductName(String product) 
	{
		searchBox.sendKeys(product);
	}
	
	public void clickOnSearchBtn() 
	{
		searchBtn.click();
	}

	public AccountRegisterPage enterToAccountRegisterPage() 
	{
		return new AccountRegisterPage(driver);
	}

	public AccountLoginPage enterToAccountLoginPage() 
	{
		return new AccountLoginPage(driver);
	}
}
