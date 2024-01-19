package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountLoginPage;
import pageObjects.MyAccountPage;
import pageObjects.StoreHomePage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass
{
	StoreHomePage storeHomePage;
	AccountLoginPage accLoginPage;
	MyAccountPage myAccountPage;
	
	@Test (groups= {"Sanity","Master"})
	public void verify_Login() 
	{
		logger.info("*** Starting TC_002_LoginTest ****");
		logger.debug("Capturing Application debug logs.....");
		try 
		{
			storeHomePage = new StoreHomePage(driver);
			
			storeHomePage.clickOnMyAccountDropdown();
			logger.info("Clicked on MyAccount drop-down");
			
			storeHomePage.clickOnLoginLink();
			logger.info("Clicked on Login Link under MyAccount.");
			
			accLoginPage = storeHomePage.enterToAccountLoginPage();
			
			logger.info("Entering valid login details.");
			myAccountPage = accLoginPage.enterToMyAccount(getProperty("email"),getProperty("password"));
			
			boolean targetPage = myAccountPage.isMyAccountPageExists();
			
			logger.info("Validating Page Existance");
			Assert.assertEquals(targetPage, true,"Msg doesn't match");
		} 
		catch (Exception e) 
		{
			Assert.fail("An exception occurred : "+e.getMessage());
		}
		logger.info("*** Finished TC_002_LoginTest ****");
	}
}
