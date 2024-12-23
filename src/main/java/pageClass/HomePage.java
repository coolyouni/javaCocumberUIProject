package pageClass;

import commonUtils.WaitUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    private static final Logger logger = LogManager.getLogger(HomePage.class); // Create a logger instance

    private By myTravelTab = By.xpath("//a[contains(text(), 'My Travel')]");
    private By welcomeMessage = By.xpath("//h1[@class='ant-typography Stepper_stepTitle__eh1m5 mt-0']");

    private By menu_icon = By.xpath("//img[@alt='Apps Icon']");
    private By corpAgencyBtn = By.xpath("//label[@for='dd-input-5']");
    private By organizationBtn = By.xpath("//a[normalize-space()='Organization']");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String url = "http://cbtqa.airretailer.local/dashboard";

    public Boolean getCurrentURL(String url) {
        driver.getCurrentUrl().toLowerCase().contains(url.toLowerCase());
        return true;
    }

    public void clickMenu() {
        logger.info("Clicking on menu icon.");
        WebElement menu_btn = WaitUtil.waitForElementToBeVisible(driver, menu_icon);
        menu_btn.click();
    }

    public void clickCorporateAgencySetup() {
        logger.info("Clicking on Corporate/Agency Setup menu");
        WebElement corpAgencyEle = WaitUtil.waitForElementToBeVisible(driver, corpAgencyBtn);
        corpAgencyEle.click();
    }

    public CorporateAgencyPage clickOrganization() {
        logger.info("Clicking on Organization in the menu");
        WebElement organizationEle = WaitUtil.waitForElementToBeVisible(driver, organizationBtn);
        organizationEle.click();
        return new CorporateAgencyPage(driver);
    }

}
