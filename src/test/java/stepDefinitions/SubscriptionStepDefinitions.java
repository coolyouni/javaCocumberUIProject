package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import commonUtils.DriverUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageClass.OrderReviewPage;
import pageClass.PackageSelectionPage;
import pageClass.PaymentPage;

public class SubscriptionStepDefinitions {

	private WebDriver driver = DriverUtil.getDefaultDriver();
	public final PackageSelectionPage packageSelectionPage;
	public final OrderReviewPage orderReviewPage;
	public final PaymentPage paymentPage;

	public SubscriptionStepDefinitions() {
		packageSelectionPage = new PackageSelectionPage(driver);
		orderReviewPage = new OrderReviewPage(driver);
		paymentPage = new PaymentPage(driver);
	}

	@Given("the user is on the package selection page")
	public void theUserIsOnThePackageSelectionPage() {
		String pageHeading = packageSelectionPage.getPageTitle();
		Assert.assertEquals(pageHeading, "Plans and Pricing");
	}

	@When("the user selects the {string} billing option")
	public void theUserSelectsTheBillingOption(String billingOption) {
		packageSelectionPage.selectBillingOption(billingOption); // Call the method to select the billing option
	}

	@And("the user selects the package {string}")
	public void theUserSelectsThePackage(String packageName) {
		packageSelectionPage.selectPackageByName(packageName);
	}

	@And("the user clicks confirm to proceed to order review page")
	public void theUserClicksConfirmToProceedToOrderReviewPage() {
		packageSelectionPage.clickConfirmButton();
	}

	@Then("the user should be on the order review page")
	public void theUserShouldBeOnTheOrderReviewPage() {
		String pageHeading = orderReviewPage.getPageTitle();
		Assert.assertEquals(pageHeading, "Confirm Subscription");
	}

	@When("the user checks the checkbox to accept the subscription agreement")
	public void the_user_checks_the_checkbox_to_accept_the_subscription_agreement() {
		orderReviewPage.clickCheckboxIfNotSelected();
	}

	@And("the user clicks {string}")
	public void the_user_clicks(String buttonLabel) {
		orderReviewPage.clickProceedToPaymentButton(buttonLabel);
	}

	@Then("the Stripe payment popup should be displayed")
	public void the_stripe_payment_popup_should_be_displayed() {
		orderReviewPage.isStripePopupDisplayed();
	}

	@When("the user enters valid payment details {string}, {string}, and {string}")
	public void the_user_enters_valid_payment_details(String cardNumber, String expDate, String cvv) {
		// Pass valid card details here (example values)
		paymentPage.enterValidPaymentDetails(cardNumber, expDate, cvv);
	}

	@And("the user clicks pay now")
	public void the_user_clicks_pay_now() {
		paymentPage.clickPayNowButton();
	}

	@Then("the user should see a payment confirmation message")
	public void the_user_should_see_payment_confirmation_message() {
		String confirmationMessage = paymentPage.getPaymentConfirmationMessage();
		String expectedConfirmMessage = "Your payment is successful !";
		Assert.assertEquals(confirmationMessage, expectedConfirmMessage);
	}

	@When("the user clicks \"Back to Login\" after subscription confirmation")
	public void the_user_clicks_back_to_login() {
		paymentPage.clickBackToLogin();
	}

	@Then("the user should be redirected to the login page")
	public void the_user_should_be_redirected_to_the_login_page() {
		boolean isLoginPageVisible = paymentPage.verifyRedirectToLogin();
		Assert.assertTrue(isLoginPageVisible);
	}

}
