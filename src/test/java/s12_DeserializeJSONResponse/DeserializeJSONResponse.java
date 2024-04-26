package s12_DeserializeJSONResponse;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class DeserializeJSONResponse {
	
	@Test
	public void createUser() {
		//https://reqres.in/api/users
		RequestSpecification reqSpec = given();
		reqSpec.baseUri("https://reqres.in");
		reqSpec.basePath("/api/users");
		
		// Create request body
		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "Vinayak");
		jsonData.put("job", "QA");
		
		// perform post request
		Response response = reqSpec.
		contentType(ContentType.JSON).
		body(jsonData.toJSONString()).
		post();
		
		ResponseBody responseBody = response.getBody();
		
		//Deserialize response body i.e JSON response body to class object
		JSONPostRequetstResponse responseClass = responseBody.as(JSONPostRequetstResponse.class);
		
		Assert.assertEquals(responseClass.name, "Vinayak", "Check for name");
		Assert.assertEquals(responseClass.job, "QA", "Check for job");
		
		System.out.println("name: " + responseClass.name);
		System.out.println("job: " + responseClass.job);
		System.out.println("id: " + responseClass.id);
		System.out.println("createdAt: " + responseClass.createdAt);
	}
}
