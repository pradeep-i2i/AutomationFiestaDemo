package pages.selenium;

import common.CustomLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import lib.selenium.PreAndPost;

public class HomePage extends PreAndPost {
	//	CustomLogger logger = new CustomLogger();
	private final CustomLogger logger = CustomLogger.getInstance();

	public HomePage(RemoteWebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		try {
			driver.switchTo().defaultContent();
			PageFactory.initElements(driver, this);
			logger.info("Initialized the HomePage elements and switched to default content.");
		} catch (Exception e) {
			logger.error("Error initializing HomePage or switching to default content: " + e.getMessage(), e);
		}
	}

	public HomePage searchUsingFilter(String value) {
		String filterLocator = "filter";
		try {
			logger.info("Searching using filter with value: " + value + " [Locator: " + filterLocator + "]");
			type(locateElement(filterLocator), value);
		} catch (Exception e) {
			logger.error("Failed to search using filter with value: " + value + " [Locator: " + filterLocator + "] - " + e.getMessage(), e);
		}
		return this;
	}

	public HomePage clickAll() {
		String allElementSelector = "return document.querySelector('macroponent-f51912f4c700201072b211d4d8c26010').shadowRoot."
				+ "querySelector('sn-canvas-appshell-root').querySelector('sn-canvas-appshell-layout')."
				+ "querySelector('sn-polaris-layout').shadowRoot.querySelector('sn-polaris-header').shadowRoot."
				+ "querySelector(\"div.sn-polaris-tab[aria-label='All']\")";
		try {
			logger.info("Attempting to click the 'All' link in the header [Selector: " + allElementSelector + "]");
			clickElementInsideShadowRoot(allElementSelector);
			logger.info("Successfully clicked the 'All' link.");
		} catch (Exception e) {
			logger.error("Failed to click the 'All' link [Selector: " + allElementSelector + "] - " + e.getMessage(), e);
		}
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
		try {
			logger.info("Attempting to click the 'Incident' link " + incidentElementSelector + "]");
			clickElementInsideShadowRoot(incidentElementSelector);
			logger.info("Successfully clicked the 'Incident' link");
		} catch (Exception e) {
			logger.error("Failed to click the 'Incident' link [Selector: " + incidentElementSelector + "] - " + e.getMessage(), e);
		}
		return this;
	}

	@FindBy(xpath = "//*[@id='incident_breadcrumb']//a[contains(@aria-label,'All Press enter to remove all subsequent conditions.')]")
	private WebElement eleBreadCrumbAll;

	public ListIncidents clickBreadCrumb_All() {
		String newButtonFrameElement = "return document.querySelector('macroponent-f51912f4c700201072b211d4d8c26010').shadowRoot.querySelector(\"iframe[id='gsft_main']\")";
		String breadCrumbXPath = "//*[@id='incident_breadcrumb']//a[contains(@aria-label,'All Press enter to remove all subsequent conditions.')]";
		try {
			logger.info("Attempting to click the 'All' breadcrumb link [XPath: " + breadCrumbXPath + "]");
			swicthToFrameInsideShadowRoot(newButtonFrameElement);
			click(eleBreadCrumbAll);
			logger.info("Successfully clicked the 'All' breadcrumb link.");
		} catch (Exception e) {
			logger.error("Failed to click the 'All' breadcrumb link [XPath: " + breadCrumbXPath + "] - " + e.getMessage(), e);
		}
		return new ListIncidents(driver, test);
	}

	@FindBy(linkText = "Create New")
	private WebElement eleCreateNew;

	public CreateNewIncident clickCreateNew() {
		try {
			logger.info("Attempting to click the 'Create New' link [Link Text: Create New]");
			click(eleCreateNew);
			logger.info("Successfully clicked the 'Create New' link");
		} catch (Exception e) {
			logger.error("Failed to click the 'Create New' link [Link Text: Create New] - " + e.getMessage(), e);
		}
		return new CreateNewIncident(driver, test);
	}

	@FindBy(xpath = "//button[@id='sysverb_new']")
	private WebElement eleNew;

	public CreateNewIncident clickNew() {
		String newButtonXPath = "//button[@id='sysverb_new']";
		String newButtonFrameElement = "return document.querySelector('macroponent-f51912f4c700201072b211d4d8c26010').shadowRoot.querySelector(\"iframe[id='gsft_main']\")";
		try {
			logger.info("Attempting to click the 'New' button [XPath: " + newButtonXPath + "]");
			swicthToFrameInsideShadowRoot(newButtonFrameElement);
			click(eleNew);
			logger.info("Successfully clicked the 'New' button.");
		} catch (Exception e) {
			logger.error("Failed to click the 'New' button [XPath: " + newButtonXPath + "] - " + e.getMessage(), e);
		}
		return new CreateNewIncident(driver, test);
	}

	@FindBy(linkText = "Open")
	private WebElement eleOpen;

	public ListIncidents clickOpen() {
		try {
			logger.info("Attempting to click the 'Open' link [Link Text: Open]");
			click(eleOpen);
			logger.info("Successfully clicked the 'Open' link");
		} catch (Exception e) {
			logger.error("Failed to click the 'Open' link [Link Text: Open] - " + e.getMessage(), e);
		}
		return new ListIncidents(driver, test);
	}
}