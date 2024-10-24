package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input#userEmail")
	WebElement userEmail;
	@FindBy(css="input#userPassword")
	WebElement userPassword;
	@FindBy(css="input#login")
	WebElement login;
	@FindBy(xpath="//div[@id='toast-container']//div//div")
	WebElement loginError;

	public ProductCatalog loginToApp(String email, String pass) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(pass);
		login.click();
		ProductCatalog pc=new ProductCatalog(driver);
		return pc;
	}
	public String getLoginError() {
		waitForWebELeToAppear(loginError);
		String error=loginError.getText();	
		return error;
	}
	
	public void goToUrl() {
		driver.get("https://rahulshettyacademy.com/client");

	}
}
