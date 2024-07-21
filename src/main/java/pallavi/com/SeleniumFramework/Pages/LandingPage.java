package pallavi.com.SeleniumFramework.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pallavi.com.SeleniumFramework.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
  
	//WebElement useremail=driver.findElement(By.id("userEmail"));
	@FindBy(id="userEmail")
	WebElement useremail;
	
	@FindBy(id="userPassword")
	WebElement Password;
	
	@FindBy(id="login")
	WebElement loginbutton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errormessage;
	
	public ProductCatalogue LoginAction(String email,String PasswordLogin)
	{
		useremail.sendKeys(email);
	    Password.sendKeys(PasswordLogin);
	    loginbutton.click();
	    ProductCatalogue pc=new ProductCatalogue(driver);
	    return pc;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String errorMessage()
	{
		waitForWebElementToAppear(errormessage);
		return errormessage.getText();
		}
}
