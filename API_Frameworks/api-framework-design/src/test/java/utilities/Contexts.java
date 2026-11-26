package utilities;

public class Contexts {
	
	public static int bookingId;
	public static String token;
	
	public static void setBookingId(int id) {
		bookingId = id;
	}
	
	public static int getBookingId() {
		return bookingId;
	}
	
	public static void setToken(String tkn) {
		token = tkn;
	}
	
	public static String getToken() {
		return token;
	}

}
