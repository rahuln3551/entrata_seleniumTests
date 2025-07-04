package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BillPay {
	
	WebDriver driver;
	WebDriverWait wait;

	// Constructor for HomePage
	public BillPay(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set explicit wait duration
		PageFactory.initElements(driver, this);
}
	
	public boolean isBillPayPageExists() {
		try {
			return wait.until(ExpectedConditions.titleIs("Bill Pay | Entrata"));
		} catch (Exception e) {
			return false;
		}
	}
}
