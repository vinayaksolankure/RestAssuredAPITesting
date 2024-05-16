package s28_HowToAddHeaderInRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HowToAddHeaderInRequest {

	@Test(priority = 0)
	public void testMethod1Simple() {
		System.out.println("----------------------------------------------------------------------");
		RequestSpecification reqSpec = RestAssured.given();

		// add header
		reqSpec.header("Header1", "Value1");
		reqSpec.log().headers();

		reqSpec.baseUri("https://reqres.in/api/users?page=1");

		// perform get request
		Response response = reqSpec.get();

		// validate response cpde
		Assert.assertEquals(response.statusCode(), 200, "Check for status code");
		
		System.out.println("----------------------------------------------------------------------");
	}
	
	@Test(priority = 1)
	public void testMethod2ByMap() {
		Map<String, String> requestHeader = new HashMap<>();
		requestHeader.put("Header1", "Value1");
		requestHeader.put("Header2", "Value2");
		
		RequestSpecification reqSpec = RestAssured.given();

		// add header
		reqSpec.headers(requestHeader);
		reqSpec.log().headers();

		reqSpec.baseUri("https://reqres.in/api/users?page=1");

		// perform get request
		Response response = reqSpec.get();

		// validate response cpde
		Assert.assertEquals(response.statusCode(), 200, "Check for status code");
		System.out.println("----------------------------------------------------------------------");
	}
	
	@Test(priority = 2)
	public void testMethod3ByHeaderClass() {

		Header requestHeaderObj = new Header("Header1", "Value1");

		RequestSpecification reqSpec = RestAssured.given();

		// add header
		reqSpec.header(requestHeaderObj);
		reqSpec.log().headers();

		reqSpec.baseUri("https://reqres.in/api/users?page=1");

		// perform get request
		Response response = reqSpec.get();

		// validate response cpde
		Assert.assertEquals(response.statusCode(), 200, "Check for status code");
		System.out.println("----------------------------------------------------------------------");
	}
	
	@Test(priority = 3)
	public void testMethod4ByHeadersClass() {

		Header header1 = new Header("Header1", "Value1");
		Header header2 = new Header("Header2", "Value2");
		Header header3 = new Header("Header3", "Value3");
		Header header4 = new Header("Header4", "Value4");

		List<Header> headerList = new ArrayList<Header>();
		headerList.add(header1);
		headerList.add(header2);
		headerList.add(header3);
		headerList.add(header4);

		Headers headersObj = new Headers(headerList);

		RequestSpecification reqSpec = RestAssured.given();

		// add header
		reqSpec.headers(headersObj);
		reqSpec.log().headers();

		reqSpec.baseUri("https://reqres.in/api/users?page=1");

		// perform get request
		Response response = reqSpec.get();

		// validate response cpde
		Assert.assertEquals(response.statusCode(), 200, "Check for status code");
		System.out.println("----------------------------------------------------------------------");
	}
	
	@Test(priority = 4)
	public void testMethod5BDDStyle() {

		Header header1 = new Header("Header1", "Value1");
		Header header2 = new Header("Header2", "Value2");
		Header header3 = new Header("Header3", "Value3");
		Header header4 = new Header("Header4", "Value4");

		List<Header> headerList = new ArrayList<Header>();
		headerList.add(header1);
		headerList.add(header2);
		headerList.add(header3);
		headerList.add(header4);

		Headers headersObj = new Headers(headerList);

		// BDD Style
		RestAssured
		.given()
		  .headers(headersObj)
		  .log().headers()
		.when()
		  .get("https://reqres.in/api/users?page=1")
		.then()
		  .log().body();
		System.out.println("----------------------------------------------------------------------");
	}
}
