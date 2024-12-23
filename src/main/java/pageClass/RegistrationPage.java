package pageClass;

import commonUtils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPage extends BasePage {

    //private FormType currentFormType;

//    public void setCurrentFormType(FormType formType) {
//        this.currentFormType = formType;
//    }
    private By SignupLink = By.xpath("//a[@href='/auth/register']");
    // Locators for registration fields
    private By companyField = By.id("OrganizationName");
    //private By iataType = By.cssSelector("div.ant-select-selector");
    private By iataType = By.id("AgentType");
    private By iataNoField = By.xpath("//*[@id='IataNumber']");
    private By bilateralNoField = By.xpath("//*[@id='BilateralPayerCode']");
    private By TidsNoField = By.xpath("//input[@id='TidsNumber']");
    private By titleDropdown = By.xpath("(//div[@class='ant-select-selector'])[2]");
    private By titleField = By.name("title");
    private By firstNameField = By.xpath("//*[contains(@id, 'irstName')]");
    private By lastNameField = By.xpath("//*[contains(@id, 'astName')]");
    private By middleNameField = By.xpath("//*[contains(@id, 'iddleName')]");
    private By emailField =By.xpath("//*[contains(@id, 'mail')]");
    private By designationField = By.id("Designation");
    private By locationField = By.id("Address");
    //private By countryField = By.xpath("(//div[@class='ant-select-selector'])[3]");
    private By countryField = By.id("CountryCode");
    private By stateField = By.xpath("//input[@id='State']");
    //private By cityField = By.id("CityName");
    private By cityField = By.xpath("//*[contains(@id, 'City')]");
    private By zipcode = By.xpath("//*[@id='ZipCode']");
    private By phoneField = By.xpath("//input[@placeholder='1 (702) 123-4567']");

    // Buttons and checkboxes
    private By termsCheckbox = By.linkText("Terms of Service");
    private By submitButton = By.xpath("//span[normalize-space()='Submit']");
    private By planSelectionButton = By.xpath("//button[@id='confirmPlan']");
    private By payNowButton = By.xpath("//button[contains(text(), 'Pay Now')]");

    private By successPopupLocator = By.cssSelector("div.ant-message-custom-content.ant-message-success");
    private By tmcTab = By.xpath("//span[normalize-space()='TMC / Travel Agency']");
    //option for title
    String optionForTitle = "//div[@class='ant-select-item-option-content']/parent::div[@title='";

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnSignup() {
        clickElement(SignupLink);
    }

    public void enterCompanyName(String companyName) {
        enterText(companyField, companyName);
    }

    public void selectAgentType(String agentType) {
//        clickElement(iataType);
//        By dropdownforAgentType = By.xpath(optionForTitle + agentType + "']");
//        clickElement(dropdownforAgentType);
        WebElement iATATypeEle = driver.findElement(iataType);
        iATATypeEle.click();
        WebElement orgTypeOption = WaitUtil.waitForElementToBeClickable(driver, By.xpath("//div[contains(@class, 'rc-virtual-list-holder-inner')]//div[@title='" + agentType + "']"));
        orgTypeOption.click();
    }

    public void enterIATANumber(String iataNumber) {
        enterText(iataNoField, iataNumber);
    }

    public void enterBilateralCode(String bilateralfield) {
        enterText(bilateralNoField, bilateralfield);
    }

    public void selectTitle(String title) {
        //Todo
        //MAke it dynamic

        clickElement(titleDropdown);
        By optionLocator = By.xpath(optionForTitle + title + "']");
        clickElement(optionLocator);
    }

//    // Dynamic locator for "First Name"
//    private By getFirstNameLocator() {
//        switch (currentFormType) {
//            case REGISTRATION:
//                return firstNameField; // Locator for the registration form
//            case PROFILE:
//                return firstNameFieldProfile; // Locator for the profile form
//            default:
//                throw new IllegalStateException("Unknown form type: " + currentFormType);
//        }
//    }

    public void enterFirstName(String firstName) {
        enterText(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        enterText(lastNameField, lastName);
    }

    public void enterMiddleName(String middleName) {
        enterText(middleNameField, middleName);
    }

    public void enterEmail(String email) {
        enterText(emailField, email);
    }

    public void enterDesignation(String designation) {
        enterText(designationField, designation);
    }

    public void enterLocation(String location) {
        enterText(locationField, location);
    }

    public void selectCountry(String country) {
        //Todo
        //MAke it dynamic

        enterText(countryField, country);
        By dropdownforcountryCode = By.xpath(optionForTitle + country + "']");
        clickElement(dropdownforcountryCode);
    }

    public void enterState(String state) {
        enterText(stateField, state);
    }

    public void selectCity(String city) {
        enterText(cityField, city);
        By dropdownforCity = By.xpath(optionForTitle + city + "']");
        clickElement(dropdownforCity);
    }

    public void enterZipCode(String postcode) {
        enterText(zipcode, postcode);
    }

    public void enterPhoneNumber(String phoneNumber) {

        enterText(phoneField, phoneNumber);
    }

    public void agreeToTerms() {
        clickElement(termsCheckbox);
    }

    public void submitForm() {
        clickElement(submitButton);
    }

    public boolean verifySuccessPopup(String expectedMessage) {
//        String actualMessage = getText(successPopupLocator);
//
//        // Check if the actual message matches the expected message
//        return actualMessage.contains(expectedMessage);
//        //Test push change
        try {
            // Wait for the success popup to be visible
            String actualMessage = getText(successPopupLocator);
            // Check if the actual message matches the expected message
            if (actualMessage.contains(expectedMessage)) {
                WaitUtil.waitForElementToBeInvisible(driver, successPopupLocator, 2);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public void clickOnTMCTab() {
        clickElement(tmcTab);
    }

    public void enterTidsNo(String tidsNo) {
        enterText(TidsNoField, tidsNo);
    }
}
