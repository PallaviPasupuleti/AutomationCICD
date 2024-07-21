package pallavi.com.SeleniumFramework.testcomponents;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.BindException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pallavi.com.SeleniumFramework.Pages.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingpage;
	
	public WebDriver InitializeDriver() throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//pallavi//com//SeleniumFramework//Resources//Globalfile.properties");
		prop.load(fis);
		
		String browsername=System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser");
		//prop.getProperty("browser");
		
		if(browsername.contains("chrome"))
		{
			ChromeOptions options=new ChromeOptions();
			if(browsername.contains("headless"))
			{
				options.addArguments("headless");
			}
			 driver=new ChromeDriver(options);
			 driver.manage().window().setSize(new Dimension(1440,900));	//free size
		 }
		else if(browsername.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(browsername.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
		}
		else if(browsername.equalsIgnoreCase("safari"))
		{
			driver=new SafariDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDatatoMap(String filepath) throws IOException
	{
		//read Json to String
		
		String JsonContent=FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
		
		//String to HashMap Jackson Databind
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(JsonContent,new TypeReference<List<HashMap<String,String>>>(){});
		return data;
		
		}
	
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+ testcaseName +".png";
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchingapplication() throws IOException
	{
		driver=InitializeDriver();
		landingpage=new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}

}

