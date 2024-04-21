package s09_Authorization_BearerTokenAuthentication;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BearerTokenAuthentication {
	
	@Test
	public void bearerToken() {
		RequestSpecification reqSpec = given();
		
		reqSpec.baseUri("https://gorest.co.in");
		reqSpec.basePath("/public/v2/users");
		
		JSONObject payload = new JSONObject();
		payload.put("name", "Solankure");
		payload.put("gender", "Male");
		payload.put("email", "sample45@gmail.com");
		payload.put("status", "Active");
		
		String authToken = "Bearer 0f6356baaa68b45ca871fd4919d5bbcf053c00bcf5ca86384faf30911bde81ab";
		
		reqSpec.header("Authorization", authToken).contentType(ContentType.JSON).body(payload.toJSONString());
		
		Response response = reqSpec.post();
		Assert.assertEquals(response.statusCode(), 201, "Check for status code.");
		System.out.println("Response status line: " + response.statusLine());
		System.out.println("Response body: " + response.body().asString());
	}
}
