package ota.Screenshots;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class OTA_ScreenShot extends ota.BrowserFiresUtility.BrowserFires

{
	
	

	public static void captureScreenshot(WebDriver driver,String path,String screenshotName) throws Exception
	{
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM");
		String Date=formatter.format(date);
	      GregorianCalendar gcalendar = new GregorianCalendar();
	      int HH=gcalendar.get(Calendar.HOUR);
	      int MM=gcalendar.get(Calendar.MINUTE);
	      int SS=gcalendar.get(Calendar.SECOND);
	      int MS=gcalendar.get(Calendar.MILLISECOND);
	      
	      //System.out.println("Screenshot taken"+DateTime);

			try
			{
				
				TakesScreenshot ts=((TakesScreenshot)driver);
				File source=ts.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(source, new File(path+screenshotName+Date+HH+"-"+MM+"-"+SS+"-"+MS+".png"));
				System.out.println("Screenshot taken");
			}
			catch(Exception e)
			{
				System.out.println("Exception while taking screenshot "+e.getMessage());
			}
		
	}
	
	public static String getScreenshotpath(WebDriver driver)
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		
		File src=ts.getScreenshotAs(OutputType.FILE);
		
		String path=System.getProperty("user.dir")+"/Screenshot/"+System.currentTimeMillis()+".png";
		
		File destination=new File(path);
		
		try 
		{
			FileUtils.copyFile(src, destination);
		} catch (IOException e) 
		{
			System.out.println("Capture Failed "+e.getMessage());
		}
		
		return path;
	}
	
	
}
