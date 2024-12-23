package pageClass;


import commonUtils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserrolePage extends BasePage {


    private static final Logger logger = LogManager.getLogger(UserrolePage.class); // Create a logger instance
    private By userrolemangementBtn = By.xpath("//label[@for='dd-input-1']");
    private By rolemanagementBtn = By.xpath("//a[normalize-space()='Role Management']");
    private By createroleBtn = By.xpath("//span[normalize-space()='Create']");
    private By rolenameField = By.xpath("//*[@id='RoleName']");
    private By descField = By.xpath("//*[@id='Role Description']");
    private By featuresandpermissionsEle = By.xpath("//p[@class='PackageForm_pHeader__t3v4o' and text()='Features & Permissions']");
    private By selectAllBtn = By.xpath("//span[normalize-space()='Select All']");
    private By saveRoleBtn = By.xpath("//button[.//span[text()='Save']]");
    private By deleteconfirmBtn = By.xpath("//button/span[text()='Confirm']");
    private By featuresSelectAllBtn = By.xpath("//div[@class='ant-collapse-content ant-collapse-content-active']/div/div/p/parent::div/button/span[text()='Select All']");

    public UserrolePage(WebDriver driver) {
        super(driver);
    }

    // click on Menu and select User role and management page
    public void clickUserandRolemangment() {
        logger.info("Clicking on User and Role management menu");
        WebElement userRoleMangEle = WaitUtil.waitForElementToBeVisible(driver, userrolemangementBtn);
        userRoleMangEle.click();
    }

    public void selectRolemanagement() {
        logger.info("Clicking on Role management");
        WebElement rolemanagementEle = WaitUtil.waitForElementToBeVisible(driver, rolemanagementBtn);
        rolemanagementEle.click();
        logger.info("Navigated to Create user role page");
        WaitUtil.waitForElementToBeVisible(driver, createroleBtn);
    }


    // Check if the "admin" role already exists
    public boolean doesAdminRoleExist(String RoleName) {
        try {
                String deleteAdminRolexpath = "//a[text()='%s']/parent::td/following-sibling::td[1]/div[@class='Admin_action__yIqvb']/button[@class='ant-btn ant-btn-default Admin_deleteButton__QFhWl']";
                String finalAdmindeleteRolexpath = String.format(deleteAdminRolexpath, RoleName);
                WebElement dynamicAdminDeleteEle = driver.findElement(By.xpath(finalAdmindeleteRolexpath));
                return dynamicAdminDeleteEle.isDisplayed();
            } catch (Exception e) {
            return false;  // If Admin role element is not found, role doesn't exist
        }
    }

    public void deleteExistingAdminRole(String RoleName) {
        boolean adminRoleExists = doesAdminRoleExist(RoleName);
        if (adminRoleExists) {
            clickOnRoleDeleteBtn(RoleName);
            clickOnRoleConfirmDelBtn();
            WaitUtil.waitForElementToBeVisible(driver, createroleBtn);
        }
        else {
            logger.info("Navigated to Create user role page and verified existing Admin role");
        }
    }




    public void clickOnCreate() {
        clickElement(createroleBtn);
    }

    public void enterRoleName(String RoleName) {
        enterText(rolenameField, RoleName);
    }

    public void enterRoleDescription(String RoleDescription) {
        enterText(descField, RoleDescription);
    }

    public void clickOnFeaturesandPermissions() {
        clickElement(featuresandpermissionsEle);
        logger.info("Selected All features and permissions to provide Admin access");
        WaitUtil.waitForElementToBeVisible(driver, selectAllBtn);
    }

    public void clickOnSelectAll() {
        clickElement(selectAllBtn);
    }

    public void selectfeatures(List<String> features) {

        //iterate passed features
        for (String feature : features) {

            // Define the XPath for the specific feature and clicking the passed features
            String featurexpath = "//p[@class='PackageForm_pHeader__t3v4o' and text()='" + feature + "']";
            WebElement featuresicon = driver.findElement(By.xpath(featurexpath));
            featuresicon.click();

            try {
                // click select All element exists after selecting specific feature
                WebElement selectallfeatureEle = WaitUtil.waitForElementToBeVisible(driver, featuresSelectAllBtn);
                selectallfeatureEle.click();
                featuresicon.click();

            } catch (Exception e) {
                // Return null if feature is not found or not clickable
                logger.info("Not able to select features");

            }
        }
    }

        public void clickOnActionForRole (String RoleName){
            String editRolexpath = "//a[text()='%s']/parent::td/following-sibling::td[1]/div[@class='Admin_action__yIqvb']/span[@class='anticon anticon-form']";
            String finaleditXpath = String.format(editRolexpath, RoleName);
            By dynamiceditLocator = By.xpath(finaleditXpath);
            clickElement(dynamiceditLocator);
        }

        public void clickOnSave () {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 500);");
            clickElement(saveRoleBtn);
            js.executeScript("setTimeout(function(){}, 3000);");
        }

        public void clickOnRoleDeleteBtn (String RoleName){

            String deleteRolexpath = "//a[text()='%s']/parent::td/following-sibling::td[1]/div[@class='Admin_action__yIqvb']/button[@class='ant-btn ant-btn-default Admin_deleteButton__QFhWl']";
            String finaldeleteRolexpath = String.format(deleteRolexpath, RoleName);
            By dynamicDeleteEle = By.xpath(finaldeleteRolexpath);
            clickElement(dynamicDeleteEle);

        }

        public void clickOnRoleConfirmDelBtn () {
            clickElement(deleteconfirmBtn);
        }
    }

