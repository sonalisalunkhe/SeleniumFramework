package Utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		
	}

	public void waitForELeToAppear(By FindBy) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(FindBy));

		
		
	}
	public void waitForWebELeToAppear(WebElement FindBy) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(FindBy));

		
		
	}
	public void waitForEleToIvisible(WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));

		wait.until(ExpectedConditions.invisibilityOf(element));

	}

}
