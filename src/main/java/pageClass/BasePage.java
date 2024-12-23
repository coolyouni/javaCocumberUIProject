package pageClass;

import commonUtils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement waitForElement(By locator) {
        return WaitUtil.waitForElementToBeVisible(driver, locator); // Uses WaitUtil for visibility wait
    }

    protected void clickElement(By locator) {
        WaitUtil.waitForElementToBeClickable(driver, locator).click(); // Uses WaitUtil for clickable wait
    }

    protected void enterText(By locator, String text) {
        WebElement element = waitForElement(locator); // Uses WaitUtil for visibility wait
        //element.clear();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.BACK_SPACE);
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return waitForElement(locator).getText(); // Uses WaitUtil for visibility wait
    }
    
    protected WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true; // Element exists
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false; // Element does not exist
        }
    }
}
