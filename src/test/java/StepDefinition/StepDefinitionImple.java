package StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;

import Base.TestBase;
import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.ConfirmationPage;
import PageObjects.LandingPage;
import PageObjects.ProductCatalog;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImple extends TestBase{

	public LandingPage lp;
	public ProductCatalog pc;
	public ConfirmationPage cf;
	@Given("I landed on Ecommerse page")
	public void I_landed_n_Ecommerse_page() throws IOException {
		lp=launchApp();
		
	}
	@Given("^Given Logged in with  username (.+) and passwoord (.+)$")
	public void Logged_in_with_username_and_passwoord(String uname, String pass) {
		pc=lp.loginToApp(uname, pass);
	}
	@When("^When I add product (.+) to cart$")
	public void When_I_add_product_to_cart(String pname) {
		List<WebElement> prooduct=pc.getProductList();
		pc.addProductToCart(pname);
	}
	@And("^Check out the product (.+) and submit the order$")
	public void Check_out_the_product_and_submit_the_order(String  pname) {
		CartPage cp=pc.clickOnCart();
		
		
		
		boolean result=cp.verifyProductInCart(pname);
		AssertJUnit.assertTrue(result);
		CheckoutPage ck=cp.checkOut();
		
		ck.selectCountry("India");
		 cf=ck.submit();
	}
	
    @Then("{String} message is displayed on confirmation page.")
    public void message_is_displayed_on_confirmation_page(String String) {
		
		String success=cf.getSuccessText();

		System.out.println(success);

		AssertJUnit.assertTrue(success.equalsIgnoreCase(String));
    }
    

}

