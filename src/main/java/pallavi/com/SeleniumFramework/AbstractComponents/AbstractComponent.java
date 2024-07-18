package pallavi.com.SeleniumFramework.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pallavi.com.SeleniumFramework.Pages.MyCartPage;
import pallavi.com.SeleniumFramework.Pages.OrdersPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	@FindBy(css="[routerlink*='cart']")
    WebElement cart;
	
	@FindBy(css="[routerlink*='/dashboard/myorders']")
    WebElement OrderPage;
	
	
	public AbstractComponent(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void waitForElementToAppear(By Findby)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(Findby));
	}
	
	public void waitForWebElementToAppear(WebElement Findby)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(Findby));
	}
	
	public void waitForElementtoDisappear(By Findby)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(Findby));
	}
	
	public MyCartPage ClickingOnCart()
    {
    	cart.click();
    	return new MyCartPage(driver);
		
    }
	
	public OrdersPage ClickingOnOrdersPage()
    {
    	OrderPage.click();
    	return new OrdersPage(driver);
    }	

}
