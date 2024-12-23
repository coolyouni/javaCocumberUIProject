package pageClass;

import commonUtils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class DashboardPage extends BasePage{
    public DashboardPage(WebDriver driver) {
        super(driver);
    }
    private By myTravelTab = By.xpath("//a[contains(text(), 'My Travel')]");
    private By welcomeMessage =By.xpath("//h1[@class='ant-typography Stepper_stepTitle__eh1m5 mt-0']");
    private By menuIcon = By.xpath("//img[@alt='Apps Icon']");
    private By userRoleManagementTab= By.xpath("//div/p[text()='User & Role Management']");
    private By userManagementMenu= By.xpath("//a[text()='User Management']");
    private By TravellerProfileTab= By.xpath("//div/p[text()='Traveller profile']");
    private By ProfilesMenu= By.xpath("//a[text()='Profiles']");

    public  String url ="http://cbtqa.airretailer.local/dashboard";
    public Boolean getCurrentURL(String url) {
        driver.getCurrentUrl().toLowerCase().contains(url.toLowerCase());
        return true;

    }
    public boolean verifySuccess(String expectedMessage) {
        String actualMessage = getText(myTravelTab);
        return actualMessage.contains(expectedMessage);

    }
    public boolean verifySuccessWelcome(String expectedMessage) {
        String actualMessage = getText(welcomeMessage);
        return actualMessage.contains(expectedMessage);

    }
   public void navigatesToTheUserManagementScreen() {
       clickElement(menuIcon);
       clickElement(userRoleManagementTab);
       clickElement(userManagementMenu);

   }

    public void navigatesToTheProfileScreen() {
        clickElement(menuIcon);
        clickElement(TravellerProfileTab);
        clickElement(ProfilesMenu);

    }



}
