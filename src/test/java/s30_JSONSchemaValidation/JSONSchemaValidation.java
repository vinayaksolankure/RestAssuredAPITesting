package s30_JSONSchemaValidation;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import java.io.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class JSONSchemaValidation {

    @Test
    public void testMethod() {
        String requestPayload = "{\r\n"
                + "    \"username\" : \"admin\",\r\n"
                + "    \"password\" : \"password123\"\r\n"
                + "}";
        
        RestAssured
        .given()
          .log().all()  // Log the request
          .baseUri("https://restful-booker.herokuapp.com")
          .basePath("/auth")
          .contentType(ContentType.JSON)
          .body(requestPayload)
        .when()
          .post()
        .then()
          .log().all()  // Log the response
          .assertThat()
          .statusCode(200)
          .body("token", Matchers.notNullValue())
          .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
          //.body(JsonSchemaValidator.matchesJsonSchema(new File("C:\\Users\\HP\\Desktop\\JSON Data\\schema.json")));

    }
}
