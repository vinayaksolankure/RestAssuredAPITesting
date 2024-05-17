package s29_ResponseSpecification_ResponseSpecBuilder;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecification_ResponseSpecBuilder {
	
	ResponseSpecification responseSpec = null;
	
	@BeforeClass
	public void createResponseSpec() {
		ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder();
		responseBuilder.expectStatusCode(200)
		.expectStatusLine("HTTP/1.1 200 OK")
		.expectContentType(ContentType.JSON)
		.expectResponseTime(Matchers.lessThan(10000L));
		
		/*responseBuilder.expectStatusCode(200);
		responseBuilder.expectStatusLine("HTTP/1.1 200 OK");
		responseBuilder.expectContentType(ContentType.JSON);
		responseBuilder.expectResponseTime(Matchers.lessThan(10000L));*/
		
		responseSpec = responseBuilder.build();
	}

	@Test
	public void getAllBookingIDs() {
		// https://restful-booker.herokuapp.com/booking
		RestAssured.given()
		  .baseUri("https://restful-booker.herokuapp.com/booking")
		.when()
		  .get()
		.then()
		  .spec(responseSpec)
		  .body("size()", Matchers.greaterThan(0));
		
		  /*.statusCode(200)
		  .statusLine("HTTP/1.1 200 OK")
		  .contentType(ContentType.JSON)
		  .time(Matchers.lessThan(10000L));*/
	}
	
	@Test
	public void getBookingByFirstName() {
		RestAssured.given()
		  .baseUri("https://restful-booker.herokuapp.com/booking?firstname=sally")
		.when()
		  .get()
		.then()
		  .spec(responseSpec);
		  /*.statusCode(200)
		  .statusLine("HTTP/1.1 200 OK")
		  .contentType(ContentType.JSON)
		  .time(Matchers.lessThan(10000L));*/
	}
}
