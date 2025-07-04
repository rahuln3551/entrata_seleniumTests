package testCases;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;

public class TC04_Validate_FooterSection_BrokenLinks extends BaseClass {
		
	@Test
	public void testBrokenLinksInFooterSection() {
		
		//1. Launch the application URL
		String applicationUrl = properties.getProperty("appURL");
		driver.get(applicationUrl);
		logger.info("Navigated to application URL: " + applicationUrl);
		
		//2. Capture URLs from footer section
		HomePage homePage = new HomePage(driver) ;
		
		//3. Validate for broken links
				List <String> brokenLinks = new ArrayList();
				for(WebElement link : homePage.getFooterSectionLinks()) {
					String url = link.getAttribute("href");
					 if (url == null || url.trim().isEmpty() || url.startsWith("javascript")) {
			                continue;
			            }

			            try {
			                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			                connection.setRequestMethod("HEAD");
			                connection.connect();
			                int responseCode = connection.getResponseCode();

			                if (responseCode >= 400) {
			                    brokenLinks.add(url + "  " + responseCode);
			                }

			            } catch (Exception e) {
			                brokenLinks.add(url + "  Exception: " + e.getMessage());
			            }
				}
				
				logger.info("Status code of links captured");
			
			//4.verifying presence of broken links
				Assert.assertTrue(brokenLinks.isEmpty(), "Footer section has broken links");
				logger.info("No presence of broken links and test passed");
				
	}
}
