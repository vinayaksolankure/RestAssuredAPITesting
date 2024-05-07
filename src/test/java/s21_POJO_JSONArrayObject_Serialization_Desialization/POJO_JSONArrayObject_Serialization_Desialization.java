package s21_POJO_JSONArrayObject_Serialization_Desialization;

import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import s20_POJO_JSONObjectData_Serialization_Desialization.Employee;

public class POJO_JSONArrayObject_Serialization_Desialization {

	@Test
	public void createEmployeesJSONArray() throws JsonProcessingException {
		// Create First Employee Object
		Employee emp1 = new Employee();
		emp1.setFirstName("Vinayak");
		emp1.setLastName("Solankure");
		emp1.setGender("Male");
		emp1.setAge(25);
		emp1.setSalary(25000.00);

		// Create Second Employee Object
		Employee emp2 = new Employee();
		emp2.setFirstName("Sourabh");
		emp2.setLastName("Bhalerao");
		emp2.setGender("Male");
		emp2.setAge(26);
		emp2.setSalary(28000.00);

		// Create Second Employee Object
		Employee emp3 = new Employee();
		emp3.setFirstName("Shirish");
		emp3.setLastName("Mistari");
		emp3.setGender("Male");
		emp3.setAge(27);
		emp3.setSalary(29000.00);
		
		// Create List of Employee
		List<Employee> listOfEmp = new ArrayList<Employee>();
		listOfEmp.add(emp1);
		listOfEmp.add(emp2);
		listOfEmp.add(emp3);
		
		//SERIALIZATION 
		// Covert Employee Class Object to JSON Array Payload as a string
		ObjectMapper objMapper = new ObjectMapper();
		
		String jsonArrayPayload = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(listOfEmp);
		
		System.out.println("--------------------------SERIALIZATION-------------------------------------");
		System.out.println("-----------Employee Class Object to JSON Array Payload as a string------------");
		System.out.println(jsonArrayPayload);
		
		RequestSpecification reqSpec = given();
		reqSpec.baseUri("https://httpbin.org/post");
		reqSpec.contentType(ContentType.JSON);
		reqSpec.body(jsonArrayPayload);
		
		Response response = reqSpec.post();
		
		System.out.println("-----------Post Response Payload------------");
		response.prettyPrint();
		
		Assert.assertEquals(response.statusCode(), 200, "Check for the status code");
		
		//DESERIALIZATION
		// Convert JSONArray to Employee Class Object 
		ResponseBody responseBody = response.getBody();
		
		JsonPath jsonPathView = responseBody.jsonPath();
		
		List<Employee> allEmployees = jsonPathView.getList("json", Employee.class);
		
		System.out.println("--------------------------DESERIALIZATION-------------------------------------");
		System.out.println("--------------------------JSONArray to Employee Class Object-------------------------------------");
		for (Employee employee : allEmployees) {
			System.out.println(employee.getFirstName() + " " + employee.getLastName());
		}
		
	}
}
