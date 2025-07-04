package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;

	
/**
 * Test Case: Check presence of HomePage
 * Steps: 
 1) Navigate to the application URL.
 2) Verify title of home page.
 */

public class TC01_HomePage_Load_Test extends BaseClass {
		
		@Test
		public void checkPresenceOfHomepage() {
			
			// 1. Launch the application URL
			String applicationUrl = properties.getProperty("appURL");
			driver.get(applicationUrl);
			logger.info("Navigated to application URL: " + applicationUrl);
			
			// 2. Verifying the title of home page.
			HomePage homePage = new HomePage(driver);
			logger.info("Verifying if the homepage is displayed");
			boolean isHomePageDisplayed = homePage.isHomePageExists();
			
			Assert.assertTrue(isHomePageDisplayed, "Application Launch failed: Homepage not displayed");
			
		}
		
		
	
}
