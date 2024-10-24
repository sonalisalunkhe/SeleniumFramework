package Authentication;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.TestBase;

public class Login extends TestBase {

	@Test(groups="errorHandling")
	public void test_InvalidLogin() {
		lp.loginToApp("sonalie@mailinator.com", "Aabc@123");
		String error = lp.getLoginError();
		System.out.println(error);

		AssertJUnit.assertTrue(error.equalsIgnoreCase("Incorrect email or password."));

	}

}
