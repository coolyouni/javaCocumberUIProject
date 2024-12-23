@endtoend
Feature: Create User,edit user and delete user

#  Background:
#    Given user on CBT website
#    When enter CBT account "mak@mailinator.com"
#    And enter the password  "123456Aa@@@"
#    And click on login button
#    Then user will be on the dashboard
#   # And will see the logged user name displayed as "Welcome to your Dashboard, Mosa"
#    And the dashboard will contain the tab "My Travel"


  Scenario Outline: Create User Functionality
    When User navigates to the User and Role Management screen
    When User clicks on the create button
    When user selects "<Title_for_Create_user>" for create user
    And  User enters the following details:
      | First_Name | Last_Name | Email   | Phone_Number |
      | DYNAMIC    | DYNAMIC   | DYNAMIC | DYNAMIC      |
    And  User clicks on the Save button
    Then The user should be created successfully
#    And user logs out
    Examples:
      |Title_for_Create_user |
      |Mr  |


  Scenario Outline: Edit user's phone number
    Given User navigates to the User and Role Management screen
    When user clicks on edit icon
    When user updates "<phone_number>" field
    And click on save button
    Then success message "<success_message>" should appear
#    And user logs out
    Examples:
      | phone_number | success_message|
      | 562267519    | User updated successfully.|


  Scenario Outline: Delete User Functionality
    Given User navigates to the User and Role Management screen
    When user clicks on delete icon
    And user clicks on confirm button
    Then success message "<success_message>" should appear for delete operation
    Examples:
     | success_message|
     | User deleted successfully.|

