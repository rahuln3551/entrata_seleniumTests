package testCases;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import utilities.ConfigReader;

public class BaseClass {

	 
    public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>(); // ThreadLocal to maintain separate WebDriver instances for each thread
    public WebDriver driver;
    public Logger logger; // Logger instance for logging
    public Properties properties;
    SoftAssert softAssert = new SoftAssert(); // SoftAssert for test validations


    // Setup method to initialize WebDriver before test class execution
    @BeforeClass(groups = { "Sanity", "Regression", "Master", "Datadriven" })
    @Parameters({ "os", "browser" })
    public void setUp(@Optional String os, @Optional String br) throws IOException {
        ConfigReader configReader = new ConfigReader();
        properties = configReader.getProperties(); // Load configuration properties
       
        logger = (Logger) LogManager.getLogger(this.getClass()); // Initialize logger

        // Determine execution environment and initialize WebDriver accordingly
        if (properties.getProperty("execution_env").equalsIgnoreCase("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            configureOSCapabilities(os, capabilities);
            configureBrowserCapabilities(br, capabilities);
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } 
        else {
            driver = initializeLocalDriver(br);
        }

        // Configure WebDriver settings
        if (driver != null) {
            setDriver(driver); // Set WebDriver in ThreadLocal
            driver = getDriver();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            logger.info("WebDriver initialized for: " + br);
        } else {
            logger.error("Failed to initialize WebDriver for browser: " + br);
        }
    }

    // Set WebDriver instance in ThreadLocal
    public static void setDriver(WebDriver driver) {
        tdriver.set(driver);
    }

    // Get WebDriver instance from ThreadLocal
    public static WebDriver getDriver() {
        return tdriver.get();
    }

    // Configure DesiredCapabilities based on the OS
    private void configureOSCapabilities(@Optional String os, DesiredCapabilities capabilities) {
        switch (os.toLowerCase()) {
        case "windows":
            capabilities.setPlatform(Platform.WIN11);
            break;
        case "linux":
            capabilities.setPlatform(Platform.LINUX);
            break;
        case "mac":
            capabilities.setPlatform(Platform.MAC);
            break;
        default:
            logger.error("Invalid OS: " + os);
        }
    }

    // Configure DesiredCapabilities based on the browser
    private void configureBrowserCapabilities(@Optional String browser, DesiredCapabilities capabilities) {
        switch (browser.toLowerCase()) {
        case "chrome":
            capabilities.setBrowserName("chrome");
            break;
        case "edge":
            capabilities.setBrowserName("MicrosoftEdge");
            break;
        case "firefox":
            capabilities.setBrowserName("firefox");
            break;
        default:
            logger.error("No matching browser: " + browser);
        }
    }

    // Initialize WebDriver for local execution
    private WebDriver initializeLocalDriver(@Optional String browser) {
        switch (browser.toLowerCase()) {
        case "chrome": {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-notifications", "--disable-popup-blocking",
                    "--disable-extensions", "disable-infobars", "--ignore-certificate-errors");
            chromeOptions.setAcceptInsecureCerts(true);
            return new ChromeDriver(chromeOptions);
        }
        case "edge": {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--disable-notifications", "--disable-popup-blocking",
                    "--disable-extensions", "disable-infobars", "--ignore-certificate-errors");
            edgeOptions.setAcceptInsecureCerts(true);
            return new EdgeDriver(edgeOptions);
        }
        case "firefox": {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--disable-notifications", "--disable-popup-blocking",
                    "--disable-extensions", "disable-infobars", "--ignore-certificate-errors");
            firefoxOptions.setAcceptInsecureCerts(true);
            firefoxOptions.addPreference("dom.webnotifications.enabled", false);
            firefoxOptions.addPreference("dom.disable_open_during_load", true);
            firefoxOptions.addPreference("extensions.showRecommendedInstalled", false);
            return new FirefoxDriver(firefoxOptions);
        }
        default: {
            logger.error("No matching browser for local execution: " + browser);
            return null;
        }
        
    }

   
    }

    // Teardown method to quit WebDriver after test class execution
    @AfterClass(groups = { "Sanity", "Regression", "Master", "Datadriven" })
    public void tearDown() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
            tdriver.remove(); // Remove WebDriver from ThreadLocal
            logger.info("WebDriver quit and removed from ThreadLocal.");
        }
    }

    // Utility method to generate a random alphabetic string
    public String generateString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    // Utility method to generate a random numeric string
    public String generateNumber() {
        return RandomStringUtils.randomNumeric(5);
    }

    // Utility method to generate a random alphanumeric string
    public String generateAlphaNumeric() {
        String generatedString = RandomStringUtils.randomAlphabetic(3);
        String generatedNumber = RandomStringUtils.randomNumeric(3);
        return generatedString + "@" + generatedNumber;
    }
}
