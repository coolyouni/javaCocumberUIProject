package pageClass;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commonUtils.WaitUtil;

public class PackageSelectionPage extends BasePage {

	private By pageHeading = By.xpath("//h1");
	private By monthlyBillingButton = By.xpath("//button[span[text()='Monthly Billing']]");
	private By annualBillingButton = By.xpath("//button[span[text()='Annual Billing']]");
	private By leftArrowButton = By.xpath("//button[@class='splide__arrow splide__arrow--next' and @type='button']");
	private By confirmButton = By.xpath("//button[span[text()='Confirm']]");

	public PackageSelectionPage(WebDriver driver) {
		super(driver);
	}

	public String getPageTitle() {
		return getText(pageHeading);
	}

	public void selectBillingOption(String billingOption) {
		By billingButton;

		if (billingOption.equalsIgnoreCase("Monthly Billing")) {
			billingButton = monthlyBillingButton;
		} else if (billingOption.equalsIgnoreCase("Annual Billing")) {
			billingButton = annualBillingButton;
		} else {
			throw new IllegalArgumentException("Invalid billing option: " + billingOption);
		}

		// Click on the selected billing option
		clickElement(billingButton);
	}

	public void selectPackageByName(String packageName) {
		// Define the XPath for the package
		By packageXpath = getPackageXpath(packageName);

		// Try to find the package and click if visible
		WebElement packageElement = tryClickPackage(packageXpath);

		// If the package is not found, try scrolling and selecting the package
		if (packageElement == null) {
			packageElement = scrollAndSelectPackage(packageXpath);
		}

		// If the package is found after scrolling or initially, click on it
		if (packageElement != null) {
			packageElement.click();
		} else {
			// If the package is not found even after scrolling, throw an exception
			throw new NoSuchElementException("Package " + packageName + " was not found after scrolling.");
		}
	}

	// Helper method to get the package XPath
	private By getPackageXpath(String packageName) {
		return By.xpath("//h3[text()='" + packageName
				+ "']/ancestor::li//button[contains(@class, 'Subscription_packageSelect__O7evm')]");
	}

	// Helper method to try clicking on the package if visible
	private WebElement tryClickPackage(By packageXpath) {
		try {
			// Wait for the package to be visible and clickable before trying to click
			return WaitUtil.fluentWaitForElementToBeClickable(driver, packageXpath);
		} catch (Exception e) {
			// Return null if package is not found or not clickable
			return null;
		}
	}

	// Helper method to handle scrolling and selecting the package if it's not
	// initially visible
	private WebElement scrollAndSelectPackage(By packageXpath) {
		WebElement arrowButton = WaitUtil.fluentWaitForElementToBeClickable(driver, leftArrowButton); // Arrow button to
																										// slide through
																										// packages

		int maxScrollAttempts = 5; // Limit number of scroll attempts to prevent infinite loop
		int attempts = 0;

		while (isArrowButtonEnabled(arrowButton) && attempts < maxScrollAttempts) {
			// Click the arrow to slide through the packages
			arrowButton.click();

			// Wait a brief moment to allow the package list to update
			WebElement packageElement = tryClickPackage(packageXpath);
			if (packageElement != null) {
				return packageElement; // Return package element if found
			}

			attempts++; // Increment attempt counter
		}

		return null; // If the package is still not found, return null
	}

	// Helper method to check if the arrow button is enabled
	private boolean isArrowButtonEnabled(WebElement arrowButton) {
		return arrowButton.isEnabled() && arrowButton.isDisplayed(); // Check if the button is enabled and visible
	}

	public void clickConfirmButton() {
		clickElement(confirmButton);
	}

}
