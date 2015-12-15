package testpages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Reporter;

public class LogInPage {
	private WebDriver driver;
	private Reporter reporter;
	
	public LogInPage(WebDriver driver, Reporter reporter) {
		this.driver = driver;
		this.reporter = reporter;
	}
	
	private WebElement getEmailTextBox(){
		return getWebElement(By.id("email"));
	}
	
	private WebElement getPasswordTextBox(){
		return getWebElement(By.id("pass"));
	}
	
	private WebElement getLogInButton(){
		return getWebElement(By.id("loginbutton"));
	}
	
	//Action
	public void LogIn(String email, String password){
		try{
			this.getEmailTextBox().sendKeys(email);
			this.reporter.info("Eneter " + email + " in email textbox");
			this.getPasswordTextBox().sendKeys(password);
			this.reporter.info("Eneter password in password textbox");
			this.getLogInButton().click();
			this.reporter.info("Click login button");
		}catch(NoSuchElementException ex){
			this.reporter.error(ex.getMessage());
		}catch(Exception ex){
			this.reporter.error(ex.getMessage());
		}

	}
	
	private WebElement getWebElement(By selector){
		WebElement webelement = null;
		if(this.driver != null){
			webelement = this.driver.findElement(selector);
		}else {
			this.reporter.error("Driver is not initialized!");
		}
		return webelement;
	}

}
