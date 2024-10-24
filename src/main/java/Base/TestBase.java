package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PageObjects.LandingPage;

public class TestBase {
	
	public WebDriver driver;
	public LandingPage lp;
	public WebDriver initializeDriver() throws IOException {
		
		
		
		
		File file=new File(System.getProperty("user.dir")+"//src//main//java//Resources//Config.properties");
		FileInputStream fis=new FileInputStream(file);
		
		Properties pro=new Properties();
		
		pro.load(fis);
		
		String browserName=pro.getProperty("browser");
		
		System.out.println(browserName);
		if(browserName.equalsIgnoreCase("chrome")) {
			 driver=new ChromeDriver();

		}else if(browserName.equalsIgnoreCase("firfox")) {
			 driver=new FirefoxDriver();

		}else if(browserName.equalsIgnoreCase("edge")) {
			 driver=new EdgeDriver();

		}
		
	
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
		
		
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String path) throws IOException {
		//read json to string
		String jsonContent=FileUtils.readFileToString(new File(path),StandardCharsets.UTF_8);
	//String to hashmap
		ObjectMapper mapper=new ObjectMapper();
	List<HashMap<String,String>>	data=mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){});
	
	return data;
	}
	public String getScreenshoots(String testcaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File scr=ts.getScreenshotAs(OutputType.FILE);
		File dest=new File(System.getProperty("user.dir")+"/src/test/java/Screenshots/"+testcaseName+"file.jpg");
		FileUtils.copyFile(scr, dest);
		return System.getProperty("user.dir")+"/src/test/java/Screenshots/"+testcaseName+"file.jpg";
	}
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApp() throws IOException {
		
		driver=initializeDriver();
		lp=new LandingPage(driver);
		lp.goToUrl();
		
		return lp;
		
	}
	@AfterMethod(alwaysRun=true)
	public void  tearDown() {
		driver.close();
	}

}
