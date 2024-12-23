Feature: Login

  Scenario: Login to CBT
    Given user on CBT website
    When enter CBT account "mak@mailinator.com"
    And enter the password  "123456Aa@@@"
    And click on login button
    Then user will be on the dashboard
#    And will see the logged user name displayed as "Welcome to your Dashboard, Mosa"
    And the dashboard will contain the tab "My Travel"

  @sanity
  Scenario: Login to CBT with invalid credentials
    Given user on CBT website
    When user clicks on login without entering the email and password
    Then validate the empty field error message "Please fill all the required fields"
    When enter invalid username "makaaaa@mailinator.com"
    And enter the valid password  "123456Aa@@"
    And click on login button
    Then the user should see an error message "Invalid username or password. Please check your credentials and try again."

  @endtoend
  @sanity
  Scenario: Login to CBT
    Given user on CBT website
    When enter CBT account "mak@mailinator.com"
    And enter the password  "123456Aa@@@"
    And click on login button
    Then user will be on the dashboard
#    And will see the logged user name displayed as "Welcome to your Dashboard, Mosa"
    And the dashboard will contain the tab "My Travel"


