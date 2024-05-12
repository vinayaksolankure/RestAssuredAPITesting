package s24_IgnoreEmptyNullDefaultValuesInPayload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class IgnoreEmptyNullDefaultValuesInPayload {

	@Test
	public void testMethod1() throws JsonProcessingException {
		// Create Payload
		EmployeePOJOClass emp1 = new EmployeePOJOClass();
		emp1.setFirstName("Vinayak");
		//emp1.setLastName("Solankure");
		emp1.setGender("Male");
		emp1.setAge(25);
		emp1.setSalary(25000.00);
		emp1.setMarried(false);
		
		// Empty array
		String[] hobbies = {"Reading", "Playing", "Writing"};
		emp1.setHobbies(hobbies);
		// Empty list
		List<String> degrees = new ArrayList<String>();
		degrees.add("BCA");
		degrees.add("BE");
		degrees.add("MSC");
		emp1.setDegrees(degrees);
		// Empty Map
		Map<String, String> familyMembers = new HashMap<String, String>();
		familyMembers.put("1", "Mother");
		familyMembers.put("2", "Father");
		emp1.setFamilyMembers(familyMembers);

		// Convert employee class object to JSON payload as a string
		ObjectMapper objMapper = new ObjectMapper();
		String employeeJSON = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp1);
		System.out.println(employeeJSON);
		
		
	}
}
