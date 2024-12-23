package commonUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScreenshotUtil {
    private static final Logger LOGGER = Logger.getLogger(ScreenshotUtil.class.getName());
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor(); 
    // For async saving

    public static String takeScreenshot(WebDriver driver, String testName) {
        // Format timestamp for uniqueness
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = "screenshots/" + testName + "_" + timestamp + ".jpeg";

        // Ensure screenshots directory exists
        File directory = new File("screenshots");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Capture screenshot as file
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Save screenshot asynchronously
        executorService.submit(() -> saveScreenshotAsync(screenshot, filePath));
        
        return filePath; // Return file path for reference
    }

    // Asynchronous screenshot saving method
    private static void saveScreenshotAsync(File screenshot, String filePath) {
        try {
            FileUtils.copyFile(screenshot, new File(filePath));
            LOGGER.log(Level.INFO, "Screenshot saved at: " + filePath);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to save screenshot: " + filePath, e);
        }
    }

    // Method to gracefully shutdown the executor
    public static void shutdown() {
        executorService.shutdown();
    }
}
