package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.DashboardPage;
import pageObjects.AdminPage;
import testBase.BaseClass;

public class TC_007_AdminLoginTest extends BaseClass
{
	AdminPage adminPage;
	DashboardPage dashboardPage;
	
  @Test
  public void checkLogin()
  {
	  
	  adminPage = new AdminPage(driver);
	  dashboardPage = adminPage.enterToDashBoard(getProperty("username"), getProperty("password1"));
	  
	  
	  String actTitle = dashboardPage.getDashboardPageTitle();
	  String expTitle = "Dashboard";
	  
	  Assert.assertEquals(actTitle, expTitle,"Title doesn't match");
  }
}
