package PageObjects;

import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage {

	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		
		//super(driver);
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//table//tbody//tr//td[2]")
	List<WebElement> pNames;
	
	public boolean getProductName(String productName) {
		
		boolean match=pNames.stream().anyMatch(pname->pname.getText().equalsIgnoreCase(productName));
		
		return match;
	}

}
