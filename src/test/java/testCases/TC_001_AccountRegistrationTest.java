package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterPage;
import pageObjects.StoreHomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass
{
	StoreHomePage storeHomePage;
	AccountRegisterPage accRegPage;

	@Test(groups = {"Regression","Master"})
	public void verify_Account_Registration()
	{
		logger.debug("Application logs started.....");
		logger.info("*** Starting TC_001_AccountRegistrationTest ****");
		try 
		{
			storeHomePage = new StoreHomePage(driver);
			
			storeHomePage.clickOnMyAccountDropdown();
			logger.info("Clicked on MyAccount drop-down");
			
			storeHomePage.clickOnRegisterLink();
			logger.info("Clicked on Register Link.");
			
			logger.info("Entering customer Details.");
			accRegPage = storeHomePage.enterToAccountRegisterPage();
			accRegPage.setFirstName(randomString().toUpperCase());
			accRegPage.setLastName(randomString().toUpperCase());
			accRegPage.setEmail(randomString()+"@gmail.com");
			accRegPage.setTelePhone(randomNumber());

			String password = randomAlphaNumeric();
			accRegPage.setPassword(password);
			accRegPage.setConfirmPassword(password);

			accRegPage.clickOnPolicy();
			accRegPage.clickOnContinueBtn();
			logger.info("Clicked on Continue Button.");

			String actMsg = accRegPage.getConfirmationMsg();
			String expMsg = "Your Account Has Been Created!";
			
			logger.info("Validating expected Message");
			Assert.assertEquals(actMsg, expMsg,"Msg doesn't match");
		} 
		catch (Exception e) 
		{
			logger.error("test failed....");
			Assert.fail("An exception occur : "+e.getMessage());
		}
		logger.debug("Application logs end.....");
		logger.info("*** Finished TC_001_AccountRegistrationTest ****");
	}
}
