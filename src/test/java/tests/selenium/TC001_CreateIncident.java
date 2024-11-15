package tests.selenium;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import lib.selenium.PreAndPost;
import pages.selenium.CreateNewIncident;
import pages.selenium.HomePage;
import pages.selenium.LoginPage;
import common.CustomLogger;

public class TC001_CreateIncident extends PreAndPost {
	private final CustomLogger logger = CustomLogger.getInstance();
	@BeforeTest
	public void setValues() {

		testCaseName = "Create Incident (Using Selenium)";
		testDescription = "Create a new Incident";
		nodes = "Incident Management";
		authors = "Hari";
		category = "UI";
		dataSheetName = "TC002";

		logger.logTestCaseStart(testCaseName);

	}

	@Test(dataProvider = "fetchData")
	public void createIncident(String filter, String user, String short_desc) {

		LoginPage loginPage = new LoginPage(driver, test);
		HomePage homePage = new HomePage(driver, test);
		CreateNewIncident createNewIncident = new CreateNewIncident(driver, test);

		logger.step(1, "Login to the Web Application");
		loginPage.loginApp();

		logger.step(2, "Click the All link in the header");
		homePage.clickAll();

		logger.step(3, "Click the Incident");
		homePage.clickIncident();

		logger.step(4, "Click the new button");
		homePage.clickNew();

		logger.step(5, "Get the Incident number ");
		createNewIncident.getIncidentNumber();

		logger.step(6, "Select the user");
		createNewIncident.selectUser(user);

		logger.step(7, "Enter short description");
		createNewIncident.typeShortDescription(short_desc);

		logger.step(8, "Click the Submit button");
		createNewIncident.clickSubmit();

		logger.step(9, "Verify the newly created incident");
		createNewIncident.verifyIncidentCreation();
	}

	@AfterMethod
	public void tearDown() {
		logger.logTestCaseEnd(testCaseName);
	}
}