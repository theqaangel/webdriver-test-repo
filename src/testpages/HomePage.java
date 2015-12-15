package testpages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import common.Reporter;

public class HomePage {
	private WebDriver driver;
	private Reporter reporter;

	public HomePage(WebDriver driver, Reporter reporter) {
		this.driver = driver;
		this.reporter = reporter;
	}

	// Element
	public WebElement getLogedInUserName() {
		return getWebElement(By.className("_2dpb"));
	}

	// Verify
	public void VerifyLogedInUserName(String expectedUserName) {
		try {
			String actualUserNameText = this.getLogedInUserName().getText();
			Assert.assertEquals(actualUserNameText, expectedUserName);
			this.reporter.pass("User profile name is " + expectedUserName + " as expected");
		} catch (NoSuchElementException ex) {
			this.reporter.error("Profile name element not found!");
		} catch (Exception ex) {
			this.reporter.error("Can't verify logged in username");
		}
	}
	
	public void VerifyProfileNameIsVisible(){
		try {
			WebElement userName = this.getLogedInUserName();
			if(!userName.isDisplayed()){
				this.reporter.error("Profile name label is NOT displayed");
			}
			Assert.assertTrue(userName.isDisplayed());
			this.reporter.pass("Profile name label is displayed");
		} catch (NoSuchElementException ex) {
			this.reporter.error("Profile name element not found!");
		} catch (Exception ex) {
			this.reporter.error("Can't verify logged in username");
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
