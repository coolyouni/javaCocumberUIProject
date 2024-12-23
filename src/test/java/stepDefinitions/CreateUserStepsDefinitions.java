package stepDefinitions;

import commonUtils.*;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageClass.CreateUsersPage;
import pageClass.DashboardPage;
import pageClass.UsersListPage;



public class CreateUserStepsDefinitions {

    DashboardPage dashboardPage = new DashboardPage(DriverUtil.getDefaultDriver());
    UsersListPage usersListPage = new UsersListPage(DriverUtil.getDefaultDriver());
    CreateUsersPage createUsersPage = new CreateUsersPage(DriverUtil.getDefaultDriver());
    LogoutUtil logOutUtil = new LogoutUtil(DriverUtil.getDefaultDriver());


    @When("^User navigates to the User and Role Management screen$")
    public void navigatesToTheUserManagementMenu() {
        dashboardPage.navigatesToTheUserManagementScreen();
    }

    @When("User clicks on the create button")
    public void userClicksOnTheCreateButton() {
        usersListPage.clickOnCreateBtn();
    }

    @When("user selects {string} for create user")
    public void userSelectsForCreateUser(String title) {
        createUsersPage.selectTitle(title);
    }


    @And("User clicks on the Save button")
    public void userClicksOnTheSaveButton() {
        createUsersPage.clickOnSaveBtn();
    }

    @Then("^The user should be created successfully$")
    public void theUserShouldBeCreatedSuccessfully() {
        String firstName = TestData.getInstance().getFirstName();
        WebElement popUp = usersListPage.getPopUpMessage(firstName);
        Assert.assertTrue(popUp.isDisplayed(), "The user creation pop-up was not displayed correctly.");
        final String expectedText = String.format("Created %s", firstName);
        Assert.assertTrue(popUp.getText().equals(expectedText),
                "The pop-up text is incorrect. Expected: '" + expectedText + "', but found: '" + popUp.getText() + "'.");
    }


    @When("user clicks on edit icon")
    public void userClicksOnEditIcon() {
          usersListPage.clickOnEditBtn();
    }

    @When("user updates {string} field")
    public void userUpdatesField(String phone_number) {
        usersListPage.userUpdatesPhoneNumber(phone_number);
    }

    @And("click on save button")
    public void clickOnSaveButton() {
        usersListPage.clickOnUserEditSaveBtn();
    }

    @Then("success message {string} should appear")
    public void successMessageShouldAppear(String expectedMessage) {
        boolean isSuccessMessageDisplayed = usersListPage.verifySuccessPopup(expectedMessage);
        Assert.assertTrue(isSuccessMessageDisplayed,
                "The pop-up message was not displayed correctly or its content is incorrect.");
 }

    @When("user clicks on delete icon")
    public void userClicksOnDeleteIcon() {
        usersListPage.clickOnDeleteBtn();
    }

    @And("user clicks on confirm button")
    public void userClicksOnConfirmButton() {
        usersListPage.clickOnConfirmBtn();
    }


    @Then("success message {string} should appear for delete operation")
    public void successMessageShouldAppearForDeleteOperation(String expectedPopUpMessage) {
        boolean isSuccessMessageDisplayed = usersListPage.verifySuccessPopupMessage(expectedPopUpMessage);
        Assert.assertTrue(isSuccessMessageDisplayed,
                "The pop-up message was not displayed correctly or its content is incorrect.");
    }

    @And("user logs out")
    public void userLogsOut() throws InterruptedException {
        logOutUtil.logout();
    }
}

