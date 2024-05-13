package s25_IgnoreFieldsFromPOJOClass_Serialization_Deserialization;

import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class IgnoreFieldsFromPOJOClass_Serialization_Deserialization {

	@Test
	public void testMethod1() throws JsonProcessingException {
		// Create Payload
		EmployeePOJOClass emp1 = new EmployeePOJOClass();
		emp1.setFirstName("Vinayak");
		emp1.setLastName("Solankure");
		emp1.setGender("Male");
		emp1.setAge(25);
		emp1.setSalary(25000.00);
		emp1.setMarried(false);
		emp1.setFullName("Vinayak Solankure");

		// SERIALIZATION
		// Convert employee class object to JSON payload as a string
		ObjectMapper objMapper = new ObjectMapper();
		String employeeJSON = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp1);
		System.out.println("--------------------------SERIALIZATION-----------------------------------");
		System.out.println(employeeJSON);

		// JSON Payload String to Employee Class Object
		String payload = "{\r\n"
				+ "  \"firstName\" : \"Vinayak\",\r\n"
				+ "  \"lastName\" : \"Solankure\",\r\n"
				+ "  \"gender\" : \"Male\",\r\n"
				+ "  \"age\" : 25,\r\n"
				+ "  \"salary\" : 25000.0,\r\n"
				+ "  \"fullName\" : \"Vinayak Solankure\",\r\n"
				+ "  \"married\" : false\r\n"
				+ "}";

		//DESERIALIZATION
		// Convert JSON string to class object
		EmployeePOJOClass emp2 = objMapper.readValue(payload, EmployeePOJOClass.class);

		System.out.println("--------------------------DESERIALIZATION-----------------------------------");
		System.out.println("Firstname: " + emp2.getFirstName());
		System.out.println("Lastname: " + emp2.getLastName());
		System.out.println("Gender: " + emp2.getGender());
		System.out.println("Age: " + emp2.getAge());
		System.out.println("Salary: " + emp2.getSalary());
		System.out.println("FullName: " + emp2.getFullName());
		System.out.println("Marital Status: " + emp2.isMarried());
		System.out.println("-------------------------------------------------------------------------------");
	}
}
