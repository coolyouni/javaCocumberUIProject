package pageClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commonUtils.WaitUtil;

public class OrderReviewPage extends BasePage {

	private By pageHeading = By.xpath("//div[@class='ant-col ant-col-md-8']//h2");
	private By subscriptionAgreementLink = By.xpath("//a[text()='Subscription Agreement']");
//	private By proceedToPaymentButton = By.xpath("//button[contains(@class, 'ant-btn-primary')]//span[normalize-space(text())='Proceed To Payment']");
	private By stripePopup = By.xpath("//h3[@class='ma-0']");

	public OrderReviewPage(WebDriver driver) {
		super(driver);
	}

	public String getPageTitle() {
		return getText(pageHeading);
	}

	public void clickCheckboxIfNotSelected() {
		WaitUtil.scrollUpUntilElementClickable(driver, subscriptionAgreementLink);
	}

	public void clickProceedToPaymentButton(String buttonLabel) {
		// Build the XPath dynamically based on the buttonLabel
		
		//button[contains(@class, 'ant-btn-primary')]//span[normalize-space(text())=+'buttonLabel']
		By buttonLocator = By.xpath("//button[contains(@class, 'ant-btn-primary')]//span[normalize-space(text())='" + buttonLabel + "']");
		
	    // Wait for the button to be clickable
	    WaitUtil.scrollUpUntilElementClickable(driver, buttonLocator);
		
//		WaitUtil.scrollUpUntilElementClickable(driver, proceedToPaymentButton);
//		clickElement(proceedToPayment);
	}

	public boolean isStripePopupDisplayed() {
		try {
			WebElement popup = findElement(stripePopup);
			return popup.isDisplayed();
		} catch (Exception e) {
			return false; // If the popup is not found, return false
		}
	}

}
