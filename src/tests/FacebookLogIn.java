package tests;

import org.testng.annotations.Test;

import common.BaseTestCase;

public class FacebookLogIn extends BaseTestCase {

	@Test
	public void ValidLogIn() {
		open("http://facebook.com");
		loginPage.LogIn("qademouser99@gmail.com", "one+two=3");
		homePage.VerifyProfileNameIsVisible();
	}

	@Test
	public void InvalidLogIn() {
		open("http://facebook.com");
		loginPage.LogIn("qademouser99@gmail.com", "one+two=31");
		invalidLoginPage.VerifyForgotPasswordLinkExists();
	}
}
