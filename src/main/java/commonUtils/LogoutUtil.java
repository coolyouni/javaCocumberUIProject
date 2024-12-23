package commonUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageClass.BasePage;

public class LogoutUtil extends BasePage {

    private By logoutMenuButton = By.xpath("//div[contains(@class, 'HeaderNew_profileHolder__e7gtM') and contains(@class, 'HeaderNew_dropDown__GQyQ6')]//h2");
    private By logoutButton = By.xpath("//ul[contains(@class, 'ant-dropdown-menu') and @role='menu']//a[text()='Log Out']");
    private By logOutConfirmButton = By.xpath("//button/span[text()='Confirm']");

    public LogoutUtil(WebDriver driver) {
        super(driver);
    }

    public void logout() throws InterruptedException {
        clickElement(logoutMenuButton);
        clickElement(logoutButton);
      //  Thread.sleep(1000);
        clickElement(logOutConfirmButton);
    }
}
