package s06_ValidateJSONResponseBody;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class ValidateJSONResponseBody {
	
	@Test
	public void userListResponseBody() {
		//https://reqres.in/api/users?page=2
		//Get RequestSpecifications
		RequestSpecification reqSpec = given();
		
		// Specify base uri and path
		reqSpec.baseUri("https://reqres.in");
		reqSpec.basePath("api/users?page=2");
		
		// Create get request
		Response response = reqSpec.get();
		
		// read response body
		ResponseBody responseBody = response.getBody();
		String responseString = responseBody.asString();
		
		System.out.println("ResponseBody: " + responseString);
		
		// Check for presence of george in response body
		Assert.assertEquals(responseString.contains("george"), true, "Check for presence of george");
		
		// Get JSON path view of response body
		JsonPath jsonPathView = responseBody.jsonPath();
		
		// x.data[0].first_name   - took this from JSONPath finder tool https://jsonpathfinder.com/
		String firstName = jsonPathView.get("data[0].first_name");
		Assert.assertEquals(firstName, "George", "Check for presense of first name");
		
		// printing 2nd example 
		System.out.println("email address: " + jsonPathView.get("data[1].avatar"));
	}
}
