package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {
WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		
		//super(driver);
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
@FindBy(css="h1.hero-primary")
WebElement successMessage;

public String getSuccessText() {
	String success=successMessage.getText();
	return success;
}
}
