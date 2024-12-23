package pageClass;

import commonUtils.TestData;
import commonUtils.WaitUtil;
import org.openqa.selenium.*;

import java.time.LocalDate;

public class CreateProfilePage extends BasePage {

    public CreateProfilePage(WebDriver driver) {
        super(driver);
    }

    String optionForTitle = "//div[@class='ant-select-item-option-content']/parent::div[@title='";

    private By genderField = By.xpath("//*[contains(@id, 'ender')]");
    private By dateOfBirthField = By.xpath("//*[contains(@id, 'irthdate')]");
    private By countryField = By.xpath("//*[contains(@id, 'countries')]");
    private By preferredLanguage = By.xpath("//*[contains(@id, 'referedLanguage')]");
    private By nationalityField = By.xpath("//*[contains(@id, 'ationality')]");
    private By suffixField = By.xpath("//*[contains(@id, 'suffix')]");
    private By roleField = By.xpath("//*[contains(@id, 'role')]");
    private By myProfileCard = By.xpath("//div[contains(@class,'ProfileMain_card__ymnfY')]");
    private By addContactButton = By.xpath("//button[span[text()='Add contact']]");
    private By phoneField = By.xpath("//input[@placeholder='1 (702) 123-4567']");
    private By addCDocumentButton = By.xpath("//button[span[text()='Add Document']]");
    private By passportNoField = By.xpath("//*[contains(@id, 'Number')]");
    private By country = By.xpath("//*[contains(@id, 'country')]");
    private By issueDate = By.xpath("//*[contains(@id, 'IssueDate')]");
    private By expiryDate = By.xpath("//*[contains(@id, 'ExpiryDate')]");
    private By issuedCountry = By.id("issuedCountry");
    private By issuedCity = By.xpath("(//input[@id='issuedCountry'])[2]");

    public void selectCountry(String gender) {
        enterText(genderField, gender);
        By dropdownforcountryCode = By.xpath(optionForTitle + gender + "']");
        clickElement(dropdownforcountryCode);
    }

    public void selectDateOfBirth(String dob) {
        clickElement(dateOfBirthField);
    }

    public void selectResidence(String residence) {
        enterText(countryField, residence);
        By dropdownforcountryCode = By.xpath(optionForTitle + residence + "']");
        clickElement(dropdownforcountryCode);
    }

    public void selectPreferredLanguage(String language) {
        enterText(preferredLanguage, language);
        By dropdownforcountryCode = By.xpath(optionForTitle + language + "']");
        clickElement(dropdownforcountryCode);
    }

    public void selectNationality(String nationality) {
        enterText(nationalityField, nationality);
        By dropdownforcountryCode = By.xpath(optionForTitle + nationality + "']");
        clickElement(dropdownforcountryCode);
    }

    public void enterSuffix(String suffix) {
        enterText(suffixField, suffix);
    }

    public void selectRole(String role) {
        if (isElementPresent(roleField)) {
            System.out.println("Role input field already exists. Proceeding...");
            // Click Add Role button
            clickElement(roleField);
            // Enter the role name
            enterText(roleField, role);
            // Submit the role
            clickElement(roleField);
        } else {
            System.out.println("Role input field not found");
        }
    }

    public void clickOnViewByEmail(String email) {
        // Define the XPath pattern for the "View" button based on the email
        String viewButtonXpath = "//td/a[text()='%s']/ancestor::tr/td/div[contains(@class,'Admin_action')]/button[contains(@class,'OrdersList_viewButton')]/a";

        // Replace %s with the dynamic email
        String finalViewButtonXpath = String.format(viewButtonXpath, email);

        // Create a dynamic locator
        By dynamicViewButton = By.xpath(finalViewButtonXpath);

        // Click the element using your BasePage method
        clickElement(dynamicViewButton);
    }

    public void clickOnMyProfile() {
        clickElement(myProfileCard);
    }

    public void expandContactInfoIfCollapsed(String title) {

        By sectionHeader = By.xpath("//span[text()='" + title + "']/ancestor::div[contains(@class,'ant-collapse-header')]");
        By collapsedSection = By.xpath("//span[text()='" + title + "']/ancestor::div[contains(@class,'ant-collapse-header') and @aria-expanded='false']");
        // Check if the section is collapsed
        if (isElementPresent(collapsedSection)) {
            // Click to expand
            clickElement(sectionHeader);
        } else {
            System.out.println("'" + title + "' section is already expanded.");
        }
    }

    public void clickAddContactButton() {
        clickElement(addContactButton);
    }

    public void clickAddDocumentButton() {
        clickElement(addCDocumentButton);
    }

    public void selectContactType(String type) throws InterruptedException {

        By dropdownInput = By.xpath("//*[contains(@id, 'Type')]");
        WebElement inputElement = driver.findElement(dropdownInput);

        // Use JavaScriptExecutor to trigger focus and mousedown events
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Trigger 'focus' event
        js.executeScript("arguments[0].focus();", inputElement);

        // Trigger 'mousedown' event to simulate the dropdown opening
        js.executeScript("arguments[0].dispatchEvent(new MouseEvent('mousedown', {bubbles: true}));", inputElement);
    }

    public void addContactInformation(String contactType) throws InterruptedException {

        // Perform actions based on the contact type
        switch (contactType) {
            case "Mobile Phone":
                // Locate the input field by ID
                By dropdownInput = By.xpath("//div[@class='ant-select-item-option-content' and text()='" + contactType + "']");
                clickElement(dropdownInput);

                String contact = TestData.getInstance().getPhoneNumber();
                String contact_no = "+971" + contact;
                enterContactDetails(contact_no);
                break;

            case "Work Phone":
                //To Do (Need to add logic here if needed)
                break;

            case "Home Phone":
                //To Do (Need to add logic here if needed)
                break;

            case "Emergency Phone":
                //To Do (Need to add logic here if needed)
                break;

            default:
                throw new IllegalArgumentException("Invalid Contact Type: " + contactType);
        }
    }

    // Method to enter contact number
    public void enterContactDetails(String contactNumber) {
        enterText(phoneField, contactNumber);
    }

    public void addingPassportInformation() throws InterruptedException {
        String passportNumber = TestData.getInstance().generateRandomNumericString(7);
        enterText(passportNoField, passportNumber);
        TestData.getInstance().setNoOfDatePicker(2);

        //Adding country
        clickElement(country);
        enterText(country, "United Kingdom");
        By dropDownForCountryCode = By.xpath("//div[@class='ant-select-item-option-content']/parent::div[@title='United Kingdom']");
        clickElement(dropDownForCountryCode);

        //Selecting previous date from date picker
        TestData.getInstance().selectPreviousDate(issueDate, 120);

        //Selecting issued country
        clickElement(issuedCountry);
        enterText(issuedCountry, "United Arab Emirates");
        By dropDownForIssuedCountry = By.xpath("//div[@class='ant-select-item-option-content']/parent::div[@title='United Arab Emirates']");
        clickElement(dropDownForIssuedCountry);

        //Selecting issued City
        clickElement(issuedCity);
        enterText(issuedCity, "Dubai");
        By dropDownForCity = By.xpath("//div[@class='ant-select-item-option-content']/parent::div[@title='Dubai']");
        clickElement(dropDownForCity);

        //Selecting issue date
        TestData.getInstance().selectNextDate(expiryDate, 120);
    }

    public void addTravelDocument(String documentType) throws InterruptedException {
        switch (documentType) {
            case "Passport":
                // Locate the input field by ID
                By dropdownInput = By.xpath("//div[@class='ant-select-item-option-content' and text()='" + documentType + "']");
                clickElement(dropdownInput);
                addingPassportInformation();
                break;

            case "Visa":
                //To Do (Need to add logic here if needed)
                break;

            case "ESTA":
                //To Do (Need to add logic here if needed)
                break;

            case "ETA":
                //To Do (Need to add logic here if needed)
                break;

            default:
                throw new IllegalArgumentException("Invalid Contact Type: " + documentType);
        }
    }

    public void selectDepartureDate() {
        driver.findElement(issueDate).click();

        LocalDate today = LocalDate.now();
        LocalDate travellingDate = today.minusDays(120);

        // Extract the day, month, and year
        String day = String.valueOf(travellingDate.getDayOfMonth());
        String month = travellingDate.getMonth().name().substring(0, 3).toLowerCase(); // First 3 letters of the month in lowercase
        String year = String.valueOf(travellingDate.getYear());
        // Month and year selection
        while (true) {

            WebElement monthButton = WaitUtil.waitForElementToBeVisible(driver, By.className("ant-picker-month-btn"));
            String mon = monthButton.getText().toLowerCase();

            // Wait for the year button to be visible and get its text
            WebElement yearButton = WaitUtil.waitForElementToBeVisible(driver, By.className("ant-picker-year-btn"));
            String yr = yearButton.getText();
            if (mon.equals(month) && yr.equals(year)) {
                break;
            }
            driver.findElement(By.xpath("//button[@class='ant-picker-header-prev-btn']")).click();
        }
        // Date selection
        for (WebElement dateSel : driver.findElements(By.xpath("//table[@class=\"ant-picker-content\"]//td"))) {
            if (dateSel.getText().equals(day)) {
                dateSel.click();
                break;
            }
        }
    }
}