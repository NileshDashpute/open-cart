package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.google.common.io.Files;



public class BaseClass 

{
	static public WebDriver driver;
	public Logger logger;
	public Properties properties;

	//Get Property from Property File
	public String getProperty(String key)
	{ 
		String propFilePath = System.getProperty("user.dir")+"./src/test/resources/config.properties";
		try 
		{
			FileReader file = new FileReader(propFilePath);
			properties = new Properties();
			properties.load(file);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		String value = properties.getProperty(key);
		return value;
	}

	//setUp Method
	@BeforeClass(groups= {"Regression","Sanity","Master"})
	@Parameters({"os","browser"})
	public void setUp(String os, String br) throws MalformedURLException 
	{
		logger = LogManager.getLogger(this.getClass()); //loading log4j

		//Setting Remote
		if (getProperty("execution_env").equalsIgnoreCase("remote")) 
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			//Setting OS
			if (os.equalsIgnoreCase("windows")) 
			{
				capabilities.setPlatform(Platform.WINDOWS);
			}
			else if (os.equalsIgnoreCase("mac")) 
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else 
			{
				System.out.println("No matching os...");
				return;
			}

			//Setting Browser
			switch (br.toLowerCase()) 
			{
			case "chrome": capabilities.setBrowserName("chrome");break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge");break;
			case "firefox": capabilities.setBrowserName("firefox");break;
			default:System.out.println("No matching browser...");return;
			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}

		//Setting Local
		else if (getProperty("execution_env").equalsIgnoreCase("local")) 
		{
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--guest");

			switch(br.toLowerCase()) 
			{
			case "chrome" : driver = new ChromeDriver();break;
			case "edge" : driver = new EdgeDriver(options);break;
			case "firefox" : driver = new FirefoxDriver();break;
			default : System.out.println("No matching browser..");
			return;
			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(getProperty("appURL1"));

		logger.info("Suite is started");
	}

	//Tear Down
	@AfterClass(groups= {"Regression","Sanity","Master"})
	public void tearDown()
	{
		driver.quit();
	}

	//windowHandle
	public void swithToWindow(String title) 
	{
		Set<String> windowIDs = driver.getWindowHandles();
		for (String windowID : windowIDs) 
		{
			String windowTitle = driver.switchTo().window(windowID).getTitle();
			if (windowTitle.equals(title)) 
			{
				driver.switchTo().window(windowID);
				break;
			}
		}
	}

	//Random String Methods
	public String randomString() 
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomNumber() 
	{
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}

	public String randomAlphaNumeric()
	{
		String str = RandomStringUtils.randomAlphabetic(3);
		String num = RandomStringUtils.randomNumeric(3);
		return (str+"@"+num);
	}

	//Capture Screenshot
	public String captureScreen(String testName) 
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
		File sourcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destFilePath = System.getProperty("user.dir")+"\\screenshots\\"+testName+"_"+timeStamp+".png";
		File destFile = new File(destFilePath);
		try 
		{
			Files.copy(sourcFile, destFile);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return destFilePath;
	}
}
