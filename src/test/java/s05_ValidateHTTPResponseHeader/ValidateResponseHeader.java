package s05_ValidateHTTPResponseHeader;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidateResponseHeader {
	
	@Test
	public void getSingleUser() {
		RequestSpecification reqSpec = given();
		
		reqSpec.baseUri("https://reqres.in");
		reqSpec.basePath("/api/users/2");	
		
		// Create get request
		Response response = reqSpec.get();
		
		// Validate Response Header
		String contentType = response.header("Content-Type");
		//System.out.println("Value od content type: " + contentType);
		String Connection = response.header("Connection");
		//System.out.println("Value od Connection: " + Connection);
		
		// Read all response header attributes/keys and print their values 
		Headers headers = response.getHeaders();
		for (Header header : headers) {
			System.out.println("Key: " + header.getName() + " |  Value: " + header.getValue());
		}
		
		// Validate header contenttype, expected value = application/json; charset=utf-8
		Assert.assertEquals(contentType, "application/json; charset=utf-8", "Header content type mismatched.");
		
		Assert.assertEquals(Connection, "keep-alive", "Header Connection mismatched.");
	}
}
