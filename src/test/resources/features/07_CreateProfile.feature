@endtoend
Feature: Create Profile and Adding Contact information and Passport Information

  Scenario Outline: Create a profile
    Given user on CBT website
    When enter CBT account "mak@mailinator.com"
    And enter the password  "123456Aa@@@"
    And click on login button
    Then user will be on the dashboard
    When User navigates to the travel profile screen
    When User clicks on the create button
    When user selects "<Title_for_Create_user>" for create user
    And  User enters the following details in profile form:
      | First_Name | Last_Name | Middle_name | Email   |
      | DYNAMIC    | DYNAMIC   | DYNAMIC     | DYNAMIC |
    And  User add Personal details:
      | Gender | Date_of_Birth | Residence      | Preffered_language | Suffix  | Nationality          | Role  |
      | Male   |               | United Kingdom | English            | DYNAMIC | United Arab Emirates | Admin |
    And  User clicks on the Save button
    Then a success popup with "<successful_message>" should appear
    Examples:
      | Title_for_Create_user | successful_message           |
      | Mr                    | Profile Created Successfully |

  Scenario Outline:  Adding contact information for that profile
    When user clicks on the View button for view profile
    And user click on My Profile button
    And expand "<Title_for_Label_heading>" for adding contact
    And user adds contact information for "<Contact_Type>"
    And  User clicks on the Save button
    Then a success popup with "<successful_message_Contact_creation>" should appear
    Examples:
      | Title_for_Label_heading | Contact_Type | successful_message_Contact_creation |
      | Contact Info            | Mobile Phone | Contact created                     |

  Scenario Outline: Adding passport details
    And expand "<Title_for_Travel_documents>" for adding passport information
    And user add document type for "<document_Type>" passport information
    And  User clicks on the Save button
    Then a success popup with "<successful_message_passport_information>" should appear
    Examples:
      | Title_for_Travel_documents | document_Type | successful_message_passport_information |
      | Travel Documents           | Passport      | Document added successfully             |