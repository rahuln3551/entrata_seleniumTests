package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver driver;
	WebDriverWait wait;

	// Constructor for HomePage
	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set explicit wait duration
		PageFactory.initElements(driver, this);
	}
	
	//Page objects
	@FindBy(xpath="//a[normalize-space()='Watch demo']") 
	WebElement buttonWatchDemo;
	
	@FindBy(xpath="//nav[@role=\"navigation\"]/div[1]")
	WebElement productNavigation;
	
	@FindBy(xpath="//div[normalize-space()='Bill Pay']")
	WebElement billPaySection;
	
	@FindBy(xpath="//footer[@class=\"footer_component\"]//a")
	List <WebElement> footerSectionLinks;
	
	
		public boolean isHomePageExists() {
			try {
				return driver.getTitle().equals("Property Management Software | Entrata®");

			} catch (Exception e) {
				return false;
			}
		}
		
		public void clickOnWatchDemoButton() {
			try {
				wait.until(ExpectedConditions.visibilityOf(buttonWatchDemo)).click();
			} catch(Exception e) {
				System.out.println("Exception while clicking on watch demo button: " + e.getMessage());
			}
			
		}
		
		public void hoverOverProducts() {
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(wait.until(ExpectedConditions.visibilityOf(productNavigation))).perform();
			} catch(Exception e) {
				System.out.println("Exception while hovering to products section: "+ e.getMessage());
			}
		}
		
		public void clickOnBillPaySection() {
			try {
				wait.until(ExpectedConditions.visibilityOf(billPaySection)).click();
			} catch(Exception e) {
				System.out.println("Exception while navigating to bill pay section: " + e.getMessage());
			}
		}
		
		public List<WebElement> getFooterSectionLinks() {
			try{
				return wait.until(ExpectedConditions.visibilityOfAllElements(footerSectionLinks));
			} catch(Exception e) {
				System.out.println("Exception while capturing footer links: " + e.getMessage());
				return new ArrayList<>();
			}
		}

	
}
