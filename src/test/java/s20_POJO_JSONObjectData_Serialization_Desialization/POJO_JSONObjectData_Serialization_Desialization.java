package s20_POJO_JSONObjectData_Serialization_Desialization;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class POJO_JSONObjectData_Serialization_Desialization {
	
	@Test
	public void createJSONObjectFromEmployeeClassObject() throws JsonProcessingException {
		Employee emp1 = new Employee();
		emp1.setFirstName("Vinayak");
		emp1.setLastName("Solankure");
		emp1.setGender("Male");
		emp1.setAge(25);
		emp1.setSalary(25000.00);
		
		//SERIALIZATION
		// Convert employee class object to JSON payload as a string
		ObjectMapper objMapper = new ObjectMapper();
		
		String employeeJSON = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp1);
	
		System.out.println("--------------------------SERIALIZATION-------------------------------------");
		System.out.println(employeeJSON);
		
		RequestSpecification reqSpec = RestAssured.given();
		
		reqSpec.baseUri("https://httpbin.org/post");
		reqSpec.contentType(ContentType.JSON);
		reqSpec.body(employeeJSON);
		
		Response response = reqSpec.post();
		System.out.println("-------------------------POST METHOD RESPONSE-------------------------------");
		response.prettyPrint();
		System.out.println("--------------------------DESERIALIZATION-----------------------------------");
		
		Assert.assertEquals(response.statusCode(), 200, "Check for status code");
		
		//DESERIALIZATION
		// Convert JSON string to class object
		Employee emp2 = objMapper.readValue(employeeJSON, Employee.class);
		
		System.out.println("Firstname: " + emp2.getFirstName());
		System.out.println("Lastname: " + emp2.getLastName());
		System.out.println("Gender: " + emp2.getGender());
		System.out.println("Age: " + emp2.getAge());
		System.out.println("Salary: " + emp2.getSalary());
		System.out.println("-------------------------------------------------------------------------------");
	}
}
