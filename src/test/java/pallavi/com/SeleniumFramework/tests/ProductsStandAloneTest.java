package pallavi.com.SeleniumFramework.tests;

import static org.testng.Assert.assertTrue;

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

public class ProductsStandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
		String ProductName="ZARA COAT 3";
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("userEmail")).sendKeys("pallavihanish@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Pallavi@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		/*Writting for each loop for clicking on ZARA COAT 3 ADD TO CART button
		for(WebElement product:products)
		{
			String productname=product.findElement(By.tagName("b")).getText();
			if(productname.equalsIgnoreCase("ZARA COAT 3"))
			{
				product.findElement(By.cssSelector(".btn.w-10.rounded")).click();
			}
		}*/
		
	  //using streams for clicking on ZARA COAT 3 ADD TO CART button
		WebElement prod=products.stream().filter(s -> s.findElement(By.tagName("b")).getText().equals(ProductName)).findFirst().orElse(null);
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        
        List<WebElement> cartdetails=driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match=cartdetails.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(ProductName));
        Assert.assertTrue(match);
        
        driver.findElement(By.cssSelector(".totalRow button")).click();
        driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("ind");
        driver.findElement(By.xpath("//section/button[2]")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();
        String confirmmsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmmsg.equalsIgnoreCase("Thankyou for the order."));
        driver.close();
        }
	

}
