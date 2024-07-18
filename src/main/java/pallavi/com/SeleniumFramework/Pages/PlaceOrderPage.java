package pallavi.com.SeleniumFramework.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import pallavi.com.SeleniumFramework.AbstractComponents.AbstractComponent;

public class PlaceOrderPage extends AbstractComponent
{
    WebDriver driver;
    
	public PlaceOrderPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement SelectCountry;
	
	@FindBy(xpath="//section/button[2]")
	WebElement CountryPick;
	
	@FindBy(css=".action__submit")
	WebElement Placeorder;
	
	@FindBy(css=".hero-primary")
	WebElement msg;
	
	public void selectCountry(String Country)
	{
		SelectCountry.sendKeys(Country);
		CountryPick.click();
		
	}
	
	public void ClickOnPlaceOrder()
	{
		Placeorder.click();

	}
	
	public String VerifyOrderPlacedMessage()
	{
		
		String confirmmsg=msg.getText();
		return confirmmsg;
	}

	 
     
     
}
