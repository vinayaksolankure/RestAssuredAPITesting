package s16_createJSONArray_usingJSONObject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class createJSONArray_usingJSONObject {

	@Test(priority = 0)
	public void createUserUsingJSONArray() {


		/*{
		 "firstName":"Prachi",
		   "lastName":"Gupta",
		   "age": 28,
		   "salary": 10000.56,
		}*/

		//create JSONOBjects for users

		JSONObject user1 = new JSONObject();
		user1.put("firstName","Vinayak");
		user1.put("lastName","Solankure");
		user1.put("age",28);
		user1.put("salary",10000.56);

		JSONObject user2 = new JSONObject();
		user2.put("firstName","Sourabh");
		user2.put("lastName","Bhalerao");
		user2.put("age",28);
		user2.put("salary",10000.56);

		JSONObject user3 = new JSONObject();
		user3.put("firstName","Shirish");
		user3.put("lastName","Mistari");
		user3.put("age",28);
		user3.put("salary",10000.56);

		//add JSON Object to JSON Array
		JSONArray userPayload = new JSONArray();
		userPayload.add(user1);
		userPayload.add(user2);
		userPayload.add(user3);

		// create requestspecification
		RequestSpecification reqSpec = given();
		reqSpec.baseUri("https://reqres.in/api/users");
		reqSpec.contentType(ContentType.JSON);
		reqSpec.body(userPayload);

		// perform post request
		Response response = reqSpec.post();
		response.prettyPrint();

		//verify status code
		Assert.assertEquals(response.statusCode(), 201,"check for status code.");
		System.out.println("-------------------------------------------------------------------");
	}
	
	@Test(priority = 1)
	public void createJSONArrayUsingList() {


		/*{
		 "firstName":"Prachi",
		   "lastName":"Gupta",
		   "age": 28,
		   "salary": 10000.56,
		}*/

		//create JSONOBjects for users

		Map<String, Object> user1 = new HashMap<String, Object>();
		user1.put("firstName","Vinayak");
		user1.put("lastName","Solankure");
		user1.put("age",28);
		user1.put("salary",10000.56);

		Map<String, Object> user2 = new HashMap<String, Object>();
		user2.put("firstName","Sourabh");
		user2.put("lastName","Bhalerao");
		user2.put("age",28);
		user2.put("salary",10000.56);

		Map<String, Object> user3 = new HashMap<String, Object>();
		user3.put("firstName","Shirish");
		user3.put("lastName","Mistari");
		user3.put("age",28);
		user3.put("salary",10000.56);

		// Create JSON Array using List
		List<Map<String, Object>> jsonArrayListPayload = new ArrayList<>();
		jsonArrayListPayload.add(user1);
		jsonArrayListPayload.add(user2);
		jsonArrayListPayload.add(user3);

		// create requestspecification
		RequestSpecification reqSpec = given();
		reqSpec.baseUri("https://reqres.in/api/users");
		reqSpec.contentType(ContentType.JSON);
		reqSpec.body(jsonArrayListPayload);

		// perform post request
		Response response = reqSpec.post();
		response.prettyPrint();

		//verify status code
		Assert.assertEquals(response.statusCode(), 201,"check for status code.");
		System.out.println("-------------------------------------------------------------------");
	}
}
