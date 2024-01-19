package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountLoginPage;
import pageObjects.MyAccountPage;
import pageObjects.StoreHomePage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass
{
	StoreHomePage storeHomePage;
	AccountLoginPage accLoginPage;
	MyAccountPage myAccountPage;

	@Test(groups={"Master", "Regression"}, dataProvider="LoginData", dataProviderClass=DataProviders.class)
	public void verify_LoginDDT(String email, String password, String exp) 
	{
		logger.info("*** Starting TC_003_LoginDDT ****");
		try 
		{
			storeHomePage = new StoreHomePage(driver);
			storeHomePage.clickOnMyAccountDropdown(); 
			storeHomePage.clickOnLoginLink(); 

			accLoginPage = storeHomePage.enterToAccountLoginPage();

			myAccountPage = accLoginPage.enterToMyAccount(email,password);
			boolean targetPage = myAccountPage.isMyAccountPageExists();
			
			if (exp.equalsIgnoreCase("Valid")) 
			{
				if (targetPage==true) 
				{
					myAccountPage.clickOnLogoutLink();
					Assert.assertTrue(true);
				}
				else 
				{
					Assert.assertTrue(false);
				}

			}
			else if (exp.equalsIgnoreCase("Invalid")) 
			{
				if (targetPage==true) 
				{
					myAccountPage.clickOnLogoutLink();
					Assert.assertTrue(false);
				}
				else 
				{
					Assert.assertTrue(true);
				}
			}


		} 
		catch (Exception e) 
		{
			Assert.fail("An exception occurred : "+e.getMessage());
		}
		logger.info("*** Finished TC_003_LoginDDT ****");
	}

}
