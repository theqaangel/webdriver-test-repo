package testpages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import common.Reporter;

public class SignUpPage {
	private WebDriver driver;
	private Reporter reporter;

	public SignUpPage(WebDriver driver, Reporter reporter) {
		this.driver = driver;
		this.reporter = reporter;
	}

	// Elements
	private WebElement getFirstNameTextBox() {
		return this.getWebElement(By.name("firstname"));
	}

	private WebElement getLastNameTextBox() {
		return this.getWebElement(By.name("lastname"));
	}

	public WebElement getEmailTextBox() {
		return this.getWebElement(By.name("reg_email__"));
	}

	private WebElement getConfirmEmailTextBox() {
		return this.getWebElement(By.name("reg_email_confirmation__"));
	}

	private WebElement getPasswordTextBox() {
		return this.getWebElement(By.name("reg_passwd__"));
	}

	private Select getBirhdayMonthSelectBox() {
		return getSelectBox(By.name("birthday_month"));
	}

	private Select getBirhdayDaySelectBox() {
		return getSelectBox(By.name("birthday_day"));
	}

	private Select getBirhdayYearSelectBox() {
		return getSelectBox(By.name("birthday_year"));
	}

	private WebElement getMaleRadioButton() {
		return getWebElement(By.xpath("//input[@name='sex' and @value='2']"));
	}

	private WebElement getFemaleRadioButton() {
		return getWebElement(By.xpath("//input[@name='sex' and @value='1']"));
	}

	private WebElement getSignUpButton() {
		return getWebElement(By.name("websubmit"));
	}

	private WebElement getPasswordRequiredPopup() {
		return getWebElement(By
				.xpath("//*[contains(text(), 'Enter a combination of at least six numbers')]"));
	}

	// Actions
	public void SelectBirthdate(String year, String month, String day) {
		this.getBirhdayYearSelectBox().selectByVisibleText(year);
		this.reporter
				.info("Select " + year + "year in Birthday year selectbox");
		this.getBirhdayMonthSelectBox().selectByVisibleText(month);
		this.reporter.info("Select " + month
				+ "month in Birthday month selectbox");
		this.getBirhdayDaySelectBox().selectByVisibleText(day);
		this.reporter.info("Select " + day + "day in Birthday day selectbox");
	}

	public void SignUp(String firstName, String lastName, String email,
			String confirmEmail, String password, String birthdayYear,
			String birthdayMonth, String birthdayDay, boolean isMale) {

		this.getFirstNameTextBox().sendKeys(firstName);
		this.reporter.info("Eneter " + firstName + " in firstname textbox");

		this.getLastNameTextBox().sendKeys(lastName);
		this.reporter.info("Eneter " + lastName + " in lastname textbox");

		this.getEmailTextBox().sendKeys(email);
		this.reporter.info("Eneter " + email + " in email textbox");

		this.getConfirmEmailTextBox().sendKeys(confirmEmail);
		this.reporter.info("Eneter " + confirmEmail
				+ " in confirm email textbox");

		this.getPasswordTextBox().sendKeys(password);
		this.reporter.info("Eneter " + password + " in password textbox");

		this.SelectBirthdate(birthdayYear, birthdayMonth, birthdayDay);

		if (isMale) {
			this.getMaleRadioButton().click();
			this.reporter.info("Choose Male");
		} else {
			this.getFemaleRadioButton().click();
			this.reporter.info("Choose Female");
		}

		this.getSignUpButton().click();
		this.reporter.info("Click SignUp button");
	}

	// Verification
	public void VerifyPasswordRequiredPopupIsVisible() {
		try {
			WebElement passwordPopup = this.getPasswordRequiredPopup();
			if (!passwordPopup.isDisplayed()) {
				this.reporter.error("Password required popup is NOT displayed");
			}
			Assert.assertTrue(passwordPopup.isDisplayed());
			this.reporter.pass("Password required popup is displayed");
		} catch (NoSuchElementException ex) {
			this.reporter.error("Password required popup is NOT found");
		} catch (Exception ex) {
			this.reporter.error("Can't determine password required popup state");
		}

	}

	private Select getSelectBox(By selector) {
		WebElement selectBoxElement = getWebElement(selector);
		return new Select(selectBoxElement);
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
