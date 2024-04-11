import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FirstGetRequest {
	
	@Test
	void testCase01() {
		Response res = RestAssured.get("https://reqres.in/api/users/2");
		System.out.println("Response" + res.asString());
		System.out.println("Status Code" + res.getStatusCode());
	}
}
