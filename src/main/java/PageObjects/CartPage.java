package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.AbstractComponent;

public class CartPage extends AbstractComponent{
WebDriver driver;
	
	public CartPage(WebDriver driver) {
		
		super(driver);
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='cart']//h3")
	List<WebElement> cartText;
	
	@FindBy(xpath="//li[@class='totalRow']//button")
	WebElement checkout;
	
	public boolean verifyProductInCart(String productname) {
		boolean result=cartText.stream().anyMatch(text->text.getText().equals(productname));
		return result;
	}
	
	public CheckoutPage checkOut() {
		checkout.click();
		
		CheckoutPage ck=new CheckoutPage(driver);
		return ck;
	}
}
