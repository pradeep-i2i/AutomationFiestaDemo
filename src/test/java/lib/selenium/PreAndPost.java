package lib.selenium;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import lib.factory.BrowserFactory;
import lib.factory.BrowserType;
import lib.utils.ConfigUtil;
import lib.utils.DataInputProvider;
import lib.utils.HTMLReporter;

public class PreAndPost extends WebDriverServiceImpl{
	
	public String dataSheetName;	
	
	
	@BeforeSuite
	public void beforeSuite() {
		startReport();
	}
	
	@BeforeClass
	public void beforeClass() {
		startTestCase(testCaseName, testDescription);		
	}
	
	 @Parameters({"browser", "env"})
	@BeforeMethod
	public void beforeMethod(String browser,String environment) throws FileNotFoundException, IOException {
		
		System.setProperty("env", environment); // Set the system property for environment
        ConfigUtil.loadEnvironmentProperties();
        String URL = ConfigUtil.getProperty("url");

		//for reports		
		startTestModule(nodes);
		test.assignAuthor(authors);
		test.assignCategory(category);
		HTMLReporter.svcTest = test;	
		
		if(browser.equalsIgnoreCase("chrome"))
			driver = BrowserFactory.createBrowser(BrowserType.CHROME,URL);
		else if(browser.equalsIgnoreCase("edge"))
			driver = BrowserFactory.createBrowser(BrowserType.EDGE,URL);
		
		/*System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		 driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);*/
	
	}

	@AfterMethod
	public void afterMethod() {
		closeActiveBrowser();
	}

	@AfterSuite
	public void afterSuite() {
		endResult();
	}

	@DataProvider(name="fetchData", indices= {0})
	public  Object[][] getData(){
		return DataInputProvider.getSheet(dataSheetName);		
	}	

	
	
}
