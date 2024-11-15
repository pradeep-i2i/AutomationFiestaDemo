package pages.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import lib.selenium.PreAndPost;

import common.CustomLogger;

public class ListIncidents extends PreAndPost {
	private final CustomLogger logger = CustomLogger.getInstance();
	public ListIncidents(RemoteWebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
		logger.info("Initializing ListIncidents object");
	}

	@FindBy(xpath = "(//input[@class='form-control'])[1]")
	private WebElement eleSearch;

	@FindBy(xpath = "(//a[@class='linked formlink'])[1]")
	private WebElement eleSearchResult;

	public ListIncidents typeAndEnterSearch(String data) {
		try {
			String locator = "(//input[@class='form-control'])[1]";
			logger.info("Typing and entering search data: " + data + " [Locator: " + locator + "]");
			typeAndEnter(eleSearch, data);
			logger.info("Search data entered successfully");
		} catch (Exception e) {
			String locator = "(//input[@class='form-control'])[1]";
			logger.error("Failed to enter search data [Locator: " + locator + "] - " + e.getMessage(), e);
			reportStep("Failed to enter search data [Locator: " + locator + "]", "FAIL");
		}
		return this;
	}

	public ListIncidents verifyResult(String data) {
		try {
			String locator = "(//a[@class='linked formlink'])[1]";
			logger.info("Verifying search result matches: " + data + " [Locator: " + locator + "]");
			verifyExactText(eleSearchResult, data);
			logger.info("Search result verified successfully");
		} catch (Exception e) {
			String locator = "(//a[@class='linked formlink'])[1]";
			logger.error("Failed to verify search result [Locator: " + locator + "] - " + e.getMessage(), e);
			reportStep("Failed to verify search result [Locator: " + locator + "]", "FAIL");
		}
		return this;
	}
}