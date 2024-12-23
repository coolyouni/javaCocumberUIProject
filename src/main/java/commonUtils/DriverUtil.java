package commonUtils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverUtil {
	
	private static Config config = new Config();
    public static long DEFAULT_WAIT = 20;
    public static WebDriver driver;
    //private static final long DEFAULT_WAIT = config.getDefaultTimeout();

    public static WebDriver getDefaultDriver() {
        if (driver != null) {
            return driver;
        }

        // Setup WebDriver based on the specified browser
        String preferredDriver = config.getBrowser().toLowerCase();
        boolean headless = config.isHeadless();

        switch (preferredDriver) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--disable-gpu"); // For compatibility
                }
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    edgeOptions.addArguments("--headless");
                    edgeOptions.addArguments("--disable-gpu"); // Edge-specific compatibility
                }
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                System.out.println("No valid browser specified. Defaulting to Chrome.");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_WAIT)); // Use Duration with static default wait// Updated to use Duration
        if (!headless) {
        driver.manage().window().maximize(); // Maximize the window
        }
        return driver;
    }

    public static WebElement waitAndGetElementByCssSelector(WebDriver driver, String selector, int seconds) {
        By selection = By.cssSelector(selector);
        return (new WebDriverWait(driver, Duration.ofSeconds(seconds))).until(
                ExpectedConditions.visibilityOfElementLocated(selection));
    }

    public static void closeDriver() {
        if (driver != null) {
            try {
                driver.quit(); // Quit the session completely
            } catch (NoSuchSessionException nsse) {
                System.out.println("Session already closed.");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                driver = null; // Ensure driver is set to null to prevent reuse
            }
        }
    }
}
