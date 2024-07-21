package pallavi.com.SeleniumFramework.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pallavi.com.SeleniumFramework.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	By ProductBy = By.cssSelector(".mb-3");
	By AddtoCart = By.cssSelector(".card-body button:last-of-type");
	By toastmsg =By.cssSelector("#toast-container");
	By animating = By.cssSelector(".ng-animating");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(ProductBy);
		return products;
	}
	
	public WebElement getProductByName(String ProductName)
	{
		WebElement prod=getProductList().stream().filter(s -> s.findElement(By.tagName("b")).getText().equals(ProductName)).findFirst().orElse(null);
	    return prod;
	}
	
	public void clikcingOnAddtocart(String ProductName)
	{
	     WebElement prod=getProductByName(ProductName);	
		 prod.findElement(AddtoCart).click();
		 waitForElementToAppear(toastmsg);
		 waitForElementtoDisappear(animating);
	        
	}

}
