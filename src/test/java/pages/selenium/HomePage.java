package pages.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import lib.selenium.PreAndPost;

public class HomePage extends PreAndPost{

	public HomePage(RemoteWebDriver driver, ExtentTest test) {	
		this.driver = driver;
		this.test = test;	
		driver.switchTo().defaultContent();
		PageFactory.initElements(driver, this);
	}		

	public HomePage searchUsingFilter(String value) {	
		type(locateElement("filter"),value);
		return this; 
	}	
	
	
	
	public HomePage clickAll() {
		clickElementInsideShadowRoot();
		return this;
	}
	
	
	@FindBy(linkText="Create New")
	WebElement eleCreateNew;	
	public CreateNewIncident clickCreateNew() {		
		click(eleCreateNew);
		return new CreateNewIncident(driver, test); 
	}	
	

	@FindBy(linkText="Open")
	WebElement eleOpen;	
	public ListIncidents clickOpen() {		
		click(eleOpen);
		return new ListIncidents(driver, test); 
	}	
	
}










