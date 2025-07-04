package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import testCases.BaseClass;

public class Listener implements ITestListener {

	public void onTestFailure(ITestResult result) {
        try {
            String screenshotPath = captureScreenshot(result.getName());
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	private String captureScreenshot(String testName) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String screenshotPath = System.getProperty("user.dir") + "\\screenshots\\" + testName + "_" + timestamp + ".png";
        File sourceFile = ((TakesScreenshot) BaseClass.getDriver()).getScreenshotAs(OutputType.FILE);
        File targetFile = new File(screenshotPath);
        sourceFile.renameTo(targetFile);
        return screenshotPath;
    }
}
