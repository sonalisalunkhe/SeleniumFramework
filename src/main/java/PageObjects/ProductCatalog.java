package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.AbstractComponent;

public class ProductCatalog extends AbstractComponent{
	

	WebDriver driver;
	
	public ProductCatalog(WebDriver driver) {
		
		super(driver);
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div.col-lg-4")
	List<WebElement> cards;
	
	By productsBy=By.cssSelector("div.col-lg-4");
	
	By addToCart=By.xpath("//button[text()=' Add To Cart']");
	By toastMessage=By.cssSelector("#toast-container");
	
	@FindBy(css=".ng-animating")
	WebElement animation;

	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement goToCart;
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement goToOrders;
	
	
	public List<WebElement> getProductList() {
		
		waitForELeToAppear(productsBy);
		return cards;
		
	}
	
	public WebElement getProductName(String productname) {
		WebElement product=	getProductList().stream().filter(card->card.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		return product;
	}
	
	public void addProductToCart(String productname) {
		getProductName(productname).findElement(addToCart).click();
		waitForELeToAppear(toastMessage);
		waitForEleToIvisible(animation);
	}
	
	public CartPage clickOnCart() {
		goToCart.click();
		CartPage cp=new CartPage(driver);
		return cp;
	}
	
	public OrdersPage clickOnOrder() {
		goToOrders.click();
		OrdersPage op=new OrdersPage(driver);
		return op;
	}
}
