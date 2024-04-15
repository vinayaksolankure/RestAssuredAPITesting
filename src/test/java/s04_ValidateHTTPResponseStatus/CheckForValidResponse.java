package s04_ValidateHTTPResponseStatus;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class CheckForValidResponse {
	
	@Test
	public void getSingleUser() {
		
		baseURI = "https://reqres.in/api/users/2";
		
		// Get RequestSpecification of the request
		RequestSpecification reqSpec = given();
		
		//call get method
		Response response =  reqSpec.get();
		
		// get response code
		int statusCode = response.getStatusCode();
		//Validate actual statusCode with expected
		Assert.assertEquals(statusCode, 200, "Incorrect Status code received");
		
		String statusLine =  response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK", "Incorrect Status Line received");
	}
	
	@Test
	public void getSingleUserUsingValidatableResponse() {
		
		baseURI = "https://reqres.in/api/users/2";
		
		// Get RequestSpecification of the request
		RequestSpecification reqSpec = given();
		
		//call get method
		Response response =  reqSpec.get();
		
		ValidatableResponse validateRes =  response.then();
		
		// Status code
		validateRes.statusCode(200);
		
		// Status Line
		validateRes.statusLine("HTTP/1.1 200 OK");
	}
	
	@Test
	public void getSingleUser_BDDStyle() {
		given()
		
		.when()
		   .get("https://reqres.in/api/users/2")
		   
		.then()
		   .statusCode(200).statusLine("HTTP/1.1 200 OK");
	}
}
