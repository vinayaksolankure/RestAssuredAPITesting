package s19_CreateOwnAPI_CRUDOperations;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateOwnAPI_CRUDOperations {

	@BeforeClass
	public void setupDefault() {
		RequestSpecification reqSpec = given();
		reqSpec.baseUri("http://localhost:3000");
		reqSpec.basePath("/users");

		RestAssured.requestSpecification = reqSpec;
	}

	@Test(priority = 0)
	public void readUserData() {

		//Perform Get Request
		Response response = RestAssured.get();

		System.out.println("Response Body Of Get User");
		response.prettyPrint();

		Assert.assertEquals(response.getStatusCode(), 200, "Check for response code");
		System.out.println("--------------------------------------------------------------------------");
	}

	@Test(enabled = false, priority = 1)
	public void createUserData() {

		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "Shirish");
		jsonData.put("age", 27);

		Response response = given()
				.header("Content-type","application/json")
				.contentType(ContentType.JSON)
				.body(jsonData.toJSONString())
				.post();

		System.out.println("Response Body Of Create User");
		response.prettyPrint();

		Assert.assertEquals(response.getStatusCode(), 201, "Check for response code");
		System.out.println("--------------------------------------------------------------------------");
	}
	
	@Test(enabled = false, priority = 2)
	public void updateUserData() {

		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "Swapnil");
		jsonData.put("age", 25);

		Response response = given()
				.header("Content-type","application/json")
				.contentType(ContentType.JSON)
				.body(jsonData.toJSONString())
				.put("/b6f9");

		System.out.println("Response Body Of Update User");
		response.prettyPrint();

		Assert.assertEquals(response.getStatusCode(), 200, "Check for response code");
		System.out.println("--------------------------------------------------------------------------");
	}
	
	@Test(enabled = true, priority = 3)
	public void deleteUserData() {

		Response response = given()
				.delete("/b6f9");

		System.out.println("Response Body Of Deleted User");
		response.prettyPrint();

		Assert.assertEquals(response.getStatusCode(), 200, "Check for response code");
		System.out.println("--------------------------------------------------------------------------");
	}
}
