package AmazonTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import ota.FileOperations.FileFunctions;


public class ItemSelect extends ota.BrowserFiresUtility.BrowserFires
{

	@Test
	public static void Item_Select() throws IOException
	{
	
		driver.findElement(By.cssSelector(FileFunctions.getLocatorFromElementFile("login"))).click();
		

		
	}
	
	
	
}
