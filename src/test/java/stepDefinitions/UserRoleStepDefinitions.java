package stepDefinitions;

import commonUtils.Config;
import commonUtils.TestData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import commonUtils.DriverUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import pageClass.HomePage;
import pageClass.LoginPage;
import pageClass.RegistrationPage;
import pageClass.UserrolePage;



import java.util.List;



public class UserRoleStepDefinitions {
    private WebDriver driver = DriverUtil.getDefaultDriver();
    private static final Config config = new Config();
    private final TestData testData = TestData.getInstance();
    public LoginPage login = new LoginPage(DriverUtil.getDefaultDriver());
    private static final HomePage homePage = new HomePage(DriverUtil.getDefaultDriver());
    private static final RegistrationPage registrationPage = new RegistrationPage(DriverUtil.getDefaultDriver());
    public final UserrolePage UserrolePage;


    public UserRoleStepDefinitions() {
        UserrolePage = new UserrolePage(driver);
    }

    @Given("the user navigates to user role and management page")
    public void userNavigatesToRoleandManagementPage() {
        homePage.clickMenu();
        UserrolePage.clickUserandRolemangment();
    }

    @And("the User clicks on role management")
    public void clicks_Rolemanagement() {
        UserrolePage.selectRolemanagement();
    }
    @And ("validates existing admin role {string} and deletes")
    public void verify_existingAdminRole(String RoleName) {
        UserrolePage.deleteExistingAdminRole(RoleName);
    }

    @And ("clicks the create button")
    public void clicksOnCreateRole() {
        UserrolePage.clickOnCreate();
    }

    @And("enter the valid Role description {string}")
    public void userEnters_Roledescription(String RoleDescription) {
        UserrolePage.enterRoleDescription(RoleDescription);
    }

    @And("clicks the Features & Permissions")
    public void click_Features_Permissions() {
        UserrolePage.clickOnFeaturesandPermissions();
    }

    @And("select All permissions")
    public void selectPermissions() {
        UserrolePage.clickOnSelectAll();
    }
    @When ("the admin enters a role name {string}")
    public void user_EntersNewRoleName(String RoleName) {
        UserrolePage.enterRoleName(RoleName);
    }

    @And("selects Minimal Features & Permissions")
    public void select_MinimalFeatures_Permissions(DataTable table) {
        List<String> features = table.asList(); // Extract each feature as a List<String>
        UserrolePage.selectfeatures(features);
    }

    @And("click the Edit button for the existing role {string}")
    public void user_clicksEditRole(String RoleName) {
        UserrolePage.clickOnActionForRole(RoleName);
    }

    @When("click the Delete button for the existing role {string}")
    public void userClicksOn_DeleteIcon(String RoleName) {
        UserrolePage.clickOnRoleDeleteBtn(RoleName);
    }

    @And("user clicks on delete confirm button")
    public void userClicksOnConfirmButton() {
        UserrolePage.clickOnRoleConfirmDelBtn();
    }

    @And("clicks the Save button")
    public void clickSave() {
        UserrolePage.clickOnSave();
    }

}


