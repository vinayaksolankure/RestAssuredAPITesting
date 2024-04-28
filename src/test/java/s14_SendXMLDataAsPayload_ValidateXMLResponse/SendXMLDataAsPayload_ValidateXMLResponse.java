package s14_SendXMLDataAsPayload_ValidateXMLResponse;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import java.util.List;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

public class SendXMLDataAsPayload_ValidateXMLResponse {

	@Test
	public void addPet() {
		String xmlRequestBody = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
				+ "<Pet>\r\n"
				+ "	<id>0</id>\r\n"
				+ "	<Category>\r\n"
				+ "		<id>0</id>\r\n"
				+ "		<name>string</name>\r\n"
				+ "	</Category>\r\n"
				+ "	<name>doggie</name>\r\n"
				+ "	<photoUrls>\r\n"
				+ "		<photoUrl>string</photoUrl>\r\n"
				+ "	</photoUrls>\r\n"
				+ "	<tags>\r\n"
				+ "		<Tag>\r\n"
				+ "			<id>0</id>\r\n"
				+ "			<name>string</name>\r\n"
				+ "		</Tag>\r\n"
				+ "	</tags>\r\n"
				+ "	<status>available</status>\r\n"
				+ "</Pet>";

		String jsonData = "{\r\n"
				+ "  \"id\": 0,\r\n"
				+ "  \"category\": {\r\n"
				+ "    \"id\": 0,\r\n"
				+ "    \"name\": \"string\"\r\n"
				+ "  },\r\n"
				+ "  \"name\": \"doggie\",\r\n"
				+ "  \"photoUrls\": [\r\n"
				+ "    \"string\"\r\n"
				+ "  ],\r\n"
				+ "  \"tags\": [\r\n"
				+ "    {\r\n"
				+ "      \"id\": 0,\r\n"
				+ "      \"name\": \"string\"\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"status\": \"available\"\r\n"
				+ "}";

		//https://petstore.swagger.io/v2/pet
		RequestSpecification reqSpec = given();
		reqSpec.baseUri("https://petstore.swagger.io");
		reqSpec.basePath("/v2/pet");
		reqSpec.header("accept", "application/xml");
		reqSpec.header("Content-Type", "application/xml");
		reqSpec.body(xmlRequestBody);

		// perform post request
		Response response =  reqSpec.post();
		response.prettyPrint();

		Assert.assertEquals(response.statusCode(), 200, "Check for status code");

		// Approch 1
		//response.then().body("Pet.name", Matchers.equalTo("doggie"));


		// Approch 2
		XmlPath objXmlPath = new XmlPath(response.asString()); 
		String name = objXmlPath.get("Pet.name");
		Assert.assertEquals(name, "doggie", "Check for name");
		
		// Verify total photoUrls in XML path
		List<String> listPhotoUrls = objXmlPath.getList("Pet.photoUrls.photoUrl");
		int ttlPhotoUrls = listPhotoUrls.size();
		
		Assert.assertEquals(ttlPhotoUrls, 1, "Check for totalPhotoUrls");
		
		// Verify for name doggie in Pet
		List<String> listPetNames = objXmlPath.getList("Pet.name");
		
		boolean found = false;
		for (String petName : listPetNames) {
			if(petName.equals("doggie"))
			{
				found = true;
				break;
			}
		}
		
		Assert.assertEquals(found, true);
	}
}
