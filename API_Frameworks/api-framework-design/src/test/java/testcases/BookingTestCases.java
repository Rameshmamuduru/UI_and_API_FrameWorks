package testcases;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.Booking;
import pojo.BookingDates;
import routes.Routes;
import utilities.Contexts;

import static io.restassured.RestAssured.given;

public class BookingTestCases extends BaseTest {
	
	// Retrive all the booking ids
	@Test(groups = {"sanity"})
	public void TC_001_getAllBokingIds() {
		given()
		
		
		.when()
			.get(Routes.GET_BOOKING_IDS)
		
		.then()
			.statusCode(200);
	}
	
	// Retrive with booking id
		@Test(groups = {"sanity"})
		public void TC_002_getBookingId() {
			//String id = properties.getProperty("id");
					
			given()
				.pathParam("id", properties.getProperty("id"))
	
			
			.when()
				.get(Routes.GET_BOOKING_WITH_ID)
			
			.then()
				.statusCode(200);
		}
	
		

		@Test(groups = {"createBooking","sanity"})
		public void TC_003_createBooking() {
			
		
		    Booking booking = new Booking(
		            "Jims",
		            "Brown",
		            111,
		            true,
		            new BookingDates("2018-01-01", "2019-01-01"),
		            "Breakfast"
		    );
		
		    Response response = given()
		            .contentType(ContentType.JSON)
		            .header("Accept", "*/*")
		            .body(booking)
		            .log().all()
		        .when()
		            .post(Routes.CREATE_BOOKING);
		
		    System.out.println("Status Code: " + response.getStatusCode());
		    System.out.println("Response Body: " + response.asPrettyString());
		
		    // Validate response
		    response.then()
		            .statusCode(200);
		
		    // Extract booking ID
		    int bookingId = response.jsonPath().getInt("bookingid");
		    
		    Contexts.setBookingId(bookingId);
		
		    System.out.println("Created Booking ID: " + bookingId);
		}
		
	
		
		@Test(dependsOnGroups = {"Auth","createBooking","smoke"})
		public void TC_004_updateBooking() {
			
			System.out.println("======================================");
			System.out.println("TC_003_createBooking");
			
			String token = Contexts.getToken();
			System.out.println("retrived Token from contexts: " +token);
			int bookingid = Contexts.getBookingId();
		
		    Booking booking = new Booking(
		            "James",
		            "Brown",
		            111,
		            true,
		            new BookingDates("2018-01-01", "2019-01-01"),
		            "Breakfast"
		    );
		
		    Response response = given()
		            .contentType(ContentType.JSON)
		            .header("Accept", "*/*")
		            .body(booking)
		            .cookie("token", token)
		            .log().all()
		            .pathParam("id", bookingid)
		        .when()
		            .put(Routes.UPDATE_BOOKING);
		
		    // Validate response
		    response.then()
		            .statusCode(200);
		
		}


}
