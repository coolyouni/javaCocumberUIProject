package commonUtils;

import java.time.LocalDate;
import java.util.Random;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static commonUtils.DriverUtil.driver;

public class TestData {

    private static TestData instance;

    private final Random random = new Random();
    Faker faker = new Faker();

    // Test Data Fields
    private String companyName;
    private String agentType;
    private String bilateralCode;
    private String iataNumber;
    private String title;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String designation;
    private String location;
    private String country;
    private String state;
    private String city;
    private String zipCode;
    private String phoneNumber;
    private String tidsNumber;
    private String address;
    private String description;
    private int noOfDatePicker
    private String Description;
    //private String Rolename;

    // Private Constructor for Singleton Pattern
    public TestData() {
    }

    public static synchronized TestData getInstance() {
        if (instance == null) {
            instance = new TestData();
        }
        return instance;
    }

    // Random Data Generation Methods
    public String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder randomString = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            randomString.append(characters.charAt(random.nextInt(characters.length())));
        }
        return randomString.toString();
    }

    public String generateRandomNumericString(int length) {
        StringBuilder randomNumber = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            randomNumber.append(random.nextInt(10));
        }
        return randomNumber.toString();
    }

    public String generateRandomEmail() {
        return "uitest" + System.currentTimeMillis() + "@mailinator.com";
    }

    public String generatePhoneNumber() {
        int[] validStartingDigits = {2, 3, 4, 6, 7};
        int startingDigit = validStartingDigits[random.nextInt(validStartingDigits.length)];
        return startingDigit + generateRandomNumericString(7);
    }

    // Getter and Setter for Company Name
    public String getCompanyName() {
        if (this.companyName == null || this.companyName.equals("DYNAMIC")) {
            //this.companyName = generateRandomString(10); // Generate a random company name
            //this.companyName = faker.company().name();
            this.companyName = faker.company().name().replaceAll("[^a-zA-Z0-9 ']", "");
        }
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    // Getter and Setter for Agent Type
    public String getAgentType() {
        return this.agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    // Getter and Setter for Bilateral Code
    public String getBilateralCode() {
        if (this.bilateralCode == null || this.bilateralCode.equals("DYNAMIC")) {
            this.bilateralCode = generateRandomNumericString(7); // Generate a random bilateral code
        }
        return this.bilateralCode;
    }

    public void setBilateralCode(String bilateralCode) {
        this.bilateralCode = bilateralCode;
    }

    // Getter and Setter for IATA Number
    public String getIataNumber() {
        if (this.iataNumber == null || this.iataNumber.equals("DYNAMIC")) {
            this.iataNumber = generateRandomNumericString(7); // Generate a random IATA number
        }
        return this.iataNumber;
    }

    public void setIataNumber(String iataNumber) {
        this.iataNumber = iataNumber;
    }

    // Getter and Setter for TIDS Number
    public String getTidsNumber() {
        if (this.tidsNumber == null || this.tidsNumber.equals("DYNAMIC")) {
            this.tidsNumber = generateRandomNumericString(13); // Generate a random TIDS number
        }
        return this.tidsNumber;
    }

    public void setTidsNumber(String tidsNumber) {
        this.tidsNumber = tidsNumber;
    }

    // Getter and Setter for Phone Number
    public String getPhoneNumber() {
        if (this.phoneNumber == null || this.phoneNumber.equals("DYNAMIC")) {
            this.phoneNumber = generatePhoneNumber(); // Generate a random phone number
        }
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getter and Setter for Email
    public String getEmail() {
        if (this.email == null || this.email.equals("DYNAMIC")) {
            this.email = generateRandomEmail(); // Generate a random email
        }
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for Title
    public String getTitle() {
        if (this.title == null || this.title.equals("DYNAMIC")) {
            this.title = generateRandomString(5); // Generate a random title
        }
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and Setter for First Name
    public String getFirstName() {
        if (this.firstName == null || this.firstName.equals("DYNAMIC")) {
            //this.firstName = generateRandomString(8); // Generate a random first name
            this.firstName = faker.name().firstName().replaceAll("[-'&|=+*/><., ]", "");
            ;
        }
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter and Setter for Last Name
    public String getLastName() {
        if (this.lastName == null || this.lastName.equals("DYNAMIC")) {
            //this.lastName = generateRandomString(8); // Generate a random last name
            this.lastName = faker.name().lastName().replaceAll("[-'&|=+*/><., ]", "");
            ;
        }
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter and Setter for Last Name
    public String getMiddleName() {
        if (this.middleName == null || this.middleName.equals("DYNAMIC")) {
            this.middleName = faker.name().lastName().replaceAll("[-'&|=+*/><., ]", "");
            ;
        }
        return this.middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    // Getter and Setter for Designation
    public String getDesignation() {
        if (this.designation == null || this.designation.equals("DYNAMIC")) {
            //this.designation = generateRandomString(10); // Generate a random designation
            this.designation = faker.name().title().replaceAll("[-'&|=+*/><., ]", "");
        }
        return this.designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    // Getter and Setter for Location
    public String getLocation() {
        if (this.location == null || this.location.equals("DYNAMIC")) {
            //this.location = generateRandomString(15); // Generate a random location
            this.location = faker.address().fullAddress();
        }
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // Getter and Setter for State
    public String getState() {
        if (this.state == null || this.state.equals("DYNAMIC")) {
            // this.state = generateRandomString(10); // Generate a random state
            this.state = faker.address().state().replaceAll("[-'&|=+*/><., ]", "");
        }
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // Getter and Setter for Zip Code
    public String getZipCode() {
        if (this.zipCode == null || this.zipCode.equals("DYNAMIC")) {
            //this.zipCode = generateRandomNumericString(6); // Generate a random zip code
            this.zipCode = faker.address().zipCode().replace("-", "");
        }
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    // Getter and Setter for Address
    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getAddress() {
        if (this.address == null || this.address.equals("DYNAMIC")) {
            this.address = faker.address().streetAddress();
        }
        return this.address;
    }

    // Getter and Setter for Description
    public void setDescription(String description) {
        this.Description = description;
    }

    public String getDescription() {
        if (this.Description == null || this.Description.equals("DYNAMIC")) {
            this.Description = "It is a leading provider of innovative solutions in IT, committed to delivering " +
                    "exceptional products and services to clients worldwide.";
        }
        return this.Description;
    }

    public int getNoOfDatePicker() {
        return this.noOfDatePicker;
    }

    public void setNoOfDatePicker(int noOfDatePicker) {
        this.noOfDatePicker = noOfDatePicker;
    }

    public void selectNextDate(By dateLocator, int noOfDays) throws InterruptedException {

        WaitUtil.waitForElementToBeClickable(driver, dateLocator).click();
        LocalDate today = LocalDate.now();
        LocalDate travellingDate;
        travellingDate = today.plusDays(noOfDays);

        // Extract the day, month, and year
        String day = String.valueOf(travellingDate.getDayOfMonth());
        String month = travellingDate.getMonth().name().substring(0, 3).toLowerCase(); // First 3 letters of the month in lowercase
        String year = String.valueOf(travellingDate.getYear());

        while (true) {
            WebElement monthButton = WaitUtil.waitForElementToBeVisible(driver, By.xpath("(//button[@class='ant-picker-month-btn'])" + "[" + getNoOfDatePicker() + "]"));

            String mon = monthButton.getText().toLowerCase();

            // Wait for the year button to be visible and get its text
            WebElement yearButton = WaitUtil.waitForElementToBeVisible(driver, By.xpath("(//button[@class='ant-picker-year-btn'])" + "[" + getNoOfDatePicker() + "]"));

            String yr = yearButton.getText();
            if (mon.equals(month) && yr.equals(year)) {
                break;
            }
            driver.findElement(By.xpath("(//button[@class='ant-picker-header-next-btn'])[" + getNoOfDatePicker() + "]")).click();
        }
        // Date selection
        for (WebElement dateSel : driver.findElements(By.xpath("//table[@class=\"ant-picker-content\"]//td"))) {
            if (dateSel.getText().equals(day)) {
                dateSel.click();
                break;
            }
        }
        setNoOfDatePicker(1);
    }

    public void selectPreviousDate(By dateLocator, int noOfDays) {

        WaitUtil.waitForElementToBeClickable(driver, dateLocator).click();

        LocalDate today = LocalDate.now();
        LocalDate travellingDate;
        travellingDate = today.minusDays(noOfDays);

        // Extract the day, month, and year
        String day = String.valueOf(travellingDate.getDayOfMonth());
        String month = travellingDate.getMonth().name().substring(0, 3).toLowerCase(); // First 3 letters of the month in lowercase
        String year = String.valueOf(travellingDate.getYear());
        // Month and year selection
        while (true) {

            WebElement monthButton = WaitUtil.waitForPresenceOfElement(driver, By.className("ant-picker-month-btn"));
            String mon = monthButton.getText().toLowerCase();

            // Wait for the year button to be visible and get its text
            WebElement yearButton = WaitUtil.waitForPresenceOfElement(driver, By.className("ant-picker-year-btn"));
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