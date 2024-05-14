package s26_IgnoreUnknownProperties_Deserialization;

import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class IgnoreUnknownProperties_Deserialization {

	@Test
	public void test1() throws JsonMappingException, JsonProcessingException {
		String payload = "{\r\n"
				+ "  \"firstName\" : \"Suresh\",\r\n"
				+ "  \"lastName\" : \"Mehra\",\r\n"
				+ "  \"gender\" : \"Male\",\r\n"
				+ "  \"age\" : 35,\r\n"
				+ "  \"salary\" : 10000.0,\r\n"
				+ "  \"married\" : true,\r\n"
				+ "  \"fullname\" : \"Suresh Mehra\"\r\n"
				+ "}\r\n"
				+ "";

		ObjectMapper objectMapper = new ObjectMapper();
		// To Ignore Unknown properties of pojo class
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		//DESERIALIZATION
		// Convert JSON string to class object
		Employee emp2 = objectMapper.readValue(payload, Employee.class);

		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("Firstname: " + emp2.getFirstName());
		System.out.println("Lastname: " + emp2.getLastName());
		System.out.println("Gender: " + emp2.getGender());
		System.out.println("Age: " + emp2.getAge());
		System.out.println("Salary: " + emp2.getSalary());
		System.out.println("Marital Status: " + emp2.isMarried());
		//System.out.println("FullName: " + emp2.getFullname());
		System.out.println("-------------------------------------------------------------------------------");
	}
}
