package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.StoreHomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_005_AddToCartPageTest extends BaseClass 
{
	@Test(groups= {"Sanity","Master"})
	public void verify_addToCart() throws InterruptedException 
	{
		logger.info(" Starting TC_005_AddToCartPageTest ");
		try 
		{
			StoreHomePage storeHomePage=new StoreHomePage(driver);

			storeHomePage.enterProductName("iPhone");
			Thread.sleep(3000);
			storeHomePage.clickOnSearchBtn();
			Thread.sleep(3000);

			SearchPage searchPage=new SearchPage(driver);

			if(searchPage.isProductExist("iPhone"))
			{

				searchPage.selectProduct("iPhone");
				Thread.sleep(3000);
				searchPage.setQuantity("2");
				Thread.sleep(3000);
				searchPage.addToCart();
				Thread.sleep(3000);

			}
			Assert.assertEquals(searchPage.checkConfMsg(),true);

		} 
		catch (Exception e) 
		{
			Assert.fail();
		}
		logger.info(" Finished TC_005_AddToCartPageTest ");
	}
}
