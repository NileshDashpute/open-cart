package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.StoreHomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_004_SearchProductTest extends BaseClass 
{

	@Test(groups= {"Sanity","Master"})
	public void verify_pruductSearch() throws InterruptedException 
	{
		logger.info(" Starting TC_004_SearchProductTest ");

		try 
		{
			StoreHomePage storeHomePage=new StoreHomePage(driver);
			
			//storeHomePage.enterProductName("iPhone");
			storeHomePage.enterProductName("mac");
			Thread.sleep(3000);

			storeHomePage.clickOnSearchBtn();
			Thread.sleep(3000);

			SearchPage searchPage=new SearchPage(driver);
			searchPage.isProductExist("MacBook");

			Assert.assertEquals(searchPage.isProductExist("MacBook"),true);

		} 
		catch (Exception e) 
		{
			Assert.fail();
		}
		logger.info(" Finished TC_004_SearchProductTest ");
	}
}
