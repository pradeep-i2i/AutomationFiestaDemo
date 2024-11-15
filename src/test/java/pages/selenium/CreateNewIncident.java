package pages.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import lib.selenium.PreAndPost;

import common.CustomLogger;

public class CreateNewIncident extends PreAndPost {
	private final CustomLogger logger = CustomLogger.getInstance();
	public CreateNewIncident(RemoteWebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
		logger.info("Initializing CreateNewIncident object");
	}

	@FindBy(id = "incident.number")
	private WebElement eleIncidentNumber;

	@FindBy(id = "sys_display.incident.caller_id")
	private WebElement eleCallerId;

	@FindBy(how = How.ID, using = "incident.short_description")
	private WebElement eleShortDescription;

	public CreateNewIncident getIncidentNumber() {
		try {
			pause(3000);
			incidentNumber = getAttribute(eleIncidentNumber, "value");
			logger.info("Retrieved Incident Number: " + incidentNumber);
		} catch (Exception e) {
			String locator = "incident.number";
			logger.error("Failed to retrieve Incident Number [Locator: " + locator + "] - " + e.getMessage(), e);
			reportStep("Failed to retrieve Incident Number [Locator: " + locator + "]", "FAIL");
		}
		return this;
	}

	public CreateNewIncident selectUser(String data) {
		try {
			logger.info("Selecting user: " + data);
			typeAndChoose(eleCallerId, data);
		} catch (Exception e) {
			String locator = "sys_display.incident.caller_id";
			logger.error("Failed to select user [Locator: " + locator + "] - " + e.getMessage(), e);
			reportStep("Failed to select user [Locator: " + locator + "]", "FAIL");
		}
		return this;
	}

	public CreateNewIncident typeShortDescription(String data) {
		try {
			logger.info("Typing short description: " + data);
			type(eleShortDescription, data);
		} catch (Exception e) {
			String locator = "incident.short_description";
			logger.error("Failed to type short description [Locator: " + locator + "] - " + e.getMessage(), e);
			reportStep("Failed to type short description [Locator: " + locator + "]", "FAIL");
		}
		return this;
	}

	public CreateNewIncident clickSubmit() {
		try {
			String locator = "sysverb_insert";
			logger.info("Clicking Submit button [Locator: " + locator + "]");
			WebElement eleSubmit = locateElement(locator);
			click(eleSubmit);
			logger.info("Submit button clicked successfully");
		} catch (Exception e) {
			String locator = "sysverb_insert";
			logger.error("Failed to click Submit button [Locator: " + locator + "] - " + e.getMessage(), e);
			reportStep("Failed to click Submit button [Locator: " + locator + "]", "FAIL");
		}
		return this;
	}

	public CreateNewIncident verifyIncidentCreation() {
		try {
			String locator = "incident_breadcrumb";
			logger.info("Verifying Incident creation [Locator: " + locator + "]");
			WebElement ele = locateElement("id","incident_breadcrumb");
			verifyDisplayed(ele);
			logger.info("Incident creation verified successfully");
		} catch (Exception e) {
			String locator = "incident_breadcrumb";
			logger.error("Failed to verify Incident creation [Locator: " + locator + "] - " + e.getMessage(), e);
			reportStep("Failed to verify Incident creation [Locator: " + locator + "]", "FAIL");
		}
		return this;
	}
}