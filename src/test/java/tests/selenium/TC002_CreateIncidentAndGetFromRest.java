package tests.selenium;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;
import lib.selenium.PreAndPost;
import lib.utils.HTMLReporter;
import pages.selenium.LoginPage;

public class TC002_CreateIncidentAndGetFromRest extends PreAndPost{

	@BeforeTest
	public void setValues() {

		testCaseName = "Create Incident and Verify";
		testDescription = "Create Incident (Using Selenium) and Verify using REST";
		nodes = "Incident Management";
		authors = "Hari";
		category = "UI & API";
		dataSheetName = "TC002";

	}

	@Test(dataProvider = "fetchData")
	public void createIncident(String filter, String user, String short_desc) {
		
		
		
		new LoginPage(driver,test)
		.loginApp()
		.clickAll()
		.clickIncident()
		.clickNew()
		.getIncidentNumber()
		.selectUser(user)
		.typeShortDescription(short_desc)
		.clickSubmit()
		.verifyIncidentCreation();
		
		
		// Verify Using REST Assured		
		Response response = RESTAssuredBase.get("table/incident?number="+incidentNumber);
		RESTAssuredBase.verifyResponseCode(response, 200);
		RESTAssuredBase.verifyContentsWithKey(response, "result.short_description",short_desc);
	
	
	}


}





