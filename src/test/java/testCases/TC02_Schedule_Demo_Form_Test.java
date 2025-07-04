package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.ScheduleDemoPage;

public class TC02_Schedule_Demo_Form_Test extends BaseClass {

		@Test
		public void testScheduleDemoForm() {
			
			//1. Launch the application URL
			String applicationUrl = properties.getProperty("appURL");
			driver.get(applicationUrl);
			logger.info("Navigated to application URL: " + applicationUrl);
			
			//2. Navigate to Watch Demo page from Home page
			HomePage homePage = new HomePage(driver);
			homePage.clickOnWatchDemoButton();
			logger.info("Clicked on 'Watch Demo' button.");
			
			//3. Fill Details in watch demo form
			ScheduleDemoPage scheduleDemoPage = new ScheduleDemoPage(driver);
			
			logger.info("Entering user details...");
			scheduleDemoPage.setFirstName(properties.getProperty("firstName"));
			logger.info("Entered first name...");
			
			scheduleDemoPage.setLastName(properties.getProperty("lastName"));
			logger.info("Entered last name...");
			
			scheduleDemoPage.setEmail(properties.getProperty("email"));
			logger.info("Entered email...");
			
			scheduleDemoPage.setCompanyName(properties.getProperty("companyName"));
			logger.info("Entered company name...");
			
			scheduleDemoPage.setPhoneNumber(properties.getProperty("phoneNumber"));
			logger.info("Entered phone number...");
			
			scheduleDemoPage.selectTotalManagedUnits(properties.getProperty("totalManagedUnits"));
			logger.info("Selected total managed units...");
			
			scheduleDemoPage.setJobTitle(properties.getProperty("jobTitle"));
			logger.info("Entered job title...");
			
			scheduleDemoPage.selectUserType(properties.getProperty("userType"));
			logger.info("Selected user type...");
			
			//4. Verify the text fields and watch demo button presence
			logger.info("Verifying the text fields value and demo button presence");
			Assert.assertEquals(scheduleDemoPage.verifyVisibilityOfWatchDemoButton(), true);
			Assert.assertEquals(scheduleDemoPage.getTxtFirstname().getAttribute("value"), "john");
			Assert.assertEquals(scheduleDemoPage.getTxtLasttname().getAttribute("value"), "watson");
			Assert.assertEquals(scheduleDemoPage.getTxtEmail().getAttribute("value"), "john123@email.com");
			Assert.assertEquals(scheduleDemoPage.getTxtJobTitle().getAttribute("value"), "Associate");
			logger.info("text fields and demo button validation is successful");
			
		}
}
