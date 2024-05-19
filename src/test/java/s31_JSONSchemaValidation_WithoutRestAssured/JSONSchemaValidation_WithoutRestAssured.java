package s31_JSONSchemaValidation_WithoutRestAssured;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import io.restassured.module.jsv.JsonSchemaValidator;

public class JSONSchemaValidation_WithoutRestAssured {
	
	@Test
	public void testMethod() {
		String json = "{\r\n"
				+ "        \"id\": 2,\r\n"
				+ "        \"email\": \"janet.weaver@reqres.in\",\r\n"
				+ "        \"first_name\": \"Janet\",\r\n"
				+ "        \"last_name\": \"Weaver\"\r\n"
				+ "}";
		
		MatcherAssert.assertThat(json, JsonSchemaValidator.matchesJsonSchemaInClasspath("EmployeeSchema.json"));
	}
}
