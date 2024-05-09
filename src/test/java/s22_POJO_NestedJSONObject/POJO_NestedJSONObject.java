package s22_POJO_NestedJSONObject;

import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class POJO_NestedJSONObject {
	
	@Test
	public void createUser() throws JsonProcessingException {
		
		EmployeePOJOClass emp1 = new EmployeePOJOClass();
		emp1.setFirstName("Vinayak");
		emp1.setLastName("Solankure");
		emp1.setGender("Male");
		emp1.setAge(25);
		emp1.setSalary(25000.25);
		
		EmployeeAddress emp1Address = new EmployeeAddress();
		emp1Address.setStreet("East to Ram Mandir");
		emp1Address.setCity("Ayodhya");
		emp1Address.setState("UP");
		emp1Address.setPincode(255623);
		
		emp1.setAddress(emp1Address);
		
		// Covert Class Object to JSON Object as a string
		ObjectMapper objectMapper = new ObjectMapper();
		
		String jsonPayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp1);
		
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("https://httpbin.org/post");
		reqSpec.contentType(ContentType.JSON);
		reqSpec.body(jsonPayload);
		
		Response response = reqSpec.post();
		response.prettyPrint();
		
	}
}
