package s11_Authorization_OAuth2_0;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Authorization_OAuth2_0 {

	static String accessToken;

	@Test(priority = 0)
	public void getAccessToken() {
		// https://api-m.sandbox.paypal.com/v1/oauth2/token
		String clientId = "AT0DL1bHOVgaeJE6z35C_T7G0ykR-y82cX2DZBryTgA4hobdHxDpEcodBnN-oopjQbvrWub8y6lTc-lm";
		String clientSecret = "EDG5dN0SgRzNhaSou5HHxaNxxOnU8bQ21ZukUTHJ-WUv6mH5H9UtoIjI2H5zb2fEv7ORYZRb6-LAVBcR";

		RequestSpecification reqSpec = given();

		reqSpec.baseUri("https://api-m.sandbox.paypal.com");
		reqSpec.basePath("/v1/oauth2/token");

		// Basic authorization
		Response response = reqSpec.auth().preemptive().basic(clientId, clientSecret).param("grant_type", "client_credentials").post();

		response.prettyPrint(); // prints the response body

		System.out.println("Response status code: " + response.statusCode());
		System.out.println("Response status line: " + response.statusLine());

		Assert.assertEquals(response.statusCode(), 200, "Check for status code.");

		// Get Access token from response body
		accessToken = response.getBody().path("access_token");
		System.out.println("Access Token: " + accessToken);
	}

	@Test(dependsOnMethods = "getAccessToken")
	public void listInvoice() {
		//https://api-m.sandbox.paypal.com/v1/invoicing/invoices?page=3&page_size=4&total_count_required=true

		Response response = given().auth().oauth2(accessToken)
				.queryParam("page", "3")
				.queryParam("page_size", "4")
				.queryParam("total_count_required", "true")
				.get("https://api-m.sandbox.paypal.com/v1/invoicing/invoices");
		
		System.out.println("\n-------------------------ListInvoice------------------------------");

		response.prettyPrint(); // prints the response body
		System.out.println("Response status code: " + response.statusCode());
		System.out.println("Response status line: " + response.statusLine());
		Assert.assertEquals(response.statusCode(), 200, "Check for status code.");
	}
}
