package pageClass;

import commonUtils.TestData;
import commonUtils.WaitUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UsersListPage extends BasePage {

    public UsersListPage(WebDriver driver) {
        super(driver);
    }

    private By createBtn = By.xpath("//button/span[text()='Create']");
    String xpathTemplate = "//span[text()='Created %s']";
    String xpathEditTemplate = "//a[text()='%s']/parent::td/following-sibling::td[7]/div[@class='Admin_action__yIqvb']/span[@class='anticon anticon-form']";
    String xpathDeleteTemplate = "//a[text()='%s']/parent::td/following-sibling::td[7]/div[@class='Admin_action__yIqvb']/button";
    private By phoneNumberTxtBox=By.xpath("//input[@placeholder='1 (702) 123-4567']");
    private By userEditBtn= By.xpath("//button/span[text()='Save']");
    private By userUpdatedSuccessfullyPopUpMessage = By.xpath("//span[text()='User updated successfully.']");
    private By deleteBtn= By.xpath("//button/span[text()='Confirm']");
    private By userDeletedSuccessfullyPopUpMessage = By.xpath("//span[text()='User deleted successfully.']");


    public void clickOnCreateBtn(){
        clickElement(createBtn);
    }

    public WebElement getPopUpMessage(String firstName) {
        String xpath = String.format(xpathTemplate, firstName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public void clickOnEditBtn(){
        String firstName=TestData.getInstance().getFirstName();
        String lastName=TestData.getInstance().getLastName();
        String fullName = firstName + " " + lastName;
        String finalXpath = String.format(xpathEditTemplate, fullName);
        By dynamicLocator = By.xpath(finalXpath);
        clickElement(dynamicLocator);
    }

    public void userUpdatesPhoneNumber(String phone_number){
        WebElement phoneNumberField=findElement(phoneNumberTxtBox);
        Actions actions = new Actions(driver);
        actions.moveToElement(phoneNumberField)
                .click()
                .sendKeys(Keys.END);
        for (int i = 0; i < 9; i++) {
            actions.sendKeys(Keys.BACK_SPACE);
        }
        actions.sendKeys(phone_number).build().perform();
    }

    public void clickOnUserEditSaveBtn(){
        clickElement(userEditBtn);
    }

    public boolean verifySuccessPopup(String expectedEditPopUpMessage) {
        try {
            WebElement popUpMessage = WaitUtil.waitForElementToBeVisible(driver, userUpdatedSuccessfullyPopUpMessage, 10);
            String actualMessage = popUpMessage.getText().trim();
            return actualMessage.equals(expectedEditPopUpMessage);
        } catch (Exception e) {
            return false;
        }
    }

    public void clickOnDeleteBtn(){
        String firstName=TestData.getInstance().getFirstName();
        String lastName=TestData.getInstance().getLastName();
        String fullName = firstName + " " + lastName;
        String finalDeleteXpath = String.format(xpathDeleteTemplate, fullName);
        By dynamicDeleteLocator = By.xpath(finalDeleteXpath);
        clickElement(dynamicDeleteLocator);
    }
    public void clickOnConfirmBtn(){
        clickElement(deleteBtn);
    }

    public boolean verifySuccessPopupMessage(String expectedDeletePopUpMessage){
        try {
            WebElement popUpMessage = WaitUtil.waitForElementToBeVisible(driver, userDeletedSuccessfullyPopUpMessage, 10);
            String actualMessage = popUpMessage.getText().trim();
            System.out.println(actualMessage);
            return actualMessage.equals(expectedDeletePopUpMessage);
        } catch (Exception e) {
            return false;
        }
    }

}
