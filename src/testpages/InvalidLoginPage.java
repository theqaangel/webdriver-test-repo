package testpages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Reporter;

public class InvalidLoginPage {
	private WebDriver driver;
	private Reporter reporter;

	public InvalidLoginPage(WebDriver driver, Reporter reporter) {
		this.driver = driver;
		this.reporter = reporter;
	}
	
	//Elements
	public WebElement getForgotPasswordLink(){
		return getWebElement(By.linkText("Forgot password?"));
	}
	
	//Verification
	public void VerifyForgotPasswordLinkExists(){
		try{
			WebElement link = this.getForgotPasswordLink();
			if(!link.isDisplayed()){
				this.reporter.error("Forgot password link DOESN'T exists");
			}
			Assert.assertTrue(link.isDisplayed());
			this.reporter.pass("Forgot password link exists");
		}catch(NoSuchElementException ex){
			this.reporter.error("Forgot Password Link doesn't exist");
		}catch (Exception e) {
			this.reporter.error("Verification of forgot password link failed!");
		}
	}
	
	private WebElement getWebElement(By selector) {
		WebElement webelement = null;
		if (this.driver != null) {
			webelement = this.driver.findElement(selector);
		} else {
			this.reporter.error("Driver is not initialized!");
		}
		return webelement;
	}
}
