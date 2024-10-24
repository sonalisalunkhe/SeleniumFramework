package Authentication;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.TestBase;
import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.ConfirmationPage;
import PageObjects.OrdersPage;
import PageObjects.ProductCatalog;
import Utilities.Retry;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class SubmitOrderTest extends TestBase{
	//String productname="ZARA COAT 3";

	@Test(dataProvider="getData",groups="purchase")
	public void test_CompletePurchaseFlow(HashMap<String,String> input) throws InterruptedException, IOException {
		String countryName="India";
				
		lp.goToUrl();
		ProductCatalog pc=lp.loginToApp(input.get("email"), input.get("pass"));
		
		
		pc.addProductToCart(input.get("productName"));
		Thread.sleep(3000);
		CartPage cp=pc.clickOnCart();
		
		
		
		boolean result=cp.verifyProductInCart(input.get("productName"));
		AssertJUnit.assertTrue(result);
		CheckoutPage ck=cp.checkOut();
		
		ck.selectCountry(countryName);
		ConfirmationPage cf=ck.submit();
		
		String success=cf.getSuccessText();

		System.out.println(success);

		AssertJUnit.assertTrue(success.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		//driver.close();
		
	}
	
	@Test(dependsOnMethods= {"test_CompletePurchaseFlow"},dataProvider="getData",retryAnalyzer=Retry.class)
	public void OrderHistoryTest(String email, String pass, String productname) {
		
		lp.goToUrl();
		ProductCatalog pc=lp.loginToApp(email, pass);
		OrdersPage op=pc.clickOnOrder();
		boolean pname=op.getProductName(productname);
		
		Assert.assertTrue(pname);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"/src/test/java/Data/PurchaseOrderData.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		/*
		HashMap<Object,Object> map=new HashMap<>();
		map.put("email", "sonali@mailinator.com");
		map.put("pass", "ABCabc@123");
		map.put("productName", "ZARA COAT 3");

		HashMap<Object,Object> map1=new HashMap<>();
		map1.put("email", "kadam@mailinator.com");
		map1.put("pass", "Temp@1234");
		map1.put("productName", "ZARA COAT 3");
		
		return new Object[][] {{"sonali@mailinator.com","ABCabc@123","ZARA COAT 3"},{"kadam@mailinator.com","Temp@1234","ZARA COAT 3"}};
	*/
	}

}
