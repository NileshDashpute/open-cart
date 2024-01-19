package pageObjects;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage 
{
	public WebDriver driver;

	public BasePage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement elementWait(WebElement element) 
	{
		WebDriverWait elementWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement ele = elementWait.until(ExpectedConditions.elementToBeClickable(element));
		return ele;
	}
	public Alert alertWait() 
	{
		WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());
		return alert;
	}
}
