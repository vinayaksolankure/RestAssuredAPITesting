package s10_Authorization_APIKey;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Authorization_APIKey {
	
	@Test
	public void getWeatherDataByCity() {
		// https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
		// 1d7516a33fe48f1dd14d9d65ec4d6a5a
		
		RequestSpecification reqSpec = given();
		
		reqSpec.baseUri("https://api.openweathermap.org");
		reqSpec.basePath("/data/2.5/weather");
		reqSpec.queryParam("q", "Pune").queryParam("appid", "1d7516a33fe48f1dd14d9d65ec4d6a5a");
		
		Response response = reqSpec.get();
		
		Assert.assertEquals(response.statusCode(), 200, "Check for status code.");
		System.out.println("Response status line: " + response.statusLine());
		System.out.println("Response body: " + response.body().asString());
	}
}
