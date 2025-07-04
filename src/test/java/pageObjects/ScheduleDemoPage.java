package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScheduleDemoPage {
	
		WebDriver driver;
		WebDriverWait wait;
	
	    // Constructor for ScheduleDemoPage
		public ScheduleDemoPage(WebDriver driver) {
			this.driver = driver;
			this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set explicit wait duration
			PageFactory.initElements(driver, this);
	}
		
	    //Locators for ScheduleDemoPage
		@FindBy(xpath="//input[@id='FirstName']") 
		WebElement txtFirstname;

		@FindBy(xpath="//input[@id='LastName']") 
		WebElement txtLasttname;

		@FindBy(xpath="//input[@id='Email']") 
		WebElement txtEmail;

		@FindBy(xpath="//input[@id='Company']") 
		WebElement txtCompanyName;
		
		@FindBy(xpath="//input[@id=\"Phone\"]") 
		WebElement txtPhoneNumber;

		@FindBy(xpath="//select[@id='Unit_Count__c']") 
		WebElement dropdownTotalManagedUnits;

		@FindBy(xpath="//input[@id='Title']") 
		WebElement txtJobTitle;
		
		@FindBy(xpath="//select[@id='demoRequest']") 
		WebElement drpodownUserType;
		
		@FindBy(xpath="//button[normalize-space()='Watch Demo']")
		WebElement buttonWatchDemo;
		
		
		//Action Methods
		public void setFirstName(String fname) {
	        try {
	            wait.until(ExpectedConditions.visibilityOf(txtFirstname)).sendKeys(fname);
	        } catch (Exception e) {
	            System.out.println("Exception while setting first name: " + e.getMessage());
	        }
	    }
		
		public void setLastName(String lname) {
	        try {
	            wait.until(ExpectedConditions.visibilityOf(txtLasttname)).sendKeys(lname);
	        } catch (Exception e) {
	            System.out.println("Exception while setting last name: " + e.getMessage());
	        }
	    }
		
		public void setEmail(String email) {
	        try {
	            wait.until(ExpectedConditions.visibilityOf(txtEmail)).sendKeys(email);
	        } catch (Exception e) {
	            System.out.println("Exception while setting email: " + e.getMessage());
	        }
	    }
		
		public void setCompanyName(String companyName) {
	        try {
	            wait.until(ExpectedConditions.visibilityOf(txtCompanyName)).sendKeys(companyName);
	        } catch (Exception e) {
	            System.out.println("Exception while setting company name: " + e.getMessage());
	        }
	    }
		
		public void setPhoneNumber(String phoneNumber) {
	        try {
	            wait.until(ExpectedConditions.visibilityOf(txtPhoneNumber)).sendKeys(phoneNumber);
	        } catch (Exception e) {
	            System.out.println("Exception while setting phone number: " + e.getMessage());
	        }
	    }
		
		public void selectTotalManagedUnits(String totalManagedUnits) {
			try {
		        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOf(dropdownTotalManagedUnits));
		        Select dropdown = new Select(dropdownTotalManagedUnits);
		        dropdown.selectByVisibleText(totalManagedUnits);
		    } catch (Exception e) {
		        System.out.println("Exception while selecting total managed units: " + e.getMessage());
		    }
	    }
		
		public void setJobTitle(String jobTitle) {
	        try {
	            wait.until(ExpectedConditions.visibilityOf(txtJobTitle)).sendKeys(jobTitle);
	        } catch (Exception e) {
	            System.out.println("Exception while setting job title: " + e.getMessage());
	        }
	    }
		
		public void selectUserType(String userType) {
			try {
		        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOf(drpodownUserType));
		        Select dropdown = new Select(drpodownUserType);
		        dropdown.selectByVisibleText(userType);
		    } catch (Exception e) {
		        System.out.println("Exception while selecting User Type: " + e.getMessage());
		    }
	    }
		
		public boolean verifyVisibilityOfWatchDemoButton(){
			 try {
		            return wait.until(ExpectedConditions.visibilityOf(buttonWatchDemo)).isDisplayed();
		        } catch (Exception e) {
		            System.out.println("Exception while setting job title: " + e.getMessage());
		            return false;
		        }
		}
		
		//Getter methods for WebElements
		public WebElement getTxtFirstname() {
			return txtFirstname;
		}

		public WebElement getTxtLasttname() {
			return txtLasttname;
		}

		public WebElement getTxtEmail() {
			return txtEmail;
		}

		public WebElement getTxtCompanyName() {
			return txtCompanyName;
		}

		public WebElement getTxtPhoneNumber() {
			return txtPhoneNumber;
		}

		public WebElement getTxtJobTitle() {
			return txtJobTitle;
		}
		
}
