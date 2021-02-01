// Booking Exception class defined, extends Exception class
public class BookingException extends Exception{

	// Calls the default super constructor
	public BookingException() {
		super();
	}
	
	// This constructor takes a string as a parameter
	// Allows a message to be thrown with the exception
	public BookingException(String message) {
		super(message);
	}
}
