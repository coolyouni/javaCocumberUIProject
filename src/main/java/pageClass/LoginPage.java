package pageClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static commonUtils.WaitUtil.waitForPresenceOfElement;


public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By email = By.xpath("//input[@class='ant-input ant-input-text']");
    private By pass = By.xpath("//input[@class='ant-input']");
    private By login = By.xpath("//button[@class='ant-btn ant-btn-default ant-button-default']");

    private  By invalidMessgeEmail= new By.ByXPath("(//span[contains(text(),'Invalid username or password. Please check your cr')])[1]");
    private  By invalidMessgePass= new By.ByXPath("(//span[normalize-space()='Password is wrong, You have 4 more attempts.'])[1]");
    private  By emptyFields = new By.ByXPath("(//span[normalize-space()='Please fill all the required fields'])[1]");



    public void sendUserInputValue(String userName) {
        enterText(email, userName);
    }

    public void sendPassInputValue(String password) {
        enterText(pass, password);
    }

    public void logInClick() {
        clickElement(login);
    }

    public boolean verifyInvalidPopup(String expectedMessage) {
        String actualMessage = getText(invalidMessgeEmail);
        return actualMessage.contains(expectedMessage);
    }
    public boolean verifyInvalidPassPopup(String expectedMessage) {
        String actualMessage = getText(invalidMessgePass);
        return actualMessage.contains(expectedMessage);

}
    public boolean verifyEmptyFieldsPopup(String expectedMessage){
        String actualMessage = getText(emptyFields);
        return actualMessage.contains(expectedMessage);


}


}
