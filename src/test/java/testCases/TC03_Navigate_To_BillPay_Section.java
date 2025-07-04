package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.BillPay;
import pageObjects.HomePage;

public class TC03_Navigate_To_BillPay_Section extends BaseClass {

	
	@Test
	public void checkPresenceOfBillPaySection() {
		
		//1. Launch the application URL
		String applicationUrl = properties.getProperty("appURL");
		driver.get(applicationUrl);
		logger.info("Navigated to application URL: " + applicationUrl);
		
		//2. Navigating to products section
		HomePage homePage = new HomePage(driver);
		homePage.hoverOverProducts();
		logger.info("Navigated to products section");
		
		//3. Click on Bill Pay section
		homePage.clickOnBillPaySection();
		logger.info("Clicked on bill pay section");
		
		//4. Verify if page is displayed and page title
		BillPay billPay = new BillPay(driver);
		logger.info("verifying the presence of Bill pay page");
		boolean isBillPayPageExists = billPay.isBillPayPageExists();
		Assert.assertTrue(isBillPayPageExists, "Bill Pay Page not found");
		logger.info("Bill pay page is launched and test case passed");
	}	
	
}
