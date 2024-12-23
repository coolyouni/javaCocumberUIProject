Feature: Create and Edit Subsite

  @endtoend
    @sanity
  Scenario Outline: User create new subsite
    Given user is Login page
    Then the user navigate to the Corporate Subsite page
    When user select add new entity under existing organization
    When the user enters the Company Name "<Company_Name>"
    And the user selects organization Type "<Organization_Type>"
    And the user selects AgentType "<AgentType>"
    And the user enters IATA No "<IATA_No>"
    And the user enters Phone Number "<Phone_Number>" with country code
    And the user enters branch code "<Branch_code>"
    And the user selects Country "<Country>"
    And the user enters City "<City>"
    And the user selects Time zone
    And the user enters Address "<Address>"
    And the user enters State "<State>"
    And the user enters Postcode "<Postcode>"
    And the user enters Email "<Email>"
    And the user enter Description "<Description>"
    And the user saves the form
    Then a success popup should appear "Created" with Company name

    Examples:
      | Company_Name | Organization_Type | AgentType | IATA_No | Phone_Number   | Branch_code | Country              | City  | Address | State   | Email   | Postcode | Description |
      | DYNAMIC      | CORPORATE         | IATA      | DYNAMIC | +971-552236974 | AB123       | United Arab Emirates | Dubai | DYNAMIC | DYNAMIC | DYNAMIC | DYNAMIC  | DYNAMIC     |

  @sanity
  Scenario Outline: User update existing organization
    Given user is Login page
    Then the user navigate to the Corporate Subsite page
    And the user has selected a company to update
    #And the user enters the Company Name "<Company_Name>"
    And the user should see the form pre-filled with existing data
    And the user enters branch code "AB123"
    And the user enters state "Deira"
    And the user enters Postcode "123456"
    And the user disable the OTP
    And the user saves the form
    Then a success popup should appear "Updated" with Company name
    Examples:
      | Company_Name |
      | DYNAMIC      |

  @sanity
  Scenario: User deactivate the created subsite
    Given user is Login page
    Then the user navigate to the Corporate Subsite page
    When the user selects a subsite to deactivate
    And deactivates the selected subsite
    Then a success message "Entity Updated successfully" should appear

  @endtoend
  @sanity
  Scenario: User delete the created subsite
    Given user is Login page
    Then the user navigate to the Corporate Subsite page
    And the user has selected a company to delete
    When the user confirms the deletion
    Then a success message "Sub entity deleted successfully" should appear


