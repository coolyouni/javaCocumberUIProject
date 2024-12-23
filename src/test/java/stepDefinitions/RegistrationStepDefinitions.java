package stepDefinitions;

import commonUtils.Config;
import commonUtils.DriverUtil;
import commonUtils.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageClass.RegistrationPage;

import java.util.List;
import java.util.Map;

public class RegistrationStepDefinitions {

    private final RegistrationPage registrationPage = new RegistrationPage(DriverUtil.getDefaultDriver());
    private final Config config = new Config();
    private final TestData testData = TestData.getInstance(); // Singleton instance

    @Given("the user navigate to the login page")
    public void navigateToRegistrationPage() {
        DriverUtil.getDefaultDriver().get(config.getBaseUrl());
    }

    @And("the user click on the signup link")
    public void ClicksOnTheSignupLink() {
        registrationPage.clickOnSignup();
    }

    @When("the user enters the Company Name {string}")
    public void theUserEntersTheCompanyName(String companyNameInput) {
        testData.setCompanyName(companyNameInput);
        String companyName = testData.getCompanyName();
        registrationPage.enterCompanyName(companyName);
    }

    @And("the user selects AgentType {string}")
    public void theUserSelectsAgentType(String agentTypeInput) throws InterruptedException {
        testData.setAgentType(agentTypeInput);
        String agentType = testData.getAgentType();
        registrationPage.selectAgentType(agentTypeInput);
    }

    @And("the user enters Bilateral Payer code No {string}")
    public void theUserEntersBilateralPayerCodeNo(String bilateralCodeInput) {
        testData.setBilateralCode(bilateralCodeInput);
        String bilateralCode = testData.getBilateralCode();
        registrationPage.enterBilateralCode(bilateralCode);
    }

    @And("the user enters IATA No {string}")
    public void theUserEntersIATANo(String iataNumberInput) {
        testData.setIataNumber(iataNumberInput);
        String iataNumber = testData.getIataNumber();
        registrationPage.enterIATANumber(iataNumber);
    }

    @And("the user selects Title {string}")
    public void theUserSelectsTitle(String titleInput) {
        testData.setTitle(titleInput);
        String title = testData.getTitle();
        registrationPage.selectTitle(title);
    }

    @And("the user enters First Name {string}")
    public void theUserEntersFirstName(String firstNameInput) {
        testData.setFirstName(firstNameInput);
        String firstName = testData.getFirstName();
        registrationPage.enterFirstName(firstName);
    }

    @And("the user enters Last Name {string}")
    public void theUserEntersLastName(String lastNameInput) {
        testData.setLastName(lastNameInput);
        String lastName = testData.getLastName();
        registrationPage.enterLastName(lastName);
    }

    @And("the user enters Middle Name {string}")
    public void theUserEntersMiddleName(String middleNameInput) {
        testData.setMiddleName(middleNameInput);
        String middleName = testData.getMiddleName();
        registrationPage.enterMiddleName(middleName);
    }

    @And("the user enters Email {string}")
    public void theUserEntersEmail(String emailInput) {
        testData.setEmail(emailInput);
        String email = testData.getEmail();
        registrationPage.enterEmail(email);
    }

    @And("the user enters Designation {string}")
    public void theUserEntersDesignation(String designationInput) {
        testData.setDesignation(designationInput);
        String designation = testData.getDesignation();
        registrationPage.enterDesignation(designation);
    }

    @And("the user enters Location {string}")
    public void theUserEntersLocation(String locationInput) {
        testData.setLocation(locationInput);
        String location = testData.getLocation();
        registrationPage.enterLocation(location);
    }

    @And("the user selects Country {string}")
    public void theUserSelectsCountry(String countryInput) {
        testData.setCountry(countryInput);
        String country = testData.getCountry();
        registrationPage.selectCountry(country);
    }

    @And("the user selects State {string}")
    public void theUserSelectsState(String stateInput) {
        testData.setState(stateInput);
        String state = testData.getState();
        registrationPage.enterState(state);
    }

    @And("the user enters City {string}")
    public void theUserEntersCity(String cityInput) {
        testData.setCity(cityInput);
        String city = testData.getCity();
        registrationPage.selectCity(city);
    }

    @And("the user enters Postcode {string}")
    public void theUserEntersPostcode(String zipCodeInput) {
        testData.setZipCode(zipCodeInput);
        String zipCode = testData.getZipCode();
        registrationPage.enterZipCode(zipCode);
    }

    @And("the user enters Phone Number {string}")
    public void theUserEntersPhoneNumber(String phoneNumberInput) {
        testData.setPhoneNumber(phoneNumberInput);
        String phoneNumber = testData.getPhoneNumber();
        registrationPage.enterPhoneNumber(phoneNumber);
    }

    @And("the user agrees to the Terms of Service and Privacy Policy")
    public void theUserAgreesToTheTerms() {
        registrationPage.agreeToTerms();
    }

    @And("the user submits the registration form")
    public void theUserSubmitsTheForm() {
        registrationPage.submitForm();
    }

    @Then("a success popup with {string} should appear")
    public void SuccessPopupWithShouldAppear(String expectedMessage) {
        boolean isPopupDisplayedCorrectly = registrationPage.verifySuccessPopup(expectedMessage);
        Assert.assertTrue(isPopupDisplayedCorrectly, "Success popup message '" + expectedMessage + "' mismatch or not displayed properly.");
    }

    @And("the user click TMC Tab for registration")
    public void theUserClickTMCTabForRegistration() {
        registrationPage.clickOnTMCTab();
    }

    @And("the user enters TIDS No {string}")
    public void theUserEntersTIDSNo(String tidsNumberInput) {
        testData.setTidsNumber(tidsNumberInput);
        String tidsNumber = testData.getTidsNumber();
        registrationPage.enterTidsNo(tidsNumber);
    }

    //@And("User enters the following details in {string} form :")
    @And("User enters the following details in profile form:")
    @And("^User enters the following details:$")
    @When("the user fills in the corporate registration form with:")
    public void theUserFillsInTheCorporateRegistrationFormWith(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        // Convert the DataTable into a Map for easier access
        List<Map<String, String>> formFieldsList = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> formFields : formFieldsList) {

            // Call the individual functions for each field
            if (formFields.containsKey("Company_Name")) {
                theUserEntersTheCompanyName(formFields.get("Company_Name"));
            }

            if (formFields.containsKey("AgentType")) {
                theUserSelectsAgentType(formFields.get("AgentType"));
            }

            if (formFields.containsKey("Bilateral_code")) {
                theUserEntersBilateralPayerCodeNo(formFields.get("Bilateral_code"));
            }

            if (formFields.containsKey("IATA_No")) {
                theUserEntersIATANo(formFields.get("IATA No"));
            }

            if (formFields.containsKey("Title")) {
                theUserSelectsTitle(formFields.get("Title"));
            }

            if (formFields.containsKey("First_Name")) {
                theUserEntersFirstName(formFields.get("First_Name"));
            }

            if (formFields.containsKey("Last_Name")) {
                theUserEntersLastName(formFields.get("Last_Name"));
            }

            if (formFields.containsKey("Middle_name")) {
                theUserEntersMiddleName(formFields.get("Middle_name"));
            }

            if (formFields.containsKey("Email")) {
                theUserEntersEmail(formFields.get("Email"));
            }

            if (formFields.containsKey("Designation")) {
                theUserEntersDesignation(formFields.get("Designation"));
            }

            if (formFields.containsKey("Location")) {
                theUserEntersLocation(formFields.get("Location"));
            }

            if (formFields.containsKey("Country")) {
                theUserSelectsCountry(formFields.get("Country"));
            }

            if (formFields.containsKey("State")) {
                theUserSelectsState(formFields.get("State"));
            }

            if (formFields.containsKey("City")) {
                theUserEntersCity(formFields.get("City"));
            }

            if (formFields.containsKey("Postcode")) {
                theUserEntersPostcode(formFields.get("Postcode"));
            }

            if (formFields.containsKey("Phone_Number")) {
                theUserEntersPhoneNumber(formFields.get("Phone Number"));
            }

            if (formFields.containsKey("TIDS_NO")) {
                theUserEntersTIDSNo(formFields.get("TIDS_NO"));
            }
        }
    }
}