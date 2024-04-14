package s03;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class Test_GetMethod {

	@Test
	public void test01() {
		Response res = get("https://reqres.in/api/users/2");
		System.out.println("Response: " + res.asString());
		System.out.println("Status Code: " + res.getStatusCode());
		System.out.println("Response Body: " + res.getBody().asString());
		System.out.println("Response Time: " + res.getTime());
		System.out.println("Response Header: " + res.getHeader("Content-Type"));

		// Validate Status code
		// Expected = 200
		int expectedStatusCode = 200;
		int actualStatusCode = res.getStatusCode();
		
		Assert.assertEquals(expectedStatusCode, actualStatusCode);
	}
	
	@Test
	public void test02() {
		// Given, When, Then
		baseURI = "https://reqres.in/api/users";
		given()
		.queryParam("page", "2")
		.when().get()
		.then().statusCode(200);
	}
}
