package s17_JSONObject_usingJacksonAPI;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JSONObject_usingJacksonAPI {

	@Test
	public void createUser() {
		// https://reqres.in/api/users

		/*{
		   "firstName":"Vinayak",
		   "lastName":"Solankure",
		   "age": 25,
		   "salary": 1034.56,
		   "IsMarried":true,
		   "TechSkill":{
				"Programming language":"Java",
				"WebAutomation":"Selenium",
				"API testing" : "Rest Assured"
		             }

		 }*/

		//Create ObjectMapper class instance
		ObjectMapper objectMapper = new ObjectMapper();

		// Create ibject node i.e JSON node
		ObjectNode userDetails = objectMapper.createObjectNode();
		userDetails.put("firstName", "Vinayak");
		userDetails.put("lastName", "Solankure");
		userDetails.put("age", 25);
		userDetails.put("salary", 1034.56);
		userDetails.put("IsMarried", false);
		userDetails.set("Hobbies",objectMapper.convertValue(Arrays.asList("Cooking","Music"),JsonNode.class));

		ObjectNode techSkills = objectMapper.createObjectNode();
		techSkills.put("Programming language", "Java");
		techSkills.put("WebAutomation", "Selenium");
		techSkills.put("API testing", "Rest Assured");

		userDetails.set("TechSkill", techSkills);

		//print userDetails JSON Object
		try {
			String userDetailsAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userDetails);

			System.out.println("Created JSON Node is:" + userDetailsAsString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Retrieve field value
		String firstName = userDetails.get("firstName").asText();
		System.out.println("Firstname is: " + firstName);

		boolean isMarried = userDetails.get("IsMarried").asBoolean();
		System.out.println("IsMarried is: " + isMarried);

		//retried field value of webAutomation
		String webAutomationValue = userDetails.get("TechSkill").get("WebAutomation").asText();
		System.out.println("webAutomationValue is: " + webAutomationValue);

		System.out.println("\n----------------------------Print all fields name----------------------------\n");

		Iterator<String> fieldNameIterator = userDetails.fieldNames();

		while (fieldNameIterator.hasNext()) {
			System.out.println(fieldNameIterator.next());
		}

		System.out.println("\n----------------------------Print all fields Values----------------------------\n");

		Iterator<JsonNode> fieldValuesIterator = userDetails.elements();

		while (fieldValuesIterator.hasNext()) {
			System.out.println(fieldValuesIterator.next());
		}

		System.out.println("\n----------------------------Print all field Names Values Both----------------------------\n");

		Iterator<Entry<String, JsonNode>> keyValueEntries = userDetails.fields();

		while (keyValueEntries.hasNext()) {
			Entry<String, JsonNode> node =  keyValueEntries.next();
			String key = node.getKey();
			JsonNode value = node.getValue();
			System.out.println("Key: " + key + " | " + "Value: " + value);
		}

		//Remove field "first name" from json object or objectnode

		String removedValued = userDetails.remove("firstName").asText();
		System.out.println("Removed firstname value is: " + removedValued);

		//update json object or object node
		userDetails.put("lastName", "Sharma");

		techSkills.put("Programming language", "C#");
		userDetails.set("TechSkill", techSkills);



		//print removed and updated userDetails JSON Object
		System.out.println("\n----------------------------Print JSON Node After Remove and Update method:----------------------------\n");

		try {
			String userDetailsAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userDetails);

			System.out.println("JSON Node After Remove and Update method:" + userDetailsAsString);

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//create Request Specification
		RequestSpecification reqSpec = RestAssured.given();

		//specify URL
		reqSpec.baseUri("https://reqres.in/api/users");
		reqSpec.contentType(ContentType.JSON);
		reqSpec.body(userDetails);

		//perform post request
		Response response = reqSpec.post();

		System.out.println("-------------Print Http response body-----------------------------");
		response.prettyPrint();

		//Validate the status code
		Assert.assertEquals(response.statusCode(), 201,"Check for status code.");

	}
}
