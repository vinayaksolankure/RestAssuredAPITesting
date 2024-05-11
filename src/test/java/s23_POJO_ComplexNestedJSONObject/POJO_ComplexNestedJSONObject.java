package s23_POJO_ComplexNestedJSONObject;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import s22_POJO_NestedJSONObject.EmployeeAddress;
import s22_POJO_NestedJSONObject.EmployeePOJOClass;

public class POJO_ComplexNestedJSONObject {

	@Test
	public void createUser() throws JsonProcessingException {
		/*"companyName" :"XYZ Ltd",
		"Street": "Arifac Avenue",
			"City": "RK Puram, Delhi",
			"State": "New Delhi",
			"pin code":110066,
		"BankAccounts":["HDFC","SBI","AXIS"]*/

		// Create Request payload
		NestedJSONPOJOClass requestPayload = new NestedJSONPOJOClass();
		requestPayload.setCompanyName("XYZ Ltd");
		requestPayload.setStreet("Arifac Avenue");
		requestPayload.setCity("RK Puram, Delhi");
		requestPayload.setState("New Delhi");
		requestPayload.setPincode("110066");

		List<String> banks = new ArrayList<String>();
		banks.add("HDFC");
		banks.add("SBI");
		banks.add("AXIS");

		requestPayload.setBankAccount(banks);

		EmployeePOJOClass emp1 = new EmployeePOJOClass();
		emp1.setFirstName("Vinayak");
		emp1.setLastName("Solankure");
		emp1.setGender("Male");
		emp1.setAge(25);
		emp1.setSalary(25000.25);
		EmployeeAddress emp1Address = new EmployeeAddress();
		emp1Address.setStreet("Pacific Avenue");
		emp1Address.setCity("RK Puram, Pune");
		emp1Address.setState("Maha");
		emp1Address.setPincode(459656);
		emp1.setAddress(emp1Address);

		EmployeePOJOClass emp2 = new EmployeePOJOClass();
		emp2.setFirstName("Sourabh");
		emp2.setLastName("Bhalerao");
		emp2.setGender("Male");
		emp2.setAge(26);
		emp2.setSalary(26000.26);
		EmployeeAddress emp2Address = new EmployeeAddress();
		emp2Address.setStreet("Atlantic Avenue");
		emp2Address.setCity("RK Puram, Solapur");
		emp2Address.setState("Maha");
		emp2Address.setPincode(568925);
		emp2.setAddress(emp2Address);

		EmployeePOJOClass emp3 = new EmployeePOJOClass();
		emp3.setFirstName("Shirish");
		emp3.setLastName("Mistari");
		emp3.setGender("Male");
		emp3.setAge(27);
		emp3.setSalary(27000.27);
		EmployeeAddress emp3Address = new EmployeeAddress();
		emp3Address.setStreet("Sapin Avenue");
		emp3Address.setCity("RK Puram, Jalgaon");
		emp3Address.setState("Maha");
		emp3Address.setPincode(459656);
		emp3.setAddress(emp3Address);

		List<EmployeePOJOClass> employees = new ArrayList<EmployeePOJOClass>();
		employees.add(emp1);
		employees.add(emp2);
		employees.add(emp3);
		
		requestPayload.setEmployeeList(employees);

		// Covert class Object to JSON object as string
		ObjectMapper objectMapper = new ObjectMapper();

		String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestPayload);	

		RequestSpecification reqSpec = RestAssured.given();

		//specify URL
		reqSpec.baseUri("http://httpbin.org/post");

		//specify content type and request payload
		reqSpec.contentType(ContentType.JSON);
		reqSpec.body(payload);
		Response response = reqSpec.post();

		System.out.println("------------response body-----------------");
		response.prettyPrint();

		Assert.assertEquals(response.statusCode(), 200, "check for status code.");

	}
}
