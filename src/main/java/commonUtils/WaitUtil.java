package commonUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class WaitUtil {

    private static final long DEFAULT_TIMEOUT;

    static {
        Config config = new Config();
        DEFAULT_TIMEOUT = config.getDefaultTimeout(); // Load default timeout from Config
    }

    // Helper method to create WebDriverWait with a specific timeout
    private static WebDriverWait getWait(WebDriver driver, long timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    // Generic helper to wait for any condition
    public static <V> V waitForCondition(WebDriver driver, ExpectedCondition<V> condition, long timeoutInSeconds) {
        try {
            return getWait(driver, timeoutInSeconds).until(condition);
        } catch (TimeoutException e) {
            throw new TimeoutException("Condition was not met within " + timeoutInSeconds + " seconds", e);
        }
    }

    // Overloaded method for default timeout
    public static <V> V waitForCondition(WebDriver driver, ExpectedCondition<V> condition) {
        return waitForCondition(driver, condition, DEFAULT_TIMEOUT);
    }

    public static WebElement waitForElementToBeVisible(WebDriver driver, By locator, long timeoutInSeconds) {
        return waitForCondition(driver, ExpectedConditions.visibilityOfElementLocated(locator), timeoutInSeconds);
    }

    public static WebElement waitForElementToBeVisible(WebDriver driver, By locator) {
        return waitForElementToBeVisible(driver, locator, DEFAULT_TIMEOUT);
    }

    public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element, long timeoutInSeconds) {
        return waitForCondition(driver, ExpectedConditions.elementToBeClickable(element), timeoutInSeconds);
    }

    public static WebElement waitForElementToBeClickable(WebDriver driver, By locator, long timeoutInSeconds) {
        return waitForCondition(driver, ExpectedConditions.elementToBeClickable(locator), timeoutInSeconds);
    }

    public static WebElement waitForElementToBeClickable(WebDriver driver, By locator) {
        return waitForElementToBeClickable(driver, locator, DEFAULT_TIMEOUT);
    }

    public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element) {
        return waitForElementToBeClickable(driver, element, DEFAULT_TIMEOUT);
    }

    public static WebElement waitForPresenceOfElement(WebDriver driver, By locator, long timeoutInSeconds) {
        return waitForCondition(driver, ExpectedConditions.presenceOfElementLocated(locator), timeoutInSeconds);
    }

    public static WebElement waitForPresenceOfElement(WebDriver driver, By locator) {
        return waitForPresenceOfElement(driver, locator, DEFAULT_TIMEOUT);
    }

    public static boolean waitForTextToBePresentInElement(WebDriver driver, WebElement element, String text, long timeoutInSeconds) {
        return waitForCondition(driver, ExpectedConditions.textToBePresentInElement(element, text), timeoutInSeconds);
    }

    public static boolean waitForTextToBePresentInElement(WebDriver driver, WebElement element, String text) {
        return waitForTextToBePresentInElement(driver, element, text, DEFAULT_TIMEOUT);
    }

    public static boolean waitForElementToBeInvisible(WebDriver driver, By locator, long timeoutInSeconds) {
        try {
            return waitForCondition(driver, ExpectedConditions.invisibilityOfElementLocated(locator), timeoutInSeconds);
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static boolean waitForElementToBeInvisible(WebDriver driver, By locator) {
        return waitForElementToBeInvisible(driver, locator, DEFAULT_TIMEOUT);
    }


    public static WebElement fluentWaitForElementToBeClickable(WebDriver driver, By locator) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(3))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, TimeoutException.class);

        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(locator);
                if (element.isEnabled() && element.isDisplayed()) {
                    return element;
                }
                return null;
            }
        });
    }

    // Method to scroll the page until the element is visible and clickable
    public static void scrollUpUntilElementClickable(WebDriver driver, By elementLocator) {
        // Find the element
        WebElement element = driver.findElement(elementLocator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Scroll the element into view (upwards or downwards)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        // Optionally, wait until the element is both visible and clickable
        wait.until(ExpectedConditions.elementToBeClickable(element));

        // Scroll the page upwards (if needed) until the element is in the viewport
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-250);");  // Scroll up by 250px or adjust as necessary

        // Now the element is visible and clickable
        // Additional logic can go here, such as clicking the element, if needed
        element.click();  // Click the element if necessary
    }

    public static void waitForIframeAndSwitch(WebDriver driver, By iframeLocator, Duration timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);

            // Wait until the iframe is visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(iframeLocator));

            // Switch to the iframe once it's visible
            WebElement iframe = driver.findElement(iframeLocator);
            driver.switchTo().frame(iframe);

        } catch (TimeoutException e) {
            System.out.println("Timed out waiting for iframe to be visible: " + iframeLocator);
            throw e;
        } catch (NoSuchElementException e) {
            System.out.println("Iframe not found: " + iframeLocator);
            throw e;
        }
    }

    public static WebElement waitForElementToHaveValue(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(locator);
                String value = element.getAttribute("value");
                return (value != null && !value.isEmpty()) ? element : null;
            }

            @Override
            public String toString() {
                return "value to be present in the element located by " + locator;
            }
        });
    }
}