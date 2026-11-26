package routes;

public class Routes {
	
	//Baseurl
	
	public static final String BASE_URL = "https://restful-booker.herokuapp.com/";
	
	//Authentication
	public static final String AUTHENTICATION = "auth";
	
	//Booking
	public static final String GET_BOOKING_IDS = "booking";
	public static final String GET_BOOKING_WITH_ID = "booking/{id}";
	public static final String CREATE_BOOKING = "booking";
	public static final String UPDATE_BOOKING = "booking/{id}";
	public static final String PARTIAL_BOOKING_UPDATE = "booking/{id}";
	public static final String DELETE_BOOKING = "booking/{id}";

}
