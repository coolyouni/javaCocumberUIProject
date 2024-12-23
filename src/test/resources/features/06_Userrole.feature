Feature: User Role Creation

  @endtoend
  Scenario Outline: As an User I want to create Admin role and provide permissions and access to all features.
    Given the user navigates to user role and management page
    And the User clicks on role management
    And validates existing admin role "<Role_name>" and deletes
    And clicks the create button
    When the admin enters a role name "<Role_name>"
    And enter the valid Role description "<Description>"
    And clicks the Features & Permissions
    And select All permissions
    And clicks the Save button
    Then a success popup with "<successful_message>" should appear
#    And click the Delete button for the existing role "<Role_name>"
#    And user clicks on delete confirm button
    Examples:
      | Role_name | Description | successful_message |
      | AdminRole | Full access | role               |


  @endtoend
  Scenario Outline: Create a user role with minimum features
    Given the user navigates to user role and management page
    And the User clicks on role management
    And clicks the create button
    When the admin enters a role name "<Role_name>"
    And enter the valid Role description "<Description>"
    And clicks the Features & Permissions
    And selects Minimal Features & Permissions
      | Dashboard                |
      | Corporate / Agency Setup |
    And clicks the Save button
    Then a success popup with "<successful_message>" should appear
    Examples:
      | Role_name | Description    | successful_message |
      | NewRole   | Minimal access | Created role       |

  @endtoend
  Scenario Outline: Edit the role and add additional features
    Given the user navigates to user role and management page
    And the User clicks on role management
    And click the Edit button for the existing role "<Role_name>"
    And clicks the Features & Permissions
    And selects Minimal Features & Permissions
      | Traveller profile   |
    And clicks the Save button
    Then a success popup with "<successful_message>" should appear
    Examples:
      | Role_name | successful_message |
      | NewRole   | Role updated successfully      |


  @endtoend
  Scenario Outline: Delete the role
    Given the user navigates to user role and management page
    And the User clicks on role management
    And click the Delete button for the existing role "<Role_name>"
    And user clicks on delete confirm button
    Then a success popup with "<successful_message>" should appear
    Examples:
      | Role_name | successful_message |
      | NewRole   | Deleted role       |
