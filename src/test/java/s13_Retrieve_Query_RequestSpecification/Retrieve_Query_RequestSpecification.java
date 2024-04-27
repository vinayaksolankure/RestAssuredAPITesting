package s13_Retrieve_Query_RequestSpecification;

import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public class Retrieve_Query_RequestSpecification {

	@Test
	public void createUser() {
		// Create request body
		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "Vinayak");
		jsonData.put("job", "QA");

		//https://reqres.in/api/users
		RequestSpecification reqSpec = given();

		reqSpec.baseUri("https://reqres.in").
		basePath("/api/users").
		contentType(ContentType.JSON).
		body(jsonData.toJSONString()).header("header1", "header1value");

		// Query details from requestspecification
		QueryableRequestSpecification queryRequest = SpecificationQuerier.query(reqSpec);

		// Get base uri
		String retrieveBaseURI = queryRequest.getBaseUri();
		System.out.println("BaseURI: " + retrieveBaseURI);

		// Get base path
		String retrieveBasePath = queryRequest.getBasePath();
		System.out.println("BasePath: " + retrieveBasePath);

		// Get Request body
		String retrieveRequestBody = queryRequest.getBody();
		System.out.println("Request Body: " + retrieveRequestBody); 
		
		// Get Request Headers
		Headers allHeaders = queryRequest.getHeaders();
		
		System.out.println("\n-------------------------------REQUEST HEADER---------------------------");
		for (Header header : allHeaders) {
			System.out.println("Header Name: " + header.getName() + " | " + "Header Value: " + header.getValue());
		}
	}
}
