Feature: Package Selection, Order Review, and Payment Flow

@endtoend
 	Scenario: User selects a package, reviews the order, and completes the payment
	  Given the user is on the package selection page
	  When the user selects the "Annual Billing" billing option
	  And the user selects the package "AutomationPack"
	  And the user clicks confirm to proceed to order review page
	  Then the user should be on the order review page
	  
	  When the user checks the checkbox to accept the subscription agreement
	  And the user clicks "Proceed To Payment"
	  Then the Stripe payment popup should be displayed
	  
	  When the user enters valid payment details "4111111111111111", "12/25", and "123"
	  And the user clicks pay now
	  Then the user should see a payment confirmation message
  
	  When the user clicks "Back to Login" after subscription confirmation
	  Then the user should be redirected to the login page
