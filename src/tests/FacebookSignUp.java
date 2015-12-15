package tests;

import org.testng.annotations.Test;

import common.BaseTestCase;

public class FacebookSignUp extends BaseTestCase {

	@Test
	public void SignUpNoPassword() {
		open("http://facebook.com");
		signUpPage.SignUp("Test", "User", "test@test.cc", "test@test.cc", "",
				"2005", "Mar", "25", true);
		signUpPage.VerifyPasswordRequiredPopupIsVisible();
	}
}
