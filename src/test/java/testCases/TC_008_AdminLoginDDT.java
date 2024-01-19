package testCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pageObjects.AdminPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_008_AdminLoginDDT extends BaseClass
{
	AdminPage adminPage;
	DashboardPage dashboardPage;

	@Test(dataProvider = "LoginData2",dataProviderClass = DataProviders.class)
	public void checkLogin(String username, String password, String expected)
	{

		adminPage = new AdminPage(driver);
		dashboardPage = adminPage.enterToDashBoard(username, password);

		boolean targetPage = dashboardPage.isDashoardPageExist();

		if (expected.equalsIgnoreCase("Valid")) 
		{
			if (targetPage==true) 
			{
				dashboardPage.clickOnLogoutLink();
				Assert.assertTrue(true);
			}
			else 
			{
				Assert.assertTrue(false);
			}
		}
		else if (expected.equalsIgnoreCase("Invalid")) 
		{
			if (targetPage==true) 
			{
				dashboardPage.clickOnLogoutLink();
				Assert.assertFalse(true);
			}
			else 
			{
				Assert.assertFalse(false);
			}
		}

	}
	@AfterMethod
	public void afterEachMerthod() 
	{
		adminPage.clearUsernameAndPassword();
	}
}
