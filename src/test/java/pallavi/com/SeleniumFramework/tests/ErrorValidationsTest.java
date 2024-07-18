package pallavi.com.SeleniumFramework.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pallavi.com.SeleniumFramework.Pages.LandingPage;
import pallavi.com.SeleniumFramework.Pages.MyCartPage;
import pallavi.com.SeleniumFramework.Pages.PlaceOrderPage;
import pallavi.com.SeleniumFramework.Pages.ProductCatalogue;
import pallavi.com.SeleniumFramework.testcomponents.BaseTest;
import pallavi.com.SeleniumFramework.testcomponents.Retry;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginPageErrorValidation() throws IOException {
		// TODO Auto-generated method stub
		ProductCatalogue pc=landingpage.LoginAction("pallavihanish@gmail.com","Pallai@123");
		Assert.assertEquals("Incorrect email or password.", landingpage.errorMessage());
	}
	
	@Test
	public void ProductNameErrorValidation() throws IOException {
		// TODO Auto-generated method stub
		String ProductName="ZARA COAT 3";
		ProductCatalogue pc=landingpage.LoginAction("pallavihanish@gmail.com","Pallavi@123");
		
		List<WebElement>products=pc.getProductList();
	    pc.clikcingOnAddtocart(ProductName);
	    MyCartPage mycartpage=pc.ClickingOnCart();
     
        Boolean match=mycartpage.VerifyCartDetails("ZARA COAT 33");
        Assert.assertFalse(match);
	}

}
