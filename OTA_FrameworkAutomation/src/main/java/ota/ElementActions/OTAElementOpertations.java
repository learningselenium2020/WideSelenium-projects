package ota.ElementActions;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ota.FileOperations.FileFunctions;
import ota.Screenshots.*;

public class OTAElementOpertations 
{

	
	public static void SendKey_PassData(WebDriver driver, String locatorName, String data) throws Exception
	{
		String screenshotpath=ota.FileOperations.FileFunctions.getDataFromConfigFile("screenshotpath");
		try{
			String ElementLocatorName = FileFunctions.getLocatorFromElementFile(locatorName);
			driver.findElement(By.cssSelector(ElementLocatorName)).clear();
			driver.findElement(By.cssSelector(ElementLocatorName)).sendKeys(data);
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
			OTA_ScreenShot.captureScreenshot(driver, screenshotpath, locatorName);
		}
		
	}
	
	public static void SendKey_PassData(WebDriver driver, String locatorName, Keys key) throws Exception
	{
		String screenshotpath=ota.FileOperations.FileFunctions.getDataFromConfigFile("screenshotpath");

		try{
			String ElementLocatorName = FileFunctions.getLocatorFromElementFile(locatorName);
			driver.findElement(By.cssSelector(ElementLocatorName)).sendKeys(key);
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
			OTA_ScreenShot.captureScreenshot(driver, screenshotpath, locatorName);
		}
		
	}
	
	public static void clickOnElement(WebDriver driver, String locatorName) throws Exception 
	{
		String screenshotpath=ota.FileOperations.FileFunctions.getDataFromConfigFile("screenshotpath");

		try{
			String elementName = FileFunctions.getLocatorFromElementFile(locatorName);
			driver.findElement(By.cssSelector(elementName)).click();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			OTA_ScreenShot.captureScreenshot(driver, screenshotpath, locatorName);
		}
		
	}
	
	public static String GetTextValueOFElement(WebDriver driver, String locatorName) throws Exception 
	{
		String screenshotpath=ota.FileOperations.FileFunctions.getDataFromConfigFile("screenshotpath");

		try{
			String elementName = FileFunctions.getLocatorFromElementFile(locatorName);
			return driver.findElement(By.cssSelector(elementName)).getText();

		} 
		catch (Exception e)
		{
			
			OTA_ScreenShot.captureScreenshot(driver, screenshotpath, locatorName);
			return "";
			
		}
		
	}
	
	public static String GetAttributeValueOFElement(WebDriver driver, String locatorName,String attributename) throws Exception 
	{
		String screenshotpath=ota.FileOperations.FileFunctions.getDataFromConfigFile("screenshotpath");

		
		try{
			String elementName = FileFunctions.getLocatorFromElementFile(locatorName);
			return driver.findElement(By.cssSelector(elementName)).getAttribute(attributename);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			OTA_ScreenShot.captureScreenshot(driver, screenshotpath, locatorName);
			return "";
		}
		
	}
	
	public static List<WebElement> GetElementList(WebDriver driver, String locatorName) throws Exception 
	{
	
			String elementName = FileFunctions.getLocatorFromElementFile(locatorName);
			return driver.findElements(By.cssSelector(elementName));
	

		
	}
	
}
