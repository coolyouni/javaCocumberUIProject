package pageClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import commonUtils.WaitUtil;

public class PaymentPage extends BasePage{

	
	private By iframeLocator = By.xpath("//div[@id='payment-element']//iframe");
	private By cardNumberLocator = By.xpath("//input[@id='Field-numberInput']");
	private By expiryDateLocator = By.xpath("//input[@id='Field-expiryInput']");
	private By securityCodeLocator = By.xpath("//input[@id='Field-cvcInput']");
	private By payNowButtonLocator = By.xpath("//button[contains(@class, 'ant-btn') and contains(@class, 'ant-btn-text')]/span[normalize-space(text())='Pay Now']");
	private By confirmationMessageLocator = By.xpath("//h1");
	private By backToLoginLocator = By.xpath("//button[@type='button']/span[text()='Back To Login']");
	private By loginFormLocator = By.xpath("//form[@id='login-form']");
	
	
	public PaymentPage(WebDriver driver) {
		super(driver);
	}

		
	public void enterValidPaymentDetails(String cardNumber,String expDate,String cvv) {
	  
	        WaitUtil.waitForIframeAndSwitch(driver, iframeLocator, Duration.ofSeconds(10));
	        
	        enterText(cardNumberLocator, cardNumber);
	        
	        enterText(expiryDateLocator, expDate);
	        
	        enterText(securityCodeLocator, cvv);
	        
	        driver.switchTo().defaultContent();
	}
	
	
	public void clickPayNowButton() {
		clickElement(payNowButtonLocator);
	}
	
	public String getPaymentConfirmationMessage() {
		return getText(confirmationMessageLocator);
	}
	
	public void clickBackToLogin() {
        clickElement(backToLoginLocator);
    }
	
	public boolean verifyRedirectToLogin() {
		return findElement(loginFormLocator).isDisplayed();
    }

}
