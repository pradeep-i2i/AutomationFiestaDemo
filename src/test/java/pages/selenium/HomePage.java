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
		String allElementSelector = "return document.querySelector('macroponent-f51912f4c700201072b211d4d8c26010').shadowRoot."
			    + "querySelector('sn-canvas-appshell-root').querySelector('sn-canvas-appshell-layout')."
			    + "querySelector('sn-polaris-layout').shadowRoot.querySelector('sn-polaris-header').shadowRoot."
			    + "querySelector(\"div.sn-polaris-tab[aria-label='All']\")";
		clickElementInsideShadowRoot(allElementSelector);
		return this;
	}
	
	public HomePage clickIncident() {
		String incidentElementSelector = "return document.querySelector('macroponent-f51912f4c700201072b211d4d8c26010').shadowRoot\r\n"
			    + "    .querySelector('sn-canvas-appshell-root')\r\n"
			    + "    .querySelector('sn-canvas-appshell-layout')\r\n"
			    + "    .querySelector('sn-polaris-layout')\r\n"
			    + "    .shadowRoot.querySelector('sn-polaris-header').shadowRoot\r\n"
			    + "    .querySelector('sn-polaris-menu').shadowRoot\r\n"
			    + "    .querySelector('sn-collapsible-list').shadowRoot\r\n"
			    + "    .querySelector(\"a[aria-label='Incidents']\")";
		clickElementInsideShadowRoot(incidentElementSelector);
		return this;
	}
	
	@FindBy(xpath="//*[@id='incident_breadcrumb']//a[contains(@aria-label,'All Press enter to remove all subsequent conditions.')]") 
	private WebElement eleBreadCrumbAll;
	public ListIncidents clickBreadCrumb_All() {
		String newButtonFrameElement = "return document.querySelector('macroponent-f51912f4c700201072b211d4d8c26010').shadowRoot.querySelector(\"iframe[id='gsft_main']\")";
		swicthToFrameInsideShadowRoot(newButtonFrameElement);
		
		click(eleBreadCrumbAll);
		return new ListIncidents(driver,test);
		
	}
	
	

	
	@FindBy(linkText="Create New")
	WebElement eleCreateNew;	
	public CreateNewIncident clickCreateNew() {		
		click(eleCreateNew);
		return new CreateNewIncident(driver, test); 
	}	
	
	@FindBy(xpath="//button[@id='sysverb_new']")
	WebElement eleNew;	
	public CreateNewIncident clickNew() {	
		String newButtonFrameElement = "return document.querySelector('macroponent-f51912f4c700201072b211d4d8c26010').shadowRoot.querySelector(\"iframe[id='gsft_main']\")";
		swicthToFrameInsideShadowRoot(newButtonFrameElement);
		click(eleNew);
		return new CreateNewIncident(driver, test); 
	}	
	
	
	
	

	@FindBy(linkText="Open")
	WebElement eleOpen;	
	public ListIncidents clickOpen() {		
		click(eleOpen);
		return new ListIncidents(driver, test); 
	}	
	
}










