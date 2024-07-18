package pallavi.com.SeleniumFramework.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import pallavi.com.SeleniumFramework.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {

	WebDriver driver;
	
    public OrdersPage(WebDriver driver)
    {
    	super(driver);
    	this.driver=driver;
    	PageFactory.initElements(driver, this);
    }
    
    
    @FindBy(css="tr td:nth-child(3)")
    List<WebElement> OrderDetails;
    
    
    public boolean VerifyOrderDetails(String ProductName)
    {
        Boolean match=OrderDetails.stream().anyMatch(order->order.getText().equalsIgnoreCase(ProductName));
        return match;
    }
    
}
