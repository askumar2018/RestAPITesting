/**
 * Post call API automation for users module
 */

package Quote.postRequest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class PostRequest {

	/**
	 * Setting the url
	 * 
	 */
	@BeforeClass
	  public void setBaseUri () throws IOException {
		Properties Properties;
		FileInputStream Locator = new FileInputStream("Config/myConfig.properties");
		Properties = new Properties();
		Properties.load(Locator);

		RestAssured.baseURI = Properties.getProperty("URL");
		String urlis=RestAssured.baseURI;
		System.out.println("urlis " +urlis);
	    //RestAssured.baseURI = Properties.getProperty("IP_ADDR")+":8088";
	  }

		/**
		 * Reading the data from excel and calling post request to create users
		 * 
		 */
		
		@Test
		public  void RegistrationSuccessful () throws BiffException, IOException{
			
			//String FilePath = "D:\\Gelato\\Data.xls";
			String FilePath ="C:\\Users\\Siva\\Documents\\RestAPI\\GelatoTest\\Config\\RestApiData.xls";
			FileInputStream fs = new FileInputStream(FilePath);
			Workbook wb = Workbook.getWorkbook(fs);
			 ResponseBody body ;
			 Response response;
			 
		// TO get the access to the sheet
			 
		Sheet sh = wb.getSheet("Sheet1");
		RequestSpecification request = RestAssured.given();
		
		// To get the number of rows present in sheet
		int totalNoOfRows = sh.getRows();
	    System.out.println(totalNoOfRows);
	    
			// To get the number of columns present in sheet
			int totalNoOfCols = sh.getColumns();
			System.out.println(totalNoOfCols);
			String s[][]= new String[totalNoOfCols][totalNoOfRows];
     		for(int i=0;i<totalNoOfCols;i++) {
			for(int j=0;j<totalNoOfRows;j++) {
					
					
				 response= RestAssured.given()
						.contentType("application/json")
						.body(sh.getCell(i, j).getContents())
						.when()
						.post("/quote")
						.then()
						.statusCode(200)
						.extract().response();	
					
				
				  body = response.getBody();
				 System.out.println("Response Body of post call is: " + body.asString());
				
				}
			}
						
	
		
					
		
			
			
	
			
		}
	

}
