package s03;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;

public class Test_PatchMethod {
	
	@Test
	public void test05() {
		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "Sourabh");
		jsonData.put("job", "Automation Engineer");
		
		baseURI = "https://reqres.in/api/users/874";
		given().
		   header("Content-type","application/json").
		   contentType(ContentType.JSON).
		   body(jsonData.toJSONString()).
		when().
		   patch().
		then().
		   statusCode(200).
		   log().
		   all();
	}
}
