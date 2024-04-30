package s15_createJSONObject_usingJavaMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateJSONObject_UsingJavaMap {

	@Test
	public void createAuthToken() {
		// Create JSON Object using Java Map
		/*
		 * {
    "username" : "admin",
    "password" : "password123"
}
		 */

		Map<String, String> authToken = new HashMap<String, String>();
		authToken.put("username", "admin");
		authToken.put("password", "password123");

		Response response = RestAssured.given().
				baseUri("https://restful-booker.herokuapp.com/auth").
				contentType(ContentType.JSON).
				body(authToken).
				post();

		response.prettyPrint();

		Assert.assertEquals(response.statusCode(), 200, "Check for status code");

		System.out.println("--------------------------------------------------------------------------");
	}

	@Test
	public void createUser() {
		/*
		 {
  "id": 2563,
  "type": "donut",
  "name": "Cake",
  "ppu": 0.55,
  "IsMarried": true,
  "hobbies": ["Music","Computer","Games"],
  "TechSkills": {
    "Programming Languages": "Java",
    "UI Automation": "Selenium",
    "API Testing": "Rest API"
  }
}
		 */

		HashMap<String, Object> userData = new HashMap<String, Object>();
		userData.put("id", 2563);
		userData.put("type", "donut");
		userData.put("name", "Cake");
		userData.put("ppu", 0.55);
		userData.put("IsMarried", true);

		ArrayList<String> hobbies = new ArrayList<String>();
		hobbies.add("Music");
		hobbies.add("Computer");
		hobbies.add("Games");

		userData.put("hobbies", hobbies);

		HashMap<String, String> TechSkills = new HashMap<String, String>();
		userData.put("Programming Languages", "Java");
		userData.put("UI Automation", "Selenium");
		userData.put("API Testing", "Rest API");

		userData.put("TechSkills", TechSkills);

		Response response = RestAssured.given().
				baseUri("https://reqres.in/api/users").
				contentType(ContentType.JSON).
				body(userData).
				post();

		response.prettyPrint();

		Assert.assertEquals(response.statusCode(), 201, "Check for status code");

		System.out.println("--------------------------------------------------------------------------");

	}

	@Test
	public void createUser2()
	{
		/*{
   "firstName":"Amod",
   "lastName":"Mahajan",
   "age": 28,
   "salary": 10000.56,
   "IsMarried":true,
   "Hobbies":["Music","Computer","Games"],
 "TechSkill":{
		"Programming language":"Java",
		"WebAutomation":"Selenium",
		"API testing" : "Rest Assured"


             }

 }*/

		HashMap <String,Object> UserData = new HashMap<String,Object>();
		UserData.put("firstName", "Amod");
		UserData.put("lastName", "Mahajan");
		UserData.put("age", 28);
		UserData.put("salary", 10000.56);
		UserData.put("IsMarried", true);

		ArrayList<String> hobbies = new ArrayList<String>();
		hobbies.add("Music");
		hobbies.add("computers");
		hobbies.add("Games");
		UserData.put("Hobbies",hobbies );

		HashMap <String,String> TechSkill = new HashMap<String,String>();
		TechSkill.put("Programming language", "Java");
		TechSkill.put("WebAutomation", "Seleniuim");
		TechSkill.put("API testing", "Rest Assured");

		UserData.put("TechSkill",TechSkill );


		Response response = RestAssured.given()
				.baseUri("https://reqres.in/api/users")
				.contentType(ContentType.JSON)
				.body(UserData)
				.post();

		response.prettyPrint();

		//verify status code
		Assert.assertEquals(response.statusCode(), 201,"check for status code.");

		System.out.println("--------------------------------------------------------------------------");
	}
}
