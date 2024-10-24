package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		
		super(driver);
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement Country;
	
	@FindBy(xpath="//button[@class='ta-item list-group-item ng-star-inserted'][2]")
	WebElement SelectCountry;
	
	@FindBy(css="a.btnn.action__submit.ng-star-inserted")
	WebElement Submit;
	
	public  void  selectCountry(String countryName) {
		Actions a=new Actions(driver);
		a.sendKeys(Country, countryName).build().perform();
		
		SelectCountry.click();
		
	}
	public ConfirmationPage submit() {
		Submit.click();
		ConfirmationPage cf=new ConfirmationPage(driver);
		return cf;
	}
	
}
