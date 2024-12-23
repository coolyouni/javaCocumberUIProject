Feature: AirBooking Registration

  @endtoend
  Scenario Outline: Corporate registration IATA
    Given the user navigate to the login page
    And the user click on the signup link
    When the user fills in the corporate registration form with:
      | Company_Name | AgentType | IATA_No | Title | First_Name | Last_Name | Email   | Designation | Location | Country              | State   | City  | Postcode | Phone_Number |
      | DYNAMIC      | IATA      | DYNAMIC | Mr    | DYNAMIC    | DYNAMIC   | DYNAMIC | DYNAMIC     | DYNAMIC  | United Arab Emirates | DYNAMIC | Dubai | DYNAMIC  | DYNAMIC      |
    And the user agrees to the Terms of Service and Privacy Policy
    And the user submits the registration form
    Then a success popup with "<successful_message>" should appear
    Examples:
      | successful_message      |
      | Registration successful |

  Scenario Outline: Corporate registration for Non-IATA
    Given the user navigate to the login page
    And the user click on the signup link
    When the user fills in the corporate registration form with:
      | Company_Name | AgentType | Bilateral_code | Title | First_Name | Last_Name | Email   | Designation | Location | Country              | State   | City  | Postcode | Phone_Number |
      | DYNAMIC      | NON_IATA  | DYNAMIC        | Mr    | DYNAMIC    | DYNAMIC   | DYNAMIC | DYNAMIC     | DYNAMIC  | United Arab Emirates | DYNAMIC | Dubai | DYNAMIC  | DYNAMIC      |
    And the user agrees to the Terms of Service and Privacy Policy
    And the user submits the registration form
    Then a success popup with "<successful_message>" should appear
    Examples:
      | successful_message      |
      | Registration successful |

  Scenario Outline: TMC Registration IATA
    Given the user navigate to the login page
    And the user click on the signup link
    And the user click TMC Tab for registration
    When the user fills in the corporate registration form with:
      | Company_Name | AgentType | IATA_No | Title | First_Name | Last_Name | Email   | Designation | Location | Country              | State   | City  | Postcode | Phone_Number |
      | DYNAMIC      | IATA      | DYNAMIC | Mr    | DYNAMIC    | DYNAMIC   | DYNAMIC | DYNAMIC     | DYNAMIC  | United Arab Emirates | DYNAMIC | Dubai | DYNAMIC  | DYNAMIC      |
    And the user agrees to the Terms of Service and Privacy Policy
    And the user submits the registration form
    Then a success popup with "<successful_message>" should appear
    Examples:
      | successful_message      |
      | Registration successful |

  Scenario Outline: TMC Registration for Non-IATA
    Given the user navigate to the login page
    And the user click on the signup link
    And the user click TMC Tab for registration
    When the user fills in the corporate registration form with:
      | Company_Name | AgentType | TIDS_NO | Title | First_Name | Last_Name | Email   | Designation | Location | Country              | State   | City  | Postcode | Phone_Number |
      | DYNAMIC      | NON_IATA  | DYNAMIC | Mr    | DYNAMIC    | DYNAMIC   | DYNAMIC | DYNAMIC     | DYNAMIC  | United Arab Emirates | DYNAMIC | Dubai | DYNAMIC  | DYNAMIC      |
    And the user agrees to the Terms of Service and Privacy Policy
    And the user submits the registration form
    Then a success popup with "<successful_message>" should appear
    Examples:
      | successful_message      |
      | Registration successful |