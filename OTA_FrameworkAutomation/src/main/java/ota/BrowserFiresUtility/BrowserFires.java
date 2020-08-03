package ota.BrowserFiresUtility;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import ota.FileOperations.FileFunctions;

public class BrowserFires
{
public static WebDriver driver;
public static WebDriver Chrome_driver;
public static WebDriver FireFox_driver;

ExtentReports extent;
ExtentTest logger;

@BeforeMethod
public void extenReportSetup()
{
    ExtentHtmlReporter reporter=new ExtentHtmlReporter("./Reports/Amazon.html");
    extent = new ExtentReports();
    extent.attachReporter(reporter);
    logger=extent.createTest("Item_Select");

	
}


	@BeforeTest
	@Parameters({"BrowserName","weburl"})
	public void setDriver(String browsername,String weburl) throws Exception
	{
		String websiteurl=FileFunctions.getDataFromConfigFile(weburl);

		 if(browsername.equalsIgnoreCase("chrome"))
			{
				System.out.println("You are in Chrome browser");
			     System.setProperty("webdriver.chrome.silentOutput", "true");
				System.setProperty("webdriver.chrome.driver","C:\\Users\\RG\\eclipse-workspace\\RGCMD\\src\\test\\resources\\rg\\Drivers\\chromedriver.exe");
				ChromeOptions options=new ChromeOptions();
				options.setExperimentalOption("useAutomationExtension", false);
				options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				options.setExperimentalOption("prefs", prefs);
			
				Chrome_driver=new ChromeDriver(options);
				Chrome_driver.get(websiteurl);
				
				Chrome_driver.manage().window().maximize();
				//driver.manage().deleteAllCookies();
				Chrome_driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				//driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
				driver=Chrome_driver;
				}
		  else if (browsername.equalsIgnoreCase("firefox"))
			{
			
			System.out.println("You are in FireFox browser");
			System.setProperty("webdriver.gecko.driver","C:\\Users\\RG\\eclipse-workspace\\OTA_FrameworkAutomation\\src\\test\\resources\\Drivers\\geckodriver.exe");
			FirefoxOptions options=new FirefoxOptions();
			FirefoxProfile ff=new FirefoxProfile();
			ff.setAcceptUntrustedCertificates(false);
			options.setProfile(ff);
 
			FireFox_driver=new FirefoxDriver(options);
			FireFox_driver.get(websiteurl);
			
			FireFox_driver.manage().window().maximize();
			//driver.manage().deleteAllCookies();
			FireFox_driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			FireFox_driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
			driver=FireFox_driver;


			}
			else if(browsername.equalsIgnoreCase("IE"))
			{
				System.out.println("You are in IE browser");
				System.setProperty("webdriver.chrome.driver","D:\\Subhash-PGOTA-Drive(T)\\Private_Data\\Browser Driver\\chromedriver.exe");
				driver = new InternetExplorerDriver();
				driver.get(websiteurl);
				driver.manage().window().maximize();
				//driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);

				}
			 else {
					throw new Exception("invalid browser name");
				}
		
		
	}
	
	public WebDriver getDriver()
	{
		return driver;
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		
		if(result.getStatus()==ITestResult.FAILURE)
		{
			String Extentpath=ota.Screenshots.OTA_ScreenShot.getScreenshotpath(getDriver());
			
			logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(Extentpath).build());
		}
		
		extent.flush();
		
	}
	
	//@AfterTest
	public void CloseDriver() 
	{
		driver.close();
	}
	
	}
	

