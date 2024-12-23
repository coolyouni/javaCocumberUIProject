package listeners;

import commonUtils.ScreenshotUtil;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestListener implements ITestListener {
    private WebDriver driver;

    // Method to set the WebDriver instance
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void onTestFailure(Scenario scenario) {
        String testName = scenario.getName();
        Reporter.log("Scenario " + testName + " failed.");

        if (driver != null) {
            try {
                // Take a screenshot
                String screenshotPath = ScreenshotUtil.takeScreenshot(driver, testName);
                // Attach the screenshot to the report
                attachScreenshotToReport(scenario, screenshotPath);
            } catch (Exception e) {
                Reporter.log("Failed to take screenshot: " + e.getMessage());
            }
        } else {
            Reporter.log("WebDriver instance is null. Cannot take a screenshot.");
        }
    }

    private void attachScreenshotToReport(Scenario scenario, String screenshotPath) {
        try {
            // Read the screenshot file into a byte array
            byte[] screenshotBytes = Files.readAllBytes(new File(screenshotPath).toPath());
            // Attach the screenshot to the Cucumber report
            scenario.attach(screenshotBytes, "image/png", "Screenshot for " + scenario.getName());
        } catch (IOException e) {
            Reporter.log("Failed to attach screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onStart(ITestContext context) {
        Reporter.log("Starting tests in context: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        Reporter.log("Finished tests in context: " + context.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Reporter.log("Test " + result.getName() + " succeeded.");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Reporter.log("Test " + result.getName() + " was skipped.");
    }
}
