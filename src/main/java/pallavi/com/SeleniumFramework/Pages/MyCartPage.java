package pallavi.com.SeleniumFramework.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import pallavi.com.SeleniumFramework.AbstractComponents.AbstractComponent;

public class MyCartPage extends AbstractComponent {

	WebDriver driver;
	
    public MyCartPage(WebDriver driver)
    {
    	super(driver);
    	this.driver=driver;
    	PageFactory.initElements(driver, this);
    }
    
    
    @FindBy(css=".cartSection h3")
    List<WebElement> cartdetails;
    
    @FindBy(css=".totalRow button")
    WebElement checkout;
    
    public boolean VerifyCartDetails(String ProductName)
    {
        Boolean match=cartdetails.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(ProductName));
        return match;
    }
    
    public PlaceOrderPage ClickOnCheckout()
    {
    	
    	checkout.click();
    	return new PlaceOrderPage(driver);
    }
}
