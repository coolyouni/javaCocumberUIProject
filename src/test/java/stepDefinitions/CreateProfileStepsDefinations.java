package stepDefinitions;

import commonUtils.DriverUtil;
import commonUtils.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import pageClass.*;
import java.util.List;
import java.util.Map;

public class CreateProfileStepsDefinations {

    DashboardPage dashboardPage = new DashboardPage(DriverUtil.getDefaultDriver());
    CreateProfilePage createProfilePage = new CreateProfilePage(DriverUtil.getDefaultDriver());
    private final TestData testData = TestData.getInstance();

    @When("^User navigates to the travel profile screen")
    public void navigatesToTheUserManagementMenu() {
        dashboardPage.navigatesToTheProfileScreen();
    }

    @And("the user selects Gender {string}")
    public void theUserSelectGender(String genderInput) {
        testData.setCountry(genderInput);
        String gender = testData.getCountry();
        createProfilePage.selectCountry(gender);
    }

    @And("the user select Date Of Birth")
    public void theUserEnterDOB(String dob) {
        createProfilePage.selectDateOfBirth(dob);
    }

    @And("the user select Residence {string}")
    public void theUserSelectResidence(String residence) {
        testData.setCountry(residence);
        String residences = testData.getCountry();
        createProfilePage.selectResidence(residences);
    }

    @And("the user select Preferred Language {string}")
    public void theUserPreferedLanguage(String preferredLanguage) {
        testData.setCountry(preferredLanguage);
        String lang = testData.getCountry();
        createProfilePage.selectPreferredLanguage(lang);
    }

    @And("the user select Nationality {string}")
    public void theUserSelectNationality(String nationaltiy) {
        testData.setCountry(nationaltiy);
        String nationalityinfo = testData.getCountry();
        createProfilePage.selectNationality(nationalityinfo);
    }

    @And("the user enters Suffix {string}")
    public void theUserEnterssuffix(String suffix) {
        testData.setDesignation(suffix);
        String suffixValue = testData.getDesignation();
        createProfilePage.enterSuffix(suffixValue);
    }

    @When("user clicks on the View button for view profile")
    public void userClicksOnTheViewButtonForViewProfile() {
        String email = TestData.getInstance().getEmail();
        createProfilePage.clickOnViewByEmail(email);
    }

    @And("user click on My Profile button")
    public void userClickOnMyProfileButton() {
        createProfilePage.clickOnMyProfile();
    }

    @And("expand {string} for adding passport information")
    @And("expand {string} for adding contact")
    public void expandContactInfoIfCollapsed(String title) {
        createProfilePage.expandContactInfoIfCollapsed(title);
    }

    @And("user adds contact information for {string}")
    public void userAddsContactInformationFor(String type) throws InterruptedException {
        createProfilePage.clickAddContactButton();
        createProfilePage.selectContactType(type);
        createProfilePage.addContactInformation(type);
    }

    @And("user add document type for {string} passport information")
    public void userAddDocumentTypeForPassportInformation(String document_type) throws InterruptedException {
        createProfilePage.clickAddDocumentButton();
        createProfilePage.selectContactType(document_type);
        createProfilePage.addTravelDocument(document_type);

    }

//    @And("the user select Role {string}")
//    public void theUserSelectRole(String roles) {
//        testData.setCountry(roles);
//        String role = testData.getCountry();
//        createProfilePage.selectRole(role);
//    }

    @And("User add Personal details:")
    public void userAddPersonalDetails(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        // Convert the DataTable into a Map for easier access
        List<Map<String, String>> formFieldsList = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> formFields : formFieldsList) {

            if (formFields.containsKey("Gender")) {
                theUserSelectGender(formFields.get("Gender"));
            }

            if (formFields.containsKey("Date_of_Birth")) {
                theUserEnterDOB(formFields.get("Date_of_Birth"));
            }

            if (formFields.containsKey("Residence")) {

                theUserSelectResidence(formFields.get("Residence"));
            }

            if (formFields.containsKey("Preffered_language")) {
                theUserPreferedLanguage(formFields.get("Preffered_language"));
            }

            if (formFields.containsKey("Suffix")) {
                theUserEnterssuffix(formFields.get("Suffix"));
            }

            if (formFields.containsKey("Nationality")) {
                theUserSelectNationality(formFields.get("Nationality"));
            }
        }
    }
}