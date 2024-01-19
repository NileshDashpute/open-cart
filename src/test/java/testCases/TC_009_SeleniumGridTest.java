package testCases;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class TC_009_SeleniumGridTest 
{
	
  @Test
  public void f() throws MalformedURLException, InterruptedException 
  {
	  DesiredCapabilities cap = new DesiredCapabilities();
	  cap.setPlatform(Platform.WINDOWS);
	  cap.setBrowserName("chrome");
	  
	  WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
	  driver.get("https://www.google.com/");
	  Thread.sleep(15000);
	  driver.quit();
  }
}
