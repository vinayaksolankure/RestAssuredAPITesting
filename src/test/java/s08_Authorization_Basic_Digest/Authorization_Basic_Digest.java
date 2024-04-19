package s08_Authorization_Basic_Digest;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Authorization_Basic_Digest {

	@Test
	public void basicAuth() {
		RequestSpecification reqSpec = given();

		reqSpec.baseUri("https://postman-echo.com");
		reqSpec.basePath("/basic-auth");

		// Perform get request
//		// non-preemptive/Default
//		Response response = reqSpec.auth().basic("postman", "password").get();
		// preemptive
		Response response = reqSpec.auth().preemptive().basic("postman", "password").get();

		// print status line
		System.out.println("Response status: " + response.statusLine());
		System.out.println("Response body: " + response.body().asString());
	}
	
	@Test
	public void digestAuth() {
		RequestSpecification reqSpec = given();

		reqSpec.baseUri("https://httpbin.org");
		reqSpec.basePath("/digest-auth/undefined/vinayak/vinayak");

		// Perform get request
		Response response = reqSpec.auth().digest("vinayak", "vinayak").get();
		
		Assert.assertEquals(response.statusCode(), 200, "Check for status code.");

		// print status line
		System.out.println("Digest auth Response status: " + response.statusLine());
		System.out.println("Digest auth Response body: " + response.body().asString());
	}
}
