package pageClass;

import commonUtils.WaitUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CorporateAgencyPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(CorporateAgencyPage.class); // Create a logger instance

    private By corpAgencyPageHeading = By.xpath("//*[@class='AirCommon_pageTitle__k8ww6 mb-0']");
    private By threeDotMenuforTheFirstOrg = By.xpath("((//*[@class='ant-tree-node-content-wrapper'])[1]//span)[1]");
    private By editEntityBtn = By.xpath("(//*[@class='ant-dropdown-menu-item ant-dropdown-menu-item-only-child'])[2]");
    private By deleteEntityBtn = By.xpath("(//*[@class='ant-dropdown-menu-item ant-dropdown-menu-item-only-child'])[3]");
    private By orgStatusToggleSwitch=By.className("ant-switch-handle");
    private By orgStatus=By.className("ant-switch-inner");
    private By deleteConfirmPopup = By.xpath("//button[@class='ant-btn ant-btn-default ant-button-default']");
    private By addEntityBtn = By.xpath("(//*[@class='ant-dropdown-menu-item ant-dropdown-menu-item-only-child'])[1]");
    private By branchCodeField = By.id("BranchCode");
    private By provinceField = By.id("Province");
    private By saveBtn = By.xpath("//button[.//span[text()='Save']]");
    private By orgTypeLabel = By.xpath("//label[contains(text(), 'Organization Type')]");
    private By orgTypeDrpDwn = By.xpath(".//following::div[contains(@class, 'ant-select-selector')][1]");
    private By countryListArrow = By.xpath("//*[@class='selected-flag']");
    private By searchCountryCodeBox = By.xpath("//*[@class='search-box']");
    private By firstOptionCountryCode = By.xpath("//ul[@class='country-list ']/li[2]");
    private By timeZoneDrpDwn = By.xpath("//input[@id='TimeZone']/../..");
    private By addressTxtBx = By.id("AddressLine1");
    private By stateTxtBx = By.id("Province");
    private By descriptionTxtBx = By.id("Description");
    private By companyField = By.id("OrganizationName");
    private By OTPToggle = By.id("IsOtpEnabled");

    public CorporateAgencyPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeading() {
        WebElement corpAgencyPageHeadingEle = WaitUtil.waitForElementToBeVisible(driver, corpAgencyPageHeading);
        String corpAgencyPageHeading = corpAgencyPageHeadingEle.getText();
        logger.info("The corporate agency page heading is: {}", corpAgencyPageHeading);
        return corpAgencyPageHeading;
    }

    public void clickThreeDotMenuForTheFirstOrg() {
        WebElement threeDotMenu = WaitUtil.waitForElementToBeVisible(driver, threeDotMenuforTheFirstOrg);
        threeDotMenu.click();
    }

    public void clickThreeDotMenuForTheCreatedOrg(String companyName) {
        WebElement titleElement = WaitUtil.waitForElementToBeVisible(driver, By.xpath("//p[@class='title' and text()='" + companyName + "']"));
        WebElement spanElement = titleElement.findElement(By.xpath("./following-sibling::span"));
        // Click the <span>
        spanElement.click();
    }

    public void clickDeleteEntity() {
        WebElement deleteEntityEle = WaitUtil.waitForElementToBeVisible(driver, deleteEntityBtn);
        deleteEntityEle.click();
        logger.info("User clicked delete entity");
    }

    public void clickConfirm() {
        WebElement deleteConfirmEle = WaitUtil.waitForElementToBeVisible(driver, deleteConfirmPopup);
        deleteConfirmEle.click();
        logger.info("Action confirmed");
    }

    public void clickEditEntity() {
        WebElement editEntityEle = WaitUtil.waitForElementToBeVisible(driver, editEntityBtn);
        editEntityEle.click();
        logger.info("User clicked edit entity");
    }

    public void clickToDeactivateOrg() {
            // Locate the toggle switch and its state text
            WebElement toggleSwitch = driver.findElement(orgStatusToggleSwitch);
            // Get the current state of the toggle
            String stateText = getText(orgStatus);
            if ("Active".equalsIgnoreCase(stateText)) {
                // If state is "Active", click to change to "Inactive"
                toggleSwitch.click();
                logger.info("Subsite deactivated");
            } else {
                // If already "Inactive", leave as is
                logger.info("Subsite is already Inactive.");
            }
    }

    public boolean checkPreFilledData(String companyName) {
        try {
            // Wait for the value to be present
            WebElement element = WaitUtil.waitForElementToHaveValue(driver, companyField, 10);
            String filledCompanyName = element.getAttribute("value");
            // Check if the value matches the expected name
            return filledCompanyName.contains(companyName);
        } catch (Exception e) {
            logger.error("Error while waiting for pre-filled data: " + e.getMessage());
            return false;
        }
    }

    public void clickAddNewEntity() {
        WebElement addEntityEle = WaitUtil.waitForElementToBeVisible(driver, addEntityBtn);
        addEntityEle.click();
    }

    public void enterBranchCode(String branchCode) {
        enterText(branchCodeField, branchCode);
    }

    public void enterStateProvince(String state) {
        enterText(provinceField, state);
    }

    public void clickSave() {
        WebElement saveEle = WaitUtil.waitForElementToBeVisible(driver, saveBtn);
        saveEle.click();
        logger.info("Save button clicked");
    }

    public void selectOrgType(String orgType) {
        WebElement labelOrgType = driver.findElement(orgTypeLabel);
        WebElement OrgTypedropdown = labelOrgType.findElement(orgTypeDrpDwn);
        OrgTypedropdown.click();
        WebElement orgTypeOption = WaitUtil.waitForElementToBeClickable(driver, By.xpath("//div[contains(@class, 'rc-virtual-list-holder-inner')]//div[@title='" + orgType + "']"));
        orgTypeOption.click();
    }

    public void selectCountryCode(String countryCode) {
        WebElement countryListArrowEle = driver.findElement(countryListArrow);
        countryListArrowEle.click();
        enterText(searchCountryCodeBox, countryCode);
        clickElement(firstOptionCountryCode);
    }

    public void selectTimeZone(String timeZone) {
        WebElement timeZoneEle = findElement(timeZoneDrpDwn);
        timeZoneEle.click();
        WebElement firstOption = findElement(By.xpath("//div[contains(text(),'" + timeZone + "')]"));
        firstOption.click();
    }

    public void enterAddress(String address) {
        enterText(addressTxtBx, address);
    }

    public void enterState(String state) {
        enterText(stateTxtBx, state);
    }

    public void enterDescription(String description) {
        enterText(descriptionTxtBx, description);
    }

    public void disableOTP(){
        try {
            // Locate the toggle button
            WebElement toggleButton = driver.findElement(OTPToggle);
            // Check the value of the aria-checked attribute
            String ariaChecked = toggleButton.getAttribute("aria-checked");
            if ("true".equals(ariaChecked)) {
                // If checked, click to disable it
                toggleButton.click();
                logger.info("OTP is disabled");
            } else {
                // If unchecked, leave it as is
                logger.info("OTP is already disabled.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
