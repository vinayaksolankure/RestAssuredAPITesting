package s18_UploadFile;

import java.io.File;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UploadFile {
	
	@Test(priority = 0)
	public void uploadFile() {
		// Create File Object
		
		String filePath = ".//testFileUpload.txt";
		String filePath2 = ".//testFileUploadSecond.txt";
		
		File testFileUpload = new File(filePath);
		File testFileUpload2 = new File(filePath2);
		
		// Create Request Specification
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("https://httpbin.org/post");
		
		reqSpec.multiPart("files", testFileUpload);
		reqSpec.multiPart("files", testFileUpload2);
		reqSpec.contentType("multipart/form-data");
		Response response = reqSpec.post();
		response.prettyPrint();
		
		Assert.assertEquals(response.getStatusCode(), 200, "Check for status code");
	}
	
	@Test(priority = 1)
	public void uploadFile2() {
		// Create File Object
		
		String filePath = ".//roseImage.jpg";
		
		File testFileUpload = new File(filePath);
		
		// Create Request Specification
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("https://petstore.swagger.io/v2/pet/1/uploadImage");
		
		reqSpec.multiPart("file", testFileUpload);
		reqSpec.contentType("multipart/form-data");
		Response response = reqSpec.post();
		response.prettyPrint();
		
		Assert.assertEquals(response.getStatusCode(), 200, "Check for status code");
	}
}
