package testcases;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import routes.Routes;
import utilities.Contexts;

public class Authentication extends BaseTest{
	
	@Test(groups = {"Auth","smoke","sanity"})
	public void userAuthentication() {
		
		String authPayload = "{"
	            + "\"username\":\"admin\","
	            + "\"password\":\"password123\""
	            + "}";
		Response response =
		given()
			.contentType(ContentType.JSON)
	        .header("Accept", "*/*")
	        .body(authPayload)
	
		
		.when()
			.post(Routes.AUTHENTICATION)
		
		.then()
			.statusCode(200)
			.extract().response();
		
		String token = response.jsonPath().getString("token");
		Contexts.setToken(token);
	}

}
