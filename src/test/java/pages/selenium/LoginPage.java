package pages.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lib.selenium.PreAndPost;
import lib.utils.ConfigUtil;

import common.CustomLogger;

public class LoginPage extends PreAndPost {
	private Properties prop;
	private final CustomLogger logger = CustomLogger.getInstance();


	public LoginPage(RemoteWebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);

		logger.info("Initializing the LoginPage object and loading properties file");
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./src/test/resources/config.properties")));
			logger.info("Successfully loaded configuration file");
		} catch (Exception e) {
			logger.error("Failed to load configuration file: " + e.getMessage(), e);
			reportStep("Missing or unreadable configuration file", "FAIL");
		}
	}

	@FindBy(id = "user_name")
	private WebElement eleUserName;

	@FindBy(id = "user_password")
	private WebElement elePassword;

	@FindBy(how = How.ID, using = "sysverb_login")
	private WebElement eleLogin;

	@Given("Enter username as (.*)$")
	private LoginPage typeUserName(String username) {
		try {
			logger.info("Entering username: " + username);
			type(eleUserName, username);
		} catch (Exception e) {
			String locator = "ID: user_name";
			logger.error("Failed to enter username [Locator: " + locator + "] - " + e.getMessage(), e);
			reportStep("Failed to enter username [Locator: " + locator + "]", "FAIL");
		}
		return this;
	}

	@And("Enter password as (.*)$")
	private LoginPage typePassword(String password) {
		try {
			logger.info("Entering password");
			type(elePassword, password);
		} catch (Exception e) {
			String locator = "ID: user_password";
			logger.error("Failed to enter password [Locator: " + locator + "] - " + e.getMessage(), e);
			reportStep("Failed to enter password [Locator: " + locator + "]", "FAIL");
		}
		return this;
	}

	@Then("Click the Login")
	private HomePage clickLogIn() {
		try {
			logger.info("Clicking the login button");
			click(eleLogin);
			logger.info("Login button clicked successfully");
		} catch (Exception e) {
			String locator = "ID: sysverb_login";
			logger.error("Failed to click the login button [Locator: " + locator + "] - " + e.getMessage(), e);
			reportStep("Login button click failed [Locator: " + locator + "]", "FAIL");
		}
		return new HomePage(driver, test);
	}

	public HomePage loginApp() {
		String username = null;
		String password = null;
		try {
			logger.info("Fetching credentials from configuration file");
			username = ConfigUtil.getProperty("username");
			password = ConfigUtil.getProperty("password");
			logger.info("Credentials fetched successfully for the User: " + username);
		} catch (Exception e) {
			logger.error("Error fetching credentials: " + e.getMessage(), e);
			reportStep("Error fetching credentials from the configuration file", "FAIL");
		}

		try {
			logger.info("Entering Login workflow");
			return typeUserName(username)
					.typePassword(password)
					.clickLogIn();
		} catch (Exception e) {
			logger.error("Login failed: " + e.getMessage(), e);
			reportStep("Login failed. Check application logs for details", "FAIL");
			return null; // Optionally rethrow or handle appropriately
		}
	}
}