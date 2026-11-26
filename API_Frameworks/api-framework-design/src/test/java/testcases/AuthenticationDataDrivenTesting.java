package testcases;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertFalse;

import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import routes.Routes;

public class AuthenticationDataDrivenTesting extends BaseTest {

    @Test(
        groups = {"Auth","datadriven","smoke","sanity"},
        dataProvider = "jsonDataProvider",
        dataProviderClass = utilities.DataProviders.class
    )
    public void userAuthentication(Map<String, String> data) {

        JSONObject authBody = new JSONObject();

        authBody.put("username", data.get("username"));
        authBody.put("password", data.get("password"));
        
        String testType = data.get("testType");

        Response response =
            given()
                .contentType(ContentType.JSON)
                .header("Accept", "*/*")
                .body(authBody.toString())
            .when()
                .post(Routes.AUTHENTICATION)
            .then()
                .statusCode(200)
                .extract()
                .response();

    
        
        String token = response.jsonPath().getString("token");

        if (testType.equalsIgnoreCase("valid")) {
        	if(token == null || token.isBlank()) {
        		Assert.fail("Invalid credentials not allowed for logged in and not retun any token");
        		
        	}else {
        		Assert.assertTrue(true,"Valid credential shoud return the token");
        	}
        }else if(testType.equalsIgnoreCase("invalid")) {
        	if (token == null || token.isBlank()) {
        		Assert.assertTrue(true);
        	}else {
        		Assert.assertFalse(true);
        	}
        }
    }
}