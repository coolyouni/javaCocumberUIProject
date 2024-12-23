package pageClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateUsersPage extends BasePage {
    public CreateUsersPage(WebDriver driver) {
        super(driver);
    }

    private By titleDropDown = By.xpath("(//div[@class='ant-select-selector'])[3]");
    private By saveBtn= By.xpath("//span[text()='Save']");
    String optionForTitle = "//div[@class='ant-select-item-option-content']/parent::div[@title='";


    public void selectTitle(String title) {
        clickElement(titleDropDown);
        By optionLocator = By.xpath(optionForTitle + title + "']");
        clickElement(optionLocator);
    }

    public void clickOnSaveBtn(){
        clickElement(saveBtn);
    }

}
