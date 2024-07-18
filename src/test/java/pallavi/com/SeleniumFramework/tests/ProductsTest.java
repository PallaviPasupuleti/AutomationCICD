package pallavi.com.SeleniumFramework.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pallavi.com.SeleniumFramework.Pages.LandingPage;
import pallavi.com.SeleniumFramework.Pages.MyCartPage;
import pallavi.com.SeleniumFramework.Pages.OrdersPage;
import pallavi.com.SeleniumFramework.Pages.PlaceOrderPage;
import pallavi.com.SeleniumFramework.Pages.ProductCatalogue;
import pallavi.com.SeleniumFramework.testcomponents.BaseTest;

public class ProductsTest extends BaseTest {
	
	String ProductName="ZARA COAT 3";
	String Country="ind";

	@Test(dataProvider="getData",groups= {"Purchase"})
	public void SubmitOrder(HashMap<String,String> input) throws IOException {
		// TODO Auto-generated method stub
		ProductCatalogue pc=landingpage.LoginAction(input.get("username"),input.get("Password"));
		
		List<WebElement>products=pc.getProductList();
	    pc.clikcingOnAddtocart(input.get("ProductName"));
	    MyCartPage mycartpage=pc.ClickingOnCart();
     
        Boolean match=mycartpage.VerifyCartDetails(input.get("ProductName"));
        Assert.assertTrue(match);
        PlaceOrderPage placeorderpage=mycartpage.ClickOnCheckout();
        
        placeorderpage.selectCountry(Country);
        placeorderpage.ClickOnPlaceOrder();
        String confirmmsg=placeorderpage.VerifyOrderPlacedMessage(); 
        Assert.assertTrue(confirmmsg.equalsIgnoreCase("Thankyou for the order."));
        }
	
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void OrdersPageVerification()
	{
		ProductCatalogue pc=landingpage.LoginAction("pallavihanish@gmail.com","Pallavi@123");
		OrdersPage op=pc.ClickingOnOrdersPage();
		Assert.assertTrue(op.VerifyOrderDetails(ProductName));
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String, String>> data=getJsonDatatoMap(System.getProperty("user.dir")+"//src//test//java//pallavi//com//SeleniumFramework//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		
	}
	
	
	
	/*@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"pallavihanish@gmail.com","Pallavi@123","ZARA COAT 3"},{"hanish@gmail.com","Hanish@1234","ADIDAS ORIGINAL"}};
	}*/
	
	/*@DataProvider
	public Object[][] getData()
	{
	
		 HashMap<String,String> map=new HashMap<String,String>(); 
		 map.put("username","pallavihanish@gmail.com"); 
		 map.put("Password", "Pallavi@123");
		 map.put("ProductName", "ZARA COAT 3");
		  
		 HashMap<String,String> map1=new HashMap<String,String>();
		 map1.put("username", "hanish@gmail.com"); 
		 map1.put("Password", "Hanish@1234"); 
		 map1.put("ProductName","ADIDAS ORIGINAL");
		 
		return new Object[][] {{map},{map1}};
	}*/
	
	
	
	

}
