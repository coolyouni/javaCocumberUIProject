package stepDefinitions;

import commonUtils.Config;
import commonUtils.DriverUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageClass.DashboardPage;
import pageClass.HomePage;
import pageClass.LoginPage;
import pageClass.RegistrationPage;

public class LogInStepsDefinitions {
    public LogInStepsDefinitions() {
        super();
    }


    private static final RegistrationPage registrationPage = new RegistrationPage(DriverUtil.getDefaultDriver());
    private static final Config config = new Config();
    private static final DashboardPage dashboard = new DashboardPage(DriverUtil.getDefaultDriver());
    public LoginPage login = new LoginPage(DriverUtil.getDefaultDriver());
    private static final HomePage homePage = new HomePage(DriverUtil.getDefaultDriver());


    @Given("user on CBT website")
    public void userOnCBTWebsite() {
        DriverUtil.getDefaultDriver().get(config.getBaseUrl());

    }


    @When("enter CBT account {string}")
    public void enterCBTAccount(String userName) {
        login.sendUserInputValue(userName);


    }
    @And("enter the password  {string}")
    public void enterThePassword(String password) {
        login.sendPassInputValue(password);
    }

    @And("click on login button")
    public void clickOnLoginButton() {
        login.logInClick();
    }

    @Then("user will be on the dashboard")
    public void userWillBeOnTheDashboard() {
        dashboard.getCurrentURL(dashboard.url);
    }

    @And("will see the logged user name displayed as {string}")
    public void willSeeTheLoggedUserNameDisplayedAs(String welcome) {
        boolean isPopupDisplayedCorrectly = dashboard.verifySuccessWelcome(welcome);
        Assert.assertTrue(isPopupDisplayedCorrectly, "Success popup message '" + welcome + "' mismatch or not displayed properly.");
    }

    @And("the dashboard will contain the tab {string}")
    public void theDashboardWillContainTheTab(String tabName) {
        boolean isDisplayedCorrectly = dashboard.verifySuccess(tabName);
        Assert.assertTrue(isDisplayedCorrectly, "Success message '" + tabName + "' mismatch or not displayed properly.");
    }

    @Then("the user should see an error message {string}")
    public void theUserShouldSeeAnErrorMessage(String expectedMessage) throws InterruptedException {
        boolean isPopupDisplayedCorrectly = login.verifyInvalidPopup(expectedMessage);
        Thread.sleep(3000);
        Assert.assertTrue(isPopupDisplayedCorrectly, "Success popup message '" + expectedMessage + "' mismatch or not displayed properly.");
    }

    @Then("the user should see a required message {string}")
    public void theUserShouldSeeARequiredMessage(String expectedMessage) {

        boolean isPopupDisplayedCorrectly = login.verifyEmptyFieldsPopup(expectedMessage);
        Assert.assertTrue(isPopupDisplayedCorrectly, "Success popup message '" + expectedMessage + "' mismatch or not displayed properly.");
    }


    @Then("the user should see a error message {string}")
    public void theUserShouldSeeAErrorMessage(String expectedMessage) {
        boolean isPopupDisplayedCorrectly = login.verifyEmptyFieldsPopup(expectedMessage);
        Assert.assertTrue(isPopupDisplayedCorrectly, "Success popup message '" + expectedMessage + "' mismatch or not displayed properly.");
    }

    @Then("user should see an error message {string}")
    public void userShouldSeeAnErrorMessage(String expectedMessage) {
        boolean isPopupDisplayedCorrectly = login.verifyEmptyFieldsPopup(expectedMessage);
        Assert.assertTrue(isPopupDisplayedCorrectly, "Success popup message '" + expectedMessage + "' mismatch or not displayed properly.");
    }

    @When("user clicks on login without entering the email and password")
    public void userClicksOnLoginWithoutEnteringTheEmailAndPassword() {
        login.logInClick();
    }

    @Then("validate the empty field error message {string}")
    public void validateTheEmptyFieldErrorMessage(String expectedMessage) {
        boolean isPopupDisplayedCorrectly = login.verifyEmptyFieldsPopup(expectedMessage);
        Assert.assertTrue(isPopupDisplayedCorrectly, "Success popup message '" + expectedMessage + "' mismatch or not displayed properly.");
    }

    @When("enter invalid username {string}")
    public void enterInvalidUsername(String userName) {
        login.sendUserInputValue(userName);
    }

    @And("enter the valid password  {string}")
    public void enterTheValidPassword(String password) {
        login.sendPassInputValue(password);
    }

    @Given("user is Login page")
    public void userIsLoginPage() {
        Assert.assertTrue(homePage.getCurrentURL("http://cbtqa.airretailer.local/dashboard"), "Actual Url same as Expected Url");
    }
}
