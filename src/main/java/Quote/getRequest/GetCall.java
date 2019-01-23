/**
 * Rest API Automation for Users get Call 
 * 
 */
package Quote.getRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import org.json.JSONObject;
import org.json.JSONArray;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetCall {

	private String responsebody;
	
	/**
	 * 
	 * Set the Url,read the property file to get URL
	 */
	@BeforeClass
	  public void setBaseUri () throws IOException {
		Properties Properties;
		FileInputStream Locator = new FileInputStream("Config/myConfig.properties");
		Properties = new Properties();
		Properties.load(Locator);
	    RestAssured.baseURI = Properties.getProperty("URL");
	  
	  }

	

		@SuppressWarnings("unused")
		@Test
		public void getRequestQuotePage()  {
			
			 ArrayList<String> list=new ArrayList<String>();
			 JSONObject jsonObj = null;
			 JSONObject jsonObj_random = null;
			 Response response=RestAssured.given()
		        .contentType(ContentType.JSON)
		    .when()
		        .get("/quote")
		    .then()
		        .statusCode(200)
		    	.extract().response();	
			
			ResponseBody body=response.getBody();
			System.out.println("Get call response body is"+body.asString());
			
			setResponseBody(body);
			
		}
		
		public void setResponseBody(ResponseBody body){
			//ResponseBody responsebody;
			this.responsebody=body.asString();
			
		}

		public String getResponseBody() {
			// TODO Auto-generated method stub
			return responsebody;
		}



		
}
