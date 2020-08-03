package OTA_Booking;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import ota.FileOperations.FileFunctions;
import ota.ElementActions.OTAElementOpertations;

public class BookingGoogleBot extends ota.BrowserFiresUtility.BrowserFires

{
	Object[][] OTAExcelData;
	HashMap<Integer,String> GoogleMap;
	final static int ExcelInputSubscriberID=0;
	final static int ExcelInputRg_Hotelid=1;
	final static int ExcelInputHotelName=2;
	final static int ExcelInputHotelAddress=3;
	final static int ExcelGoogleHotelname=4;
	final static int ExcelGoogleAddress=5;
	final static int ExcelWebsiteURL=6;
	final static int ExcelWebsitehotelName=7;
	final static int ExcelWebsitehotelAddress=8;
	final static int ExcelWebsitehotelID=9;
	final static int WebsiteAirportCode=10;
	final static int ExcelWebsitecityname=11;
	final static int ExcelWebsitecountryname=12;
	
	@Test
	public void googleAgodaBot() throws Exception
	{
		String Excelpath=FileFunctions.getDataFromConfigFile("excelpath");
		String ExcelSheetname=FileFunctions.getDataFromConfigFile("sheet");

		OTAExcelData=FileFunctions.ReadExcelData(Excelpath,ExcelSheetname);

		 for(int i=1;i<OTAExcelData.length;i++)
		   {
			   driver.get(FileFunctions.getDataFromConfigFile("url"));
			   GoogleMap=new HashMap<Integer,String>();
			   String InputParameter =OTAExcelData[i][ExcelInputHotelName].toString()+"  "+OTAExcelData[i][ExcelInputHotelAddress].toString();
	           String UTF_HotelName = new String(InputParameter.getBytes("ISO-8859-15"), "UTF-8");
	           Normalizer.normalize(UTF_HotelName, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	           OTAElementOpertations.SendKey_PassData(getDriver(), "googleinputparameter", UTF_HotelName);
	           OTAElementOpertations.SendKey_PassData(getDriver(), "googleinputparameter", Keys.ENTER);         
	      try
	        {
	           String providerlink=OTAElementOpertations.GetTextValueOFElement(getDriver(),"providerlistclick");
	           Assert.assertEquals(providerlink, "View more rates");
			   String GoogleHotelname=OTAElementOpertations.GetTextValueOFElement(getDriver(), "googlehotelname");
			   String GoogleAddress=OTAElementOpertations.GetTextValueOFElement(getDriver(), "googleaddress");
	           OTAElementOpertations.clickOnElement(getDriver(), "providerlistclick");
	           int AgodaElementnumber=0;
	           
	           /* Expedia code started..Above code is Common for all OTAs  */
	           
	           try
	           {
			    List<WebElement> AllElementlist=OTAElementOpertations.GetElementList(getDriver(), "Bookingalllinkshttps");           
				GoogleMap.put(ExcelGoogleHotelname,GoogleHotelname);
				GoogleMap.put(ExcelGoogleAddress,GoogleAddress);
			    String Hotelsurl1=AllElementlist.get(AllElementlist.size()-1).getAttribute("href");
			    GoogleMap.put(ExcelWebsiteURL,Hotelsurl1);
			    
			    AgodaElementnumber=AgodaElementnumber+1;
			    
			   }catch(Exception e){System.out.println(e.getMessage());
}
	        	  			   
	           try
	           {
	           if(AgodaElementnumber==0)
	           {
	        	List<WebElement> AllElementlist=OTAElementOpertations.GetElementList(getDriver(), "Bookingalllinkshttp");           
			    GoogleMap.put(ExcelGoogleHotelname,GoogleHotelname);
			    GoogleMap.put(ExcelGoogleAddress,GoogleAddress);
				String Hotelsurl1=AllElementlist.get(AllElementlist.size()-1).getAttribute("href");
				GoogleMap.put(ExcelWebsiteURL,Hotelsurl1);
	           }
	            }
	           catch(Exception e)
	           {
	        	   System.out.println(e.getMessage());
	           }
	           
	           try
	           {
			    driver.get(GoogleMap.get(ExcelWebsiteURL));
			    WebDriverWait wait=new WebDriverWait(driver, 20);
			    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ota.FileOperations.FileFunctions.getLocatorFromElementFile("BookingHotelname"))));
			    GoogleMap.put(ExcelWebsiteURL,driver.getCurrentUrl());
			    GoogleMap.put(ExcelWebsitehotelName, OTAElementOpertations.GetTextValueOFElement(getDriver(), "BookingHotelname"));
			    GoogleMap.put(ExcelWebsitehotelAddress, OTAElementOpertations.GetTextValueOFElement(getDriver(), "BookingAddress"));
			    GoogleMap.put(ExcelWebsitehotelID, OTAElementOpertations.GetAttributeValueOFElement(getDriver(), "BookingHotelid","value"));
			    GoogleMap.put(WebsiteAirportCode, OTAElementOpertations.GetAttributeValueOFElement(getDriver(), "BookingAirportCode1","value"));
			    GoogleMap.put(WebsiteAirportCode, OTAElementOpertations.GetAttributeValueOFElement(getDriver(), "BookingAirportCode2","value"));
			    GoogleMap.put(ExcelWebsitecityname, OTAElementOpertations.GetAttributeValueOFElement(getDriver(), "BookingCityName","value"));
			    GoogleMap.put(ExcelWebsitecountryname, (OTAElementOpertations.GetAttributeValueOFElement(getDriver(), "BookingCountryName","title")).replace("Hotels in ", ""));
			    
	         } 
	         catch(Exception e){System.out.println(e.getMessage());
}
	      }
	                
	    catch(Exception e)
	    {
	    System.out.println(e.getMessage());
	    }
	
	  writeExcel(Excelpath,ExcelSheetname,i);
      Thread.sleep(1000);	  
			
		   }		
	}
	
	
		 public void writeExcel(String path,String sheet,int excelrownumber) throws Exception	
			{
			
				 File file=new File(path);
				 FileInputStream fis=new FileInputStream(file);
				 String fileType=file.getName().substring(file.getName().indexOf(".")+1);
				 Workbook Excelworkbook=null;

				   if (fileType.equalsIgnoreCase("xlsx"))
				   {
					   Excelworkbook=new XSSFWorkbook(fis);
					   
				   }
					
				   else if(fileType.equalsIgnoreCase("xls"))
				   {
					   Excelworkbook=new HSSFWorkbook(fis);
				   }
				   else
				   {
					   System.out.println("File has different file format");
				   }
				   
				   Sheet BoongSheet=Excelworkbook.getSheet(sheet);
				   Row rows=BoongSheet.getRow(excelrownumber);
				   rows.getCell(ExcelGoogleHotelname).setCellValue(GoogleMap.get(ExcelGoogleHotelname));
				   rows.getCell(ExcelGoogleAddress).setCellValue(GoogleMap.get(ExcelGoogleAddress));
				   rows.getCell(ExcelWebsiteURL).setCellValue(GoogleMap.get(ExcelWebsiteURL));
				   rows.getCell(ExcelWebsitehotelName).setCellValue(GoogleMap.get(ExcelWebsitehotelName));
				   rows.getCell(ExcelWebsitehotelAddress).setCellValue(GoogleMap.get(ExcelWebsitehotelAddress));
				   rows.getCell(ExcelWebsitehotelID).setCellValue(GoogleMap.get(ExcelWebsitehotelID));
				   rows.getCell(WebsiteAirportCode).setCellValue(GoogleMap.get(WebsiteAirportCode));
				   rows.getCell(ExcelWebsitecityname).setCellValue(GoogleMap.get(ExcelWebsitecityname));
				   rows.getCell(ExcelWebsitecountryname).setCellValue(GoogleMap.get(ExcelWebsitecountryname));

				   fis.close();
				   FileOutputStream fos=new FileOutputStream(file);
				   Excelworkbook.write(fos);
				   fos.close();
				   
			}
		 
	}


