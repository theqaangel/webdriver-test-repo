package common;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import testpages.HomePage;
import testpages.InvalidLoginPage;
import testpages.LogInPage;
import testpages.SignUpPage;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseTestCase {
	private WebDriver driver;
	private Reporter reporter;
	private ExtentReports extent;
	private static long timestamp = new Date().getTime();

	// Test Pages
	protected LogInPage loginPage;
	protected HomePage homePage;
	protected InvalidLoginPage invalidLoginPage;
	protected SignUpPage signUpPage;

	private void initTestPages() {
		this.loginPage = new LogInPage(this.driver, this.reporter);
		this.homePage = new HomePage(this.driver, this.reporter);
		this.invalidLoginPage = new InvalidLoginPage(this.driver, this.reporter);
		this.signUpPage = new SignUpPage(this.driver, this.reporter);
	}

	@BeforeClass
	public void beforeClass() {
		String reportPath = getReportFilePath();
		this.extent = new ExtentReports(reportPath, false);
		this.reporter = new Reporter();
	}
	
	private String getReportFilePath(){
		String currentFolder = Paths.get("").toAbsolutePath().toString();
		String reportPath = currentFolder + "\\report-" + timestamp + ".html";
		File reportFile = new File(reportPath);
		return reportPath;
	}

	@BeforeMethod
	public void beforeMethod(Method testCase ) {
		System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		initTestPages();
		ExtentTest currentTest = this.extent.startTest(testCase.getName());
		this.reporter.setTest(currentTest);
	}

	@AfterMethod
	public void afterMethod() {
		ExtentTest currentTest =this.reporter.getTest();
        extent.endTest(currentTest);
        extent.flush();
		if (this.driver != null) {
			this.driver.quit();
		} else {
			System.out.println("Driver is not initialized");
		}
	}

	protected void open(String url) {
		if (this.driver != null) {
			this.driver.get(url);
		} else {
			System.out.println("Driver is not initialized");
		}
	}

}
