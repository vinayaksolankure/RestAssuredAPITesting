package s07_QueryParameters;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class QueryParameters {
	
	@Test
	public void filterData() {
		// Get Request specification for the given request
		RequestSpecification reqSpec = given();
		
		// specify uri and path
		reqSpec.baseUri("https://reqres.in");
		reqSpec.basePath("api/users");
		reqSpec.queryParam("page", 2).queryParam("id", 10);
		
		// perform get request
		Response response = reqSpec.get();
		
		// Read response body and print it
		String responseBody = response.getBody().asString();
		System.out.println("Response Body: " + responseBody);
		
		// Get JSON path view of response body
		JsonPath jsonPathView = response.jsonPath();
		
		// Get first_name - x.data.first_name
		String firstname = jsonPathView.get("data.first_name");
		
		Assert.assertEquals(firstname, "Byron", "Verify first name");
		
	}
}
