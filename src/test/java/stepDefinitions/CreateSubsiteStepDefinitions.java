package stepDefinitions;

import com.github.javafaker.Faker;
import commonUtils.Config;
import commonUtils.DriverUtil;
import commonUtils.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageClass.CorporateAgencyPage;
import pageClass.HomePage;
import pageClass.LoginPage;
import pageClass.RegistrationPage;

public class CreateSubsiteStepDefinitions {

    Faker faker = new Faker();
    private final LoginPage loginPage = new LoginPage(DriverUtil.getDefaultDriver());
    private final Config config = new Config();
    private final HomePage homePage = new HomePage(DriverUtil.getDefaultDriver());
    private final CorporateAgencyPage corporateAgencyPage = new CorporateAgencyPage(DriverUtil.getDefaultDriver());
    private final RegistrationPage registrationPage = new RegistrationPage(DriverUtil.getDefaultDriver());
    private final TestData testData = TestData.getInstance();


    @Then("the user navigate to the Corporate Subsite page")
    public void user_navigate_to_corporate_subsite_page() {
        homePage.clickMenu();
        homePage.clickCorporateAgencySetup();
        homePage.clickOrganization();
        String actPageHeading = "Corporate Subsite";
        Assert.assertEquals(actPageHeading, corporateAgencyPage.getPageHeading());
    }

    @When("the user has selected a company to update")
    public void user_select_existing_organization() {
        String companyName = TestData.getInstance().getCompanyName();
        corporateAgencyPage.clickThreeDotMenuForTheCreatedOrg(companyName);
        corporateAgencyPage.clickEditEntity();
    }

    @When("user select add new entity under existing organization")
    public void user_select_add_new_entity_under_existing_organization() {
        corporateAgencyPage.clickThreeDotMenuForTheFirstOrg();
        corporateAgencyPage.clickAddNewEntity();
    }

    @And("the user enters branch code {string}")
    public void user_enters_branch_code(String branchCode) {
        corporateAgencyPage.enterBranchCode(branchCode);
    }

    @And("the user enters state {string}")
    public void the_user_enters_state(String state){
        corporateAgencyPage.enterStateProvince(state);
    }

    @And("the user saves the form")
    public void save_the_form() {
        corporateAgencyPage.clickSave();
    }

    @Then("a success popup should appear {string} with Company name")
    public void SuccessPopupWithShouldAppear(String msg) {
        String expectedMessage = msg + " " + TestData.getInstance().getCompanyName();
        boolean isPopupDisplayedCorrectly = registrationPage.verifySuccessPopup(expectedMessage);
        Assert.assertTrue(isPopupDisplayedCorrectly, "Success popup message '" + expectedMessage + "' mismatch or not displayed properly.");
    }

    @And("the user selects organization Type {string}")
    public void select_organization_type(String orgType) {
        corporateAgencyPage.selectOrgType(orgType);
    }

    @When("the user enters Phone Number {string} with country code")
    public void the_user_enters_phone_number_with_country_code(String phnNumber) {
        String[] parts = phnNumber.split("-");
        String countryCode = parts[0];
        String phoneNumber = parts[1];
        corporateAgencyPage.selectCountryCode(countryCode);
        registrationPage.enterPhoneNumber(phoneNumber);
    }

    @When("the user selects Time zone")
    public void the_user_selects_time_zone() {
        String timeZone = "GMT + 4:00";
        corporateAgencyPage.selectTimeZone(timeZone);
    }

    @And("the user enters Address {string}")
    public void theUserEntersAddress(String addressInput){
        testData.setAddress(addressInput);
        String address= testData.getAddress();
        corporateAgencyPage.enterAddress(address);
    }

    @And("the user enters State {string}")
    public void theUserEntersState(String stateInput) {
        testData.setState(stateInput);
        String state = testData.getState();
        corporateAgencyPage.enterState(state);
    }

    @And("the user enter Description {string}")
    public void theUserEnterDescription(String descriptionInput) {
        testData.setDescription(descriptionInput);
        String description = testData.getDescription();
        corporateAgencyPage.enterDescription(description);
    }

    @And("the user has selected a company to delete")
    public void theUserHasSelectedACompanyToDelete() {
        String companyName = TestData.getInstance().getCompanyName();
        corporateAgencyPage.clickThreeDotMenuForTheCreatedOrg(companyName);
    }

    @When("the user confirms the deletion")
    public void theUserConfirmsTheDeletion() {
        corporateAgencyPage.clickDeleteEntity();
        corporateAgencyPage.clickConfirm();
    }

    @Then("a success message {string} should appear")
    public void aSuccessMessageShouldAppearWithTheCompanySName(String msg) {
        boolean isPopupDisplayedCorrectly = registrationPage.verifySuccessPopup(msg);
        Assert.assertTrue(isPopupDisplayedCorrectly, "Success popup message '" + msg + "' mismatch or not displayed properly.");
    }

    @And("the user should see the form pre-filled with existing data")
    public void theUserShouldSeeTheFormPreFilledWithExistingData() {
        String companyName = TestData.getInstance().getCompanyName();
        Assert.assertTrue(corporateAgencyPage.checkPreFilledData(companyName), "Verified the filled data");
    }

    @And("the user disable the OTP")
    public void theUserDisableTheOTP() {
        corporateAgencyPage.disableOTP();
    }

    @When("the user selects a subsite to deactivate")
    public void theUserSelectsASubsiteToDeactivate() {
        String companyName = TestData.getInstance().getCompanyName();
        corporateAgencyPage.clickThreeDotMenuForTheCreatedOrg(companyName);
    }

    @And("deactivates the selected subsite")
    public void deactivatesTheSelectedSubsite() {
        corporateAgencyPage.clickToDeactivateOrg();
        corporateAgencyPage.clickConfirm();
    }
}
